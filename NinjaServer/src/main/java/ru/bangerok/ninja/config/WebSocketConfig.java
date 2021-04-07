package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Конфигурационный java класс для настройки подписок на серверные события.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

		/**
		 * Конфигурация брокера сообщений
		 * @param config объект брокера для конфигурации
		 */
		@Override
		public void configureMessageBroker(MessageBrokerRegistry config) {
				config.enableSimpleBroker("/topic");
				config.setApplicationDestinationPrefixes("/app");
		}

		/**
		 * Регистрация конечных точек для подписки на них в будущем.
		 * @param registry объект с информацией об этих точках.
		 */
		@Override
		public void registerStompEndpoints(StompEndpointRegistry registry) {
				registry.addEndpoint("/gs-guide-websocket").withSockJS();
		}

}
