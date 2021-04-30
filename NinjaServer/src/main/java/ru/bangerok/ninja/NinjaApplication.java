package ru.bangerok.ninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.bangerok.ninja.config.AppPropertiesConfig;
import ru.bangerok.ninja.config.MessagesConfig;
import ru.bangerok.ninja.config.MethodSecurityConfig;
import ru.bangerok.ninja.config.MvcConfig;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.config.ThymeleafConfiguration;

/**
 * A class to run the entire spring application. To run, just use the command - mvn spring-boot:
 * run.
 * <p>
 * Application configuration is represented by these classes:
 * <p> - {@link AppPropertiesConfig},
 * <p> - {@link MessagesConfig},
 * <p> - {@link MethodSecurityConfig},
 * <p> - {@link MvcConfig},
 * <p> - {@link SecurityConfig},
 * <p> - {@link ThymeleafConfiguration}.
 *
 * @author v.kuznetsov
 * @since 0.0.0
 */
@SpringBootApplication
@EnableConfigurationProperties(AppPropertiesConfig.class)
public class NinjaApplication {

		public static void main(String[] args) {
				SpringApplication.run(NinjaApplication.class, args);
		}
}