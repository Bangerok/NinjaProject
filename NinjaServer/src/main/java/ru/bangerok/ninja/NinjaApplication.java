package ru.bangerok.ninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
 * </p>
 * <p> - {@link JwtProperties}, </p>
 * <p> - {@link MessagesConfig}, </p>
 * <p> - {@link MethodSecurityConfig}, </p>
 * <p> - {@link MvcConfig}, </p>
 * <p> - {@link SecurityConfig}, </p>
 * <p> - {@link ThymeleafConfiguration}, </p>
 * <p> - {@link MailSenderConfig}, </p>
 * <p> - {@link DatabaseConfig}, </p>
 * <p> - {@link OAuth2LoginConfig}. </p>
 *
 * @author v.kuznetsov
 * @since 0.0.0
 */
@ConfigurationPropertiesScan("ru.bangerok.ninja.config.properties")
@SpringBootApplication
public class NinjaApplication {
  public static void main(String[] args) {
    SpringApplication.run(NinjaApplication.class, args);
  }
}