package br.ufpr.qrcdoor.websocket;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import br.ufpr.qrcdoor.service.EstruturaService;

public class WebSocketMessageHandler extends TextWebSocketHandler {

	@Autowired
	private EstruturaService estruturaService;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, Exception {

		
	}
}