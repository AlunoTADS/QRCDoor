package br.ufpr.qrcdoor.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebSocketController {
	@Autowired
	private HttpSession session;

	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	// @MessageMapping("/auth")
	// public void handle(@Payload String message, MessageHeaders messageHeaders,
	// SimpMessageHeaderAccessor headerAccessor, Principal user) {
	//// logger.info(messageHeaders.toString());
	//// logger.info(headerAccessor.toString());
	// logger.info(user.getName());
	//
	//// String id = messageHeaders.;
	//// logger.info(id);
	// messagingTemplate.convertAndSendToUser(user.getName(), "/open", "Vai",
	// Collections.singletonMap(SimpMessageHeaderAccessor.SESSION_ID_HEADER,
	// headerAccessor.getSessionId()));
	//
	// messagingTemplate.convertAndSendToUser(user.getName(), "/open", "[" +
	// getTimestamp() + "]:" + user.getName() + ":" + message);
	// messagingTemplate.convertAndSend("/open", "[" + getTimestamp() + "]:" +
	// user.getName() + ":" + message);
	// }

	// @MessageMapping("/auth")
	// public void auth(Message<Object> message, @Payload String content) throws
	// Exception {
	// Principal principal =
	// message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER,
	// Principal.class);
	// messagingTemplate.convertAndSendToUser(principal.getName(), "/open",
	// content);
	// messagingTemplate.convertAndSend("/open", "[" + getTimestamp() + "]:" +
	// principal.getName() + ":" + content);
	// }

	@MessageMapping("/auth")
	public void auth(@Payload String message, MessageHeaders messageHeaders, SimpMessageHeaderAccessor headerAccessor,
			Principal user) throws Exception {

		messagingTemplate.convertAndSendToUser(user.getName(), "/open",
				String.format("[%s]%s:%s", getTimestamp(), user.getName(), message));

	}

	@RequestMapping("/websocket")
	public String index() {
		return "websocket/index";
	}

	private String getTimestamp() {
		LocalDateTime date = LocalDateTime.now();
		return date.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	@ResponseBody
	@RequestMapping("/sessionId")
	public String sessionId() {
		return this.session.getId();
	}

}
