package br.ufpr.qrcdoor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import br.ufpr.qrcdoor.interceptor.HttpSessionIdHandshakeInterceptor;
import br.ufpr.qrcdoor.interceptor.SessionKeepAliveChannelInterceptor;
import br.ufpr.qrcdoor.interceptor.WebSocketSessionCapturingHandlerDecorator;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
//		config.enableStompBrokerRelay("/queue", "/topic/");
		config.enableSimpleBroker("/topic/", "/queue/");
		config.setApplicationDestinationPrefixes("/app");
	}


	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-door-manager").withSockJS().setInterceptors(httpSessionIdHandshakeInterceptor());
	}

	@Bean
	public HttpSessionIdHandshakeInterceptor httpSessionIdHandshakeInterceptor() {
		return new HttpSessionIdHandshakeInterceptor();
	}

	@SuppressWarnings("unused")
	@Autowired
	private WebSocketHandler subProtocolWebSocketHandler;

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
		registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
			@Override
			public WebSocketHandler decorate(WebSocketHandler webSocketHandler) {
				return new WebSocketSessionCapturingHandlerDecorator(webSocketHandler);
			}
		});
	}
}
