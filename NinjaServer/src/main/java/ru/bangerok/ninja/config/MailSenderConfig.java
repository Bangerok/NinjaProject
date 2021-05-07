package ru.bangerok.ninja.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ru.bangerok.ninja.service.impl.MailServiceImpl;

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
		 * Getting and configuring the settings object for sending messages to the emails.
		 * <p>
		 * Used for example here: {@link MailServiceImpl}.
		 *
		 * @return configuration object with mail sender settings.
		 */
		@Bean
		public JavaMailSender getJavaMailSender() {
				JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
				mailSender.setHost("smtp.gmail.com");
				mailSender.setPort(587);

				mailSender.setUsername("kuznetsov.w.a@gmail.com");
				mailSender.setPassword("123qweasdZxc123qweasdZxc");

				Properties props = mailSender.getJavaMailProperties();
				props.put("mail.transport.protocol", "smtp");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");

				return mailSender;
		}
}