package ru.bangerok.enterprise.ninja.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ru.bangerok.enterprise.ninja.exception.email.FailureSendEmailException;
import ru.bangerok.enterprise.ninja.service.MailService;
import ru.bangerok.enterprise.ninja.service.MessageService;

/**
 * <p> Service class (implementation) for working with sending letters to email. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.4.3
 */
@RequiredArgsConstructor
@Slf4j
@Scope("singleton")
@Service
public class MailServiceImpl implements MailService {

  private final MessageService messageService;
  private final JavaMailSender mailSender;
  private final SpringTemplateEngine thymeleafTemplateEngine;

  @Override
  public void sendVerifiedMessage(String toEmail, String token) {
    var templateModel = new HashMap<String, Object>();
    templateModel.put("text", messageService.getMessage("email.registration.confirmation.text"));
    templateModel
        .put("urlName", messageService.getMessage("email.registration.confirmation.url.name"));
    templateModel.put("token", token);

    sendMessageUsingThymeleafTemplate(
        toEmail,
        messageService.getMessage("email.registration.confirmation.subject"),
        templateModel,
        "email/confirmation-email"
    );
  }

  private void sendMessageUsingThymeleafTemplate(
      String to, String subject, Map<String, Object> templateModel, String templateName) {
    var thymeleafContext = new Context();
    thymeleafContext.setVariables(templateModel);

    var htmlBody = thymeleafTemplateEngine.process(templateName, thymeleafContext);
    sendHtmlMessage(to, subject, htmlBody);
  }

  private void sendHtmlMessage(String to, String subject, String htmlBody) {
    try {
      var message = mailSender.createMimeMessage();
      var helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(htmlBody, true);
      mailSender.send(message);
    } catch (MessagingException e) {
      throw new FailureSendEmailException(messageService.getMessageWithArgs(
          "email.error.send.msg",
          new Object[] {to, subject}
      ));
    }
  }
}