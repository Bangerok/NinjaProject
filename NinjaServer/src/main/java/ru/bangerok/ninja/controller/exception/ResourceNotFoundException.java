package ru.bangerok.ninja.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс исключения для обработки 404 ошибки, связанной с получением ресурсов.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

		public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
				super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
		}
}
