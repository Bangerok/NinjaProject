package ru.bangerok.enterprise.ninja.service.impl;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ru.bangerok.enterprise.ninja.service.MessageService;

/**
 * <p> Service class (implementation) for working with localization of messages on the server. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.4.4
 */
@RequiredArgsConstructor
@SessionScope
@Service
public class MessageServiceImpl implements MessageService {

  private final MessageSource messages;
  private Locale locale = new Locale("en");

  @Override
  public void setLocale(String language) {
    locale = new Locale(language);
  }

  @Override
  public String getMessageWithArgs(String path, Object[] args) {
    return getMessage(path, args);
  }

  @Override
  public String getMessage(String path) {
    return getMessage(path, null);
  }

  private String getMessage(String path, Object[] args) {
    return messages.getMessage(path, args, locale);
  }
}