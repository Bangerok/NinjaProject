package ru.bangerok.enterprise.ninja.event.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.bangerok.enterprise.ninja.event.OnRegistrationCompleteEvent;
import ru.bangerok.enterprise.ninja.service.AuthService;
import ru.bangerok.enterprise.ninja.service.MailService;

/**
 * <p> Listener-class for working with sending notifications to registering users about the need to
 * verify it by clicking on the link in the message. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.4.3
 */
@RequiredArgsConstructor
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

  private final AuthService userService;
  private final MailService mailService;

  @Override
  public void onApplicationEvent(@NonNull final OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  /**
   * Method for sending a message with a verification link to the mail.
   *
   * @param event user data event.
   */
  private void confirmRegistration(final OnRegistrationCompleteEvent event) {
    var user = event.getUser();
    var token = userService.createVerificationTokenForUser(user)
        .getValue();

    mailService.sendVerifiedMessage(user.getEmail(), token);
  }
}