package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
public class ThymeleafConfiguration {

		@Bean
		public SpringTemplateEngine templateEngine() {
				SpringTemplateEngine templateEngine = new SpringTemplateEngine();
				templateEngine.setTemplateResolver(thymeleafTemplateResolver());
				return templateEngine;
		}

		@Bean
		public ITemplateResolver thymeleafTemplateResolver() {
				ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
				templateResolver.setPrefix("templates/");
				templateResolver.setSuffix(".html");
				templateResolver.setTemplateMode("HTML");
				templateResolver.setCharacterEncoding("UTF-8");
				return templateResolver;
		}

		@Bean
		public ThymeleafViewResolver thymeleafViewResolver() {
				ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
				viewResolver.setTemplateEngine(templateEngine());
				return viewResolver;
		}
}
