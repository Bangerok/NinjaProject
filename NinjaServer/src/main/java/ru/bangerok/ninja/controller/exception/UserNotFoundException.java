package ru.bangerok.ninja.controller.exception;

import java.io.Serial;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Класс исключения для обработки ошибок отсутствия пользователя в базе данных.
 * <p>
 * Используется, например, здесь: {@link UserServiceImpl#getCurrentUser(UserPrincipal)}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public final class UserNotFoundException extends RuntimeException {

		@Serial
		private static final long serialVersionUID = 5861310537366287163L;

		public UserNotFoundException(final String message) {
				super(message);
		}
}