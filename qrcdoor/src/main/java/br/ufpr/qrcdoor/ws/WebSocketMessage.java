package br.ufpr.qrcdoor.ws;

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
