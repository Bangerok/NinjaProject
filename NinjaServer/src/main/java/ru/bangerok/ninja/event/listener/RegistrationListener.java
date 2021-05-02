package ru.bangerok.ninja.event.listener;

import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.event.OnRegistrationCompleteEvent;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.service.base.ServiceLocator;

/**
 * Listener-class for working with sending notifications to registering users about the need to
 * verify it by clicking on the link in the message.
 * <p>
 * Used for example here: {@link AuthController#registerUser(RegisterRequest)}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@RequiredArgsConstructor
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

		private final ServiceLocator serviceLocator;

		@Override
		public void onApplicationEvent(@NonNull final OnRegistrationCompleteEvent event) {
				// TODO: изменить обработку ошибок
				try {
						this.confirmRegistration(event);
				} catch (MessagingException e) {
						e.printStackTrace();
				}
		}

		/**
		 * Method for sending a message with a verification link to the mail.
		 *
		 * @param event user data event.
		 */
		private void confirmRegistration(final OnRegistrationCompleteEvent event)
				throws MessagingException {
				User user = event.getUser();
				String token = serviceLocator.getUserService().createVerificationTokenForUser(user)
						.getToken();

				serviceLocator.getMailService().sendVerifiedMessage(user.getEmail(), token);
		}
}