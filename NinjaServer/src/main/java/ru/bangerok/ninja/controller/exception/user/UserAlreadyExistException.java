package ru.bangerok.ninja.controller.exception.user;

import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Exception class for handling registration errors and changing user data if the data is already
 * taken.
 * <p>
 * Used for example here: {@link UserServiceImpl#registerNewUserAccount(RegisterRequest)}.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
public final class UserAlreadyExistException extends RuntimeException {

		public UserAlreadyExistException(final String message) {
				super(message);
		}
}