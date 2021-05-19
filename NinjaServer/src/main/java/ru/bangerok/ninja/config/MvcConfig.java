package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration java class for configuring allowed hosts for requests, allowed rest methods,
 * allowed headers in server requests, etc.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

		/**
		 * Method for adding CORS mapping.
		 *
		 * @param registry config file for CORS.
		 */
		@Override
		public void addCorsMappings(CorsRegistry registry) {
				long MAX_AGE_SECS = 3600;

				registry.addMapping("/**")
						.allowedOriginPatterns("*")
						.allowedMethods("GET", "POST", "PUT")
						.allowedHeaders("*")
						.allowCredentials(true)
						.maxAge(MAX_AGE_SECS);
		}
}