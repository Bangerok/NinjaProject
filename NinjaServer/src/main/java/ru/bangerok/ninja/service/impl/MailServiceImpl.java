package ru.bangerok.ninja.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.env.Environment;
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
		public void sendVerifiedMessage(String toEmail, String token) throws MessagingException {
				Map<String, Object> templateModel = new HashMap<>();
				templateModel.put("text", messageService.getMessage("register.email.confirmation.text"));
				templateModel.put("urlName", messageService.getMessage("register.email.confirmation.url.name"));
				templateModel.put("token", token);

				sendMessageUsingThymeleafTemplate(
						toEmail,
						messageService.getMessage("register.email.confirmation.subject"),
						templateModel,
						"email/confirmation-email"
				);
		}

		private void sendMessageUsingThymeleafTemplate(
				String to, String subject, Map<String, Object> templateModel, String templateName)
				throws MessagingException {

				Context thymeleafContext = new Context();
				thymeleafContext.setVariables(templateModel);

				String htmlBody = thymeleafTemplateEngine.process(templateName, thymeleafContext);

				sendHtmlMessage(to, subject, htmlBody);
		}

		private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setFrom(Objects.requireNonNull(env.getProperty("support.email")));
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(htmlBody, true);
				mailSender.send(message);
		}
}
