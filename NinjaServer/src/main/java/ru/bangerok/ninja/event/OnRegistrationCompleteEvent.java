package ru.bangerok.ninja.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * Событие-класс, который запускается после успешной регистрации пользователя.
 *
 * Используется, например, здесь: {@link AuthController#registerUser(RegisterRequest)}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

		private final User user;
		private final String appUrl;

		public OnRegistrationCompleteEvent(User user, String appUrl) {
				super(user);
				this.user = user;
				this.appUrl = appUrl;
		}
}
