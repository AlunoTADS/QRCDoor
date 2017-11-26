package br.ufpr.qrcdoor.websocket;

/**
 *
 * @author Lucas
 */
public class WebSocketMessage {

    private String content;

    public WebSocketMessage() {
    }

    public WebSocketMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
