package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурационный java класс для настройки разрешенных хостов для запросов, разрешенных методов
 * rest, разрешенных заголовков в запросах к серверу и т.д.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

		/**
		 * Метод для добавления CORS маппинга.
		 *
		 * @param registry конфигурационный файл для CORS.
		 */
		@Override
		public void addCorsMappings(CorsRegistry registry) {
				long MAX_AGE_SECS = 3600;

				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true)
						.maxAge(MAX_AGE_SECS);
		}
}
