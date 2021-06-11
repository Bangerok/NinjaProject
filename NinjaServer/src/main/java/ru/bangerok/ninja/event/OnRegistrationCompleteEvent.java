package ru.bangerok.ninja.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.rest.controllers.auth.AuthController;
import ru.bangerok.ninja.rest.payload.request.RegisterRequest;

/**
 * An event-class that is triggered after successful user registration.
 * <p>
 * Used for example here: {@link AuthController#registerUser(RegisterRequest)}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

  private final User user;
  private final String appUrl;

  public OnRegistrationCompleteEvent(User user, String appUrl) {
    super(user);
    this.user = user;
    this.appUrl = appUrl;
  }
}