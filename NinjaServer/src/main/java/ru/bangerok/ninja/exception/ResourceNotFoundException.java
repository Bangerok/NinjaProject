package ru.bangerok.ninja.exception;

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

		private final String resourceName;
		private final String fieldName;
		private final Object fieldValue;

		public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
				super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
				this.resourceName = resourceName;
				this.fieldName = fieldName;
				this.fieldValue = fieldValue;
		}

		public String getResourceName() {
				return resourceName;
		}

		public String getFieldName() {
				return fieldName;
		}

		public Object getFieldValue() {
				return fieldValue;
		}
}
