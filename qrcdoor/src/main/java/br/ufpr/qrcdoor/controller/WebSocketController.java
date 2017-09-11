package br.ufpr.qrcdoor.controller;

import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufpr.qrcdoor.ws.WebSocketMessage;


@Controller
public class WebSocketController {

    @MessageMapping("/auth/{id}")
    @SendTo("/open/{id}")
    public WebSocketMessage auth(@Payload String content, @DestinationVariable Integer id) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new WebSocketMessage(content);
    }

    @RequestMapping("/websocket")
    public String index(Map<String, Object> model) {
        return "websocket/index";
    }

}
