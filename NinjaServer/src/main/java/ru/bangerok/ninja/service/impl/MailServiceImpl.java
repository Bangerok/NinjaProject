package ru.bangerok.ninja.service.impl;

import java.util.Objects;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.service.MailService;

@Service
public class MailServiceImpl implements MailService {

		private final JavaMailSender mailSender;
		private final Environment env;

		public MailServiceImpl(JavaMailSender mailSender, Environment env) {
				this.mailSender = mailSender;
				this.env = env;
		}

		@Override
		public SimpleMailMessage constructEmailMessage(String toEmail, String message) {
				SimpleMailMessage emailMsg = new SimpleMailMessage();
				emailMsg.setTo(toEmail);
				emailMsg.setSubject("Test subject");
				if (Objects.nonNull(message)) {
						emailMsg.setText(message);
				}
				emailMsg.setFrom(Objects.requireNonNull(env.getProperty("support.email")));
				return emailMsg;
		}

		@Override
		public SimpleMailMessage configureVerifiedMessage(SimpleMailMessage emailMsg, String token) {
				emailMsg.setSubject("Registration Confirmation");
				String confirmationUrl = "/registrationConfirm.html?token=" + token;
				String message = "You registered successfully. We will send you a confirmation message to your email account.";
				emailMsg.setText(message + "\n" + confirmationUrl);
				return emailMsg;
		}

		@Override
		public void send(SimpleMailMessage emailMessage) {
				mailSender.send(emailMessage);
		}
}
