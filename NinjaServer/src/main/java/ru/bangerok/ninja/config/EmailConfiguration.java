package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
public class EmailConfiguration {

		@Bean
		public SpringResourceTemplateResolver thymeleafTemplateResolver() {
				SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
				templateResolver.setPrefix("/WEB-INF/templates/");
				templateResolver.setSuffix(".html");
				templateResolver.setTemplateMode("HTML5");
				return templateResolver;
		}

		@Bean
		public SpringTemplateEngine templateEngine() {
				SpringTemplateEngine templateEngine = new SpringTemplateEngine();
				templateEngine.setTemplateResolver(thymeleafTemplateResolver());
				return templateEngine;
		}

		@Bean
		public ThymeleafViewResolver thymeleafViewResolver() {
				ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
				viewResolver.setTemplateEngine(templateEngine());
				return viewResolver;
		}
}
