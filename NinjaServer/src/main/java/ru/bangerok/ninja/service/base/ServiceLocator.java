package ru.bangerok.ninja.service.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.rest.controllers.auth.AuthController;
import ru.bangerok.ninja.service.MailService;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.service.UserService;
import ru.bangerok.ninja.service.UserSettingService;

/**
 * <p> Service class to get the services you want. </p>
 * Used for example here: {@link AuthController}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Getter
@RequiredArgsConstructor
@Service
public class ServiceLocator {

  private final UserService userService;
  private final MailService mailService;
  private final MessageService messageService;
  private final UserSettingService userSettingService;
}