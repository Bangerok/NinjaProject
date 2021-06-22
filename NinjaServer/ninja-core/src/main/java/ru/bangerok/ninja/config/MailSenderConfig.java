package ru.bangerok.ninja.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Configuration java class for declaring and configuring a bean for mail sender connection
 * settings.
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Configuration
public class MailSenderConfig {

  /**
   * Private field with username for mail sender.
   */
  @Value("${spring.mail.username:username}")
  private String username;

  /**
   * Private field with password for mail sender.
   */
  @Value("${spring.mail.password:password}")
  private String password;

  /**
   * <p> Getting and configuring the settings object for sending messages to the emails. </p>
   *
   * @return configuration object with mail sender settings.
   */
  @Bean
  public JavaMailSender getJavaMailSender() {
    var mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);

    mailSender.setUsername(username);
    mailSender.setPassword(password);

    var props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    return mailSender;
  }
}