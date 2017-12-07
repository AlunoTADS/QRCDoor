package br.ufpr.qrcdoor.interceptor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import br.ufpr.qrcdoor.crypt.Rsa;
import br.ufpr.qrcdoor.entity.Acesso;
import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.entity.FuncaoPessoa;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura;
import br.ufpr.qrcdoor.entity.PermissaoEstrutura.PermissaoEstruturaPK;
import br.ufpr.qrcdoor.entity.PermissaoFuncao;
import br.ufpr.qrcdoor.entity.PermissaoPessoa;
import br.ufpr.qrcdoor.repository.AcessoRepository;
import br.ufpr.qrcdoor.repository.ChaveRepository;
import br.ufpr.qrcdoor.repository.FuncaoPessoaRepository;
import br.ufpr.qrcdoor.repository.PermissaoFuncaoRepository;
import br.ufpr.qrcdoor.repository.PermissaoPessoaRepository;
import br.ufpr.qrcdoor.service.EstruturaService;
import br.ufpr.qrcdoor.service.PermissaoEstruturaService;

public class WebSocketSessionCapturingHandlerDecorator extends WebSocketHandlerDecorator {

	@Autowired
	private EstruturaService estruturaService;
	@Autowired
	private ChaveRepository chaveRepository;
	@Autowired
	private AcessoRepository acessoRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketSessionCapturingHandlerDecorator.class);
	public static HashMap<String, WebSocketSession> estruturasConectadas = new HashMap<String, WebSocketSession>();

	public WebSocketSessionCapturingHandlerDecorator(WebSocketHandler delegate) {
		super(delegate);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("afterConnectionEstablished");
		estruturasConectadas.put(session.getPrincipal().getName(), session);
		super.afterConnectionEstablished(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		logger.info("afterConnectionClosed");
		estruturasConectadas.remove(session.getPrincipal().getName());
		super.afterConnectionClosed(session, closeStatus);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		logger.info("handleMessage");
		try {
			
			// TextMessage msg = new TextMessage("Hello, " +
			// message.getPayload() + "!");
			Rsa rsa = new Rsa();
			BigInteger decrypt = rsa.decrypt(new BigInteger(((TextMessage)message).getPayload()
					.split("[:]")[1].trim(), 16));

			String[] accessData = new String(decrypt.toByteArray()).split("[.]");

			String c = accessData[0];
			String p = accessData[1];
			String s = accessData[2];
			String t = accessData[3];
			String h = accessData[4];
			String cspth = "#" + c + "." + p + "." + s + "." + t + "#";

			MessageDigest md = MessageDigest.getInstance("MD5");
			String hash = new BigInteger(1, md.digest(cspth.getBytes())).toString(16);

			// Verifica se mensagem não foi adulterada
			if (!hash.substring(0, 4).equals(h)) {
				session.sendMessage(new TextMessage("ERROR"));
				return;
			}

			long date = new BigInteger(t, 16).multiply(BigInteger.valueOf(1000L)).longValue();
			long now = new Date().getTime();
			long tolerance = 5L * 60L * 1000L; // 5 minutes
			if (!(now - tolerance < date && date < now + tolerance)) {
				session.sendMessage(new TextMessage("ERROR"));
				return;
			}

			Chave chave = new Chave();
			chave.setId(Long.valueOf(c));
			chave.setAssinatura(s);

			Estrutura estrutura = new Estrutura();
			estrutura.setId(Long.valueOf(session.getPrincipal().getName()));
			if (this.estruturaService.hasPermission(chave, estrutura)) {
				session.sendMessage(new TextMessage("OPEN"));
				Acesso acesso = new Acesso();
				acesso.setChave(chave);
				acesso.setDataHoraAbertura(new Date());
				acesso.setEstrutura(estrutura);
				acesso.setPessoa(this.chaveRepository.findOne(chave.getId()).getPessoa());
				this.acessoRepository.save(acesso);
			} else {
				session.sendMessage(new TextMessage("ERROR"));
			}

		} catch (Exception e) {
			session.sendMessage(new TextMessage("ERROR"));
		}
		
		super.handleMessage(session, message);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("handleTransportError");
		super.handleTransportError(session, exception);
	}

}
