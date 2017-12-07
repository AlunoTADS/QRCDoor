package br.ufpr.qrcdoor.websocket;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import br.ufpr.qrcdoor.crypt.Rsa;
import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.service.EstruturaService;

public class WebSocketMessageHandler extends TextWebSocketHandler {
	
	@Autowired
	EstruturaService estruturaService;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws IOException, Exception {

		try {
//			TextMessage msg = new TextMessage("Hello, " + message.getPayload() + "!");
			Rsa rsa = new Rsa();
			BigInteger decrypt = rsa.decrypt(new BigInteger(message.getPayload().split("[:]")[1].trim(), 16));
			
			JSONObject json = new JSONObject(new String(decrypt.toByteArray()));
			String c = json.getString("c");
			String s = json.getString("s");
			String p = json.getString("p");
			String t = json.getString("t");
			String h = json.getString("h");
			String cspth = "#" + c + "." + s + "." + p + "." + t + "#";
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			String hash = new String(md.digest(cspth.getBytes()));
			
			// Verifica se mensagem não foi adulterada
			if (hash.equals(h)) {
				Chave chave = new Chave();
				chave.setId(Long.valueOf(c));
				chave.setAssinatura(s);
				Estrutura estrutura = new Estrutura();
				estrutura.setId(Long.valueOf(session.getPrincipal().getName()));
				if (estruturaService.hasPermission(chave, estrutura)) {
					TextMessage msg = new TextMessage("OPEN");
					session.sendMessage(msg);
				}
			}
		} catch (Exception e) {
			TextMessage msg = new TextMessage("ERROR");
			session.sendMessage(msg);
		}
	}
}