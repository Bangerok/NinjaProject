package ru.bangerok.ninja.config;

import ru.bangerok.ninja.security.CustomPermissionEvaluator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

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

		@Override
		protected MethodSecurityExpressionHandler createExpressionHandler() {
				DefaultMethodSecurityExpressionHandler expressionHandler =
						new DefaultMethodSecurityExpressionHandler();
				expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
				return expressionHandler;
		}
}
