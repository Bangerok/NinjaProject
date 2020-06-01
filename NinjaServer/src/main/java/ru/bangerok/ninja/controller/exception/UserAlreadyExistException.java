package ru.bangerok.ninja.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.service.impl.UserServiceImpl;

/**
 * Класс исключения для обработки ошибок регистрации и изменения данных пользователя, если даные уже
 * заняты.
 * <p>
 * Используется, например, здесь: {@link UserServiceImpl#registerNewUserAccount(RegisterRequest)}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class UserAlreadyExistException extends RuntimeException {

		public UserAlreadyExistException(final String message) {
				super(message);
		}
}
