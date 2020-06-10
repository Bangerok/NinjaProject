package ru.bangerok.ninja.security.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс исключения для обработки 400 ошибки.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

		public BadRequestException(String message) {
				super(message);
		}
}
