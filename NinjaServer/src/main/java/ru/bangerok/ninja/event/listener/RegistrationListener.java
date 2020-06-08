package ru.bangerok.ninja.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.event.OnRegistrationCompleteEvent;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.service.base.ServiceLocator;

/**
 * Прослушиватель-класс для работы с отправкой уведомлений регистрируюшимся пользователям о
 * необходимости ее верификации путем перехода по ссылке в сообщении.
 * <p>
 * Используется, например, здесь: {@link AuthController#registerUser(RegisterRequest)}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

		private final ServiceLocator serviceLocator;

		public RegistrationListener(ServiceLocator serviceLocator) {
				this.serviceLocator = serviceLocator;
		}

		@Override
		public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
				this.confirmRegistration(event);
		}

		/**
		 * Метод для отправки сообщения с ссылкой верификации на почту.
		 *
		 * @param event событие с данными пользователя.
		 */
		private void confirmRegistration(final OnRegistrationCompleteEvent event) {
				User user = event.getUser();
				String token = serviceLocator.getUserService().createVerificationTokenForUser(user)
						.getToken();

				SimpleMailMessage emailMessage = serviceLocator.getMailService()
						.constructEmailMessage(user.getEmail(), null, null);
				emailMessage = serviceLocator.getMailService()
						.configureVerifiedMessage(emailMessage, token);

				serviceLocator.getMailService().send(emailMessage);
		}
}
