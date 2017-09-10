package br.ufpr.qrcdoor.ws;

import java.util.Map;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public WebSocketMessage message(String content) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new WebSocketMessage(content);
    }

    @RequestMapping("/websocket")
    public String index(Map<String, Object> model) {
        return "websocket/index";
    }

}
