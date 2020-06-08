package ru.bangerok.ninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.bangerok.ninja.config.AppPropertiesConfig;
import ru.bangerok.ninja.config.MethodSecurityConfig;
import ru.bangerok.ninja.config.MvcConfig;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.config.WebSocketConfig;

/**
 * Класс для запуска всего spring приложения. Для запуска просто используй команду - mvn
 * spring-boot:run.
 * <p>
 * Конфигурация приложения представлена этими классами: <p> - {@link AppPropertiesConfig}, <p> -
 * {@link MethodSecurityConfig}, <p> - {@link MvcConfig}, <p> - {@link SecurityConfig}, <p> - {@link
 * WebSocketConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(AppPropertiesConfig.class)
public class NinjaApplication {

		public static void main(String[] args) {
				SpringApplication.run(NinjaApplication.class, args);
		}
}
