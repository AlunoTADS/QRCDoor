package br.ufpr.qrcdoor.controller;

import static br.ufpr.qrcdoor.interceptor.WebSocketSessionCapturingHandlerDecorator.estruturasConectadas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.qrcdoor.entity.Chave;
import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.exception.AcessoUnauthorizedException;
import br.ufpr.qrcdoor.exception.EstruturaNotConnectedException;
import br.ufpr.qrcdoor.service.ChaveService;
import br.ufpr.qrcdoor.service.EstruturaService;

@SuppressWarnings("rawtypes")
@RestController
public class AppController {

	@Autowired
	ChaveService chaveService;
	@Autowired
	EstruturaService estruturaService;

	@PostMapping("/app/registry")
	public ResponseEntity register(@RequestBody String body) throws Exception {
		Chave chave = new ObjectMapper().readValue(body, Chave.class);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.chaveService.register(chave, authentication.getPrincipal().toString()));
	}

	@PostMapping("/app/open/{idEstrutura}")
	public ResponseEntity open(@PathVariable Long idEstrutura, @RequestBody String c) throws Exception {
		Chave chave = new ObjectMapper().readValue(c, Chave.class);
		Estrutura estrutura = new Estrutura();
		estrutura.setId(idEstrutura);
		if (idEstrutura != null && chave != null && chave.getId() != null && this.estruturaService.hasPermission(chave, estrutura)) {
			if (estruturasConectadas.containsKey(idEstrutura.toString())) {
				WebSocketSession session = estruturasConectadas.get(idEstrutura.toString());
				if (session != null) {
					TextMessage msg = new TextMessage("OPEN");
					session.sendMessage(msg);
					return ResponseEntity.status(HttpStatus.OK).body("");
				}
			}
			throw new EstruturaNotConnectedException();
		}
		throw new AcessoUnauthorizedException();
	}

	@GetMapping("/app/list")
	public List<Estrutura> list(Pageable pageable) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.estruturaService.list(authentication.getPrincipal().toString());
	}

}
