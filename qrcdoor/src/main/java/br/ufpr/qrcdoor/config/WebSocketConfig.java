package br.ufpr.qrcdoor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import br.ufpr.qrcdoor.interceptor.HttpSessionIdHandshakeInterceptor;
import br.ufpr.qrcdoor.ws.WebSocketMessageHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
        .addHandler(messageHandler(), "/open")
        .withSockJS()
        .setInterceptors(httpSessionIdHandshakeInterceptor());
    }

    @Bean
    public WebSocketHandler messageHandler() {
        return new WebSocketMessageHandler();
    }
    
	@Bean
	public HttpSessionIdHandshakeInterceptor httpSessionIdHandshakeInterceptor() {
		return new HttpSessionIdHandshakeInterceptor();
	}
}
