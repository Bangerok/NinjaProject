package ru.bangerok.ninja.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import ru.bangerok.ninja.security.CustomPermissionEvaluator;

/**
 * Конфигурационный java класс для того, чтобы использовать кастомный класс, в котором определены
 * методы, которые можно вызывать из @PreAuthorize или @PostAuthorize.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

		/**
		 * Метод для создания и настройки handler, который вызывается при использовании выражений в
		 *
		 * @return настроенный handler с кастомным сервисным классом внутри. Аннотации - PreAuthorize
		 * или @PostAuthorize.
		 */
		@Override
		protected MethodSecurityExpressionHandler createExpressionHandler() {
				DefaultMethodSecurityExpressionHandler expressionHandler =
						new DefaultMethodSecurityExpressionHandler();
				expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
				return expressionHandler;
		}
}