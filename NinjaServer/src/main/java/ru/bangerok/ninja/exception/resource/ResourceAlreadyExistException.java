package ru.bangerok.ninja.exception.resource;

import ru.bangerok.ninja.rest.payload.request.RegisterRequest;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * An exception class to handle attempts to create an existing resource. Whether it's user, role,
 * and so on.
 * <p>
 * Used for example here: {@link UserServiceImpl#registerNewUserAccount(RegisterRequest)}.
 *
 * @author v.kuznetsov
 * @since 0.3.15
 */
public final class ResourceAlreadyExistException extends RuntimeException {

		public ResourceAlreadyExistException(final String message) {
				super(message);
		}
}