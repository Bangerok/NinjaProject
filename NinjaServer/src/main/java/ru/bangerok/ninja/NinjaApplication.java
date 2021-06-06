package ru.bangerok.ninja;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import ru.bangerok.ninja.config.DatabaseConfig;
import ru.bangerok.ninja.config.MailSenderConfig;
import ru.bangerok.ninja.config.MessagesConfig;
import ru.bangerok.ninja.config.MethodSecurityConfig;
import ru.bangerok.ninja.config.MvcConfig;
import ru.bangerok.ninja.config.OAuth2LoginConfig;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.config.ThymeleafConfiguration;
import ru.bangerok.ninja.config.properties.JwtProperties;

/**
 * A class to run the entire spring application. To run, just use the command - mvn spring-boot:
 * run.
 * <p>
 * Application configuration is represented by these classes:
 * <p> - {@link JwtProperties},
 * <p> - {@link MessagesConfig},
 * <p> - {@link MethodSecurityConfig},
 * <p> - {@link MvcConfig},
 * <p> - {@link SecurityConfig},
 * <p> - {@link ThymeleafConfiguration},
 * <p> - {@link MailSenderConfig},
 * <p> - {@link DatabaseConfig},
 * <p> - {@link OAuth2LoginConfig}.
 *
 * @author v.kuznetsov
 * @since 0.0.0
 */
@ConfigurationPropertiesScan("ru.bangerok.ninja.config.properties")
public class NinjaApplication {

		public static void main(String[] args) {
				run(NinjaApplication.class, args);
		}
}