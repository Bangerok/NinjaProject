package ru.bangerok.ninja.controller.exception.user;

import java.io.Serial;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Exception class for handling errors when a user is not in the database.
 * <p>
 * Used for example here: {@link UserServiceImpl#getCurrentUser(UserPrincipal)}.
 *
 * @author v.kuznetsov
 * @since 0.4.5
 */
public final class UserNotFoundException extends RuntimeException {

		@Serial
		private static final long serialVersionUID = 5861310537366287163L;

		public UserNotFoundException(final String message) {
				super(message);
		}
}