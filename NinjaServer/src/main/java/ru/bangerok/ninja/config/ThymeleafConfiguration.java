package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Конфигурационный java класс для настройки использования шаблонов thymeleaf.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Configuration
@EnableWebMvc
public class ThymeleafConfiguration {

		/**
		 * Получение движка шаблонов, который будет использоваться для загрузки и заполнения шаблонов
		 * @return конечный объект - SpringTemplateEngine
		 */
		@Bean
		public SpringTemplateEngine templateEngine() {
				SpringTemplateEngine templateEngine = new SpringTemplateEngine();
				templateEngine.setTemplateResolver(thymeleafTemplateResolver());
				return templateEngine;
		}

		/**
		 * Конфигурация путей до шаблонов и формирования названия файлов с ними.
		 * @return конфигурационный объект с настройками.
		 */
		@Bean
		public ITemplateResolver thymeleafTemplateResolver() {
				ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
				templateResolver.setPrefix("templates/");
				templateResolver.setSuffix(".html");
				templateResolver.setTemplateMode("HTML");
				templateResolver.setCharacterEncoding("UTF-8");
				return templateResolver;
		}
}
