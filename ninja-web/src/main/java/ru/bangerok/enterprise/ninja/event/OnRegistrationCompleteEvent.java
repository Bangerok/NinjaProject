package ru.bangerok.enterprise.ninja.event;

import java.io.Serial;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.bangerok.enterprise.ninja.event.listener.RegistrationListener;
import ru.bangerok.enterprise.ninja.persistence.model.user.User;

/**
 * <p> An event-class that is triggered after successful user registration. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.4.3
 */
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

  @Serial
  private static final long serialVersionUID = -8907250014179158718L;
  private final User user;
  private final String appUrl;

  /**
   * Create a new {@code ApplicationEvent}.
   *
   * @param user   the object on which the event initially occurred or with
   *               which the event is associated (never {@code null}).
   * @param appUrl application link.
   * @see RegistrationListener
   */
  public OnRegistrationCompleteEvent(User user, String appUrl) {
    super(user);
    this.user = user;
    this.appUrl = appUrl;
  }
}