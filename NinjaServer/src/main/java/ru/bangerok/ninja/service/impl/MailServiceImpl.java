package ru.bangerok.ninja.service.impl;

import java.util.Objects;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.service.MailService;
import ru.bangerok.ninja.service.MessageService;

@Service
public class MailServiceImpl implements MailService {

		private final MessageService messageService;
		private final JavaMailSender mailSender;
		private final Environment env;

		public MailServiceImpl(MessageService messageService, JavaMailSender mailSender,
				Environment env) {
				this.messageService = messageService;
				this.mailSender = mailSender;
				this.env = env;
		}

		@Override
		public SimpleMailMessage constructEmailMessage(String toEmail, String subject, String message) {
				SimpleMailMessage emailMsg = new SimpleMailMessage();
				if (Objects.nonNull(subject)) {
						emailMsg.setSubject(message);
				}

				if (Objects.nonNull(message)) {
						emailMsg.setText(message);
				}

				emailMsg.setTo(toEmail);
				emailMsg.setFrom(Objects.requireNonNull(env.getProperty("support.email")));
				return emailMsg;
		}

		@Override
		public SimpleMailMessage configureVerifiedMessage(SimpleMailMessage emailMsg, String token) {
				emailMsg.setSubject(messageService.getMessage("register.email.confirmation.subject"));
				String message = messageService.getMessage("register.email.confirmation.text");
				String confirmationUrl =
						"<a href='localhost:3000/?confirmEmailToken=" + token + "'> url </a>";
				emailMsg.setText(message + "\n" + confirmationUrl);
				return emailMsg;
		}

		@Override
		public SimpleMailMessage configureResendVerifiedMessage(SimpleMailMessage emailMsg,
				String newToken) {
				emailMsg.setSubject(messageService.getMessage("register.email.confirmation.subject"));
				String message = messageService.getMessage("register.email.confirmation.text");
				String confirmationUrl =
						"<a href='localhost:3000/?confirmEmailToken=" + newToken + "'> url </a>";
				emailMsg.setText(message + "\n" + confirmationUrl);
				return emailMsg;
		}

		@Override
		public void send(SimpleMailMessage emailMessage) {
				mailSender.send(emailMessage);
		}
}
