package ru.bangerok.ninja.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ru.bangerok.ninja.service.MailService;
import ru.bangerok.ninja.service.MessageService;

@Service
public class MailServiceImpl implements MailService {

		private final MessageService messageService;
		private final JavaMailSender mailSender;
		private final Environment env;
		private final SpringTemplateEngine thymeleafTemplateEngine;

		public MailServiceImpl(MessageService messageService, JavaMailSender mailSender,
				Environment env, SpringTemplateEngine thymeleafTemplateEngine) {
				this.messageService = messageService;
				this.mailSender = mailSender;
				this.env = env;
				this.thymeleafTemplateEngine = thymeleafTemplateEngine;
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
		public void sendVerifiedMessage(String toEmail, String token) throws MessagingException {
				Map<String, Object> templateModel = new HashMap<>();
				templateModel.put("text", messageService.getMessage("register.email.confirmation.text"));
				templateModel.put("url", "localhost:3000/?confirmEmailToken=" + token);

				send(
						toEmail,
						messageService.getMessage("register.email.confirmation.subject"),
						"confirmation-email",
						templateModel
				);
		}

		@Override
		public void send(String to, String subject, String templateName,
				Map<String, Object> templateModel) throws MessagingException {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setTo(to);

				if (subject.isEmpty()) {
						helper.setSubject(subject);
				}

				Context thymeleafContext = new Context();
				thymeleafContext.setVariables(templateModel);
				String htmlBodyTest = thymeleafTemplateEngine
						.process(templateName + ".html", thymeleafContext);
				helper.setText(htmlBodyTest, true);

				mailSender.send(message);
		}
}
