package ru.bangerok.ninja.config;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Конфигурационный java класс объявления и настройки бина для локализации обычных и валидационных
 * сообщений.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Configuration
public class MessagesConfig {

		/**
		 * Получение и конфигурация ResourceBundle для локализации сообщений по ключу.
		 *
		 * @return конфигурационный объект с настройками для получения сообщений.
		 */
		@Bean
		public ResourceBundleMessageSource messages() {
				var source = new ResourceBundleMessageSource();
				source.setBasenames("messages/labels");
				source.setDefaultEncoding(StandardCharsets.UTF_8.name());
				source.setUseCodeAsDefaultMessage(true);

				return source;
		}
}