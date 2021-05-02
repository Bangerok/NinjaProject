package ru.bangerok.ninja.controller.payload.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.bangerok.ninja.controller.AuthController;

/**
 * Payload java class to send response from server in controller.
 * <p>
 * Used for example here: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @since 0.4.5
 */
@Getter
@Setter
@NoArgsConstructor
public class GenericResponse {

		/**
		 * Private field containing a message about the final result of the request.
		 */
		private String message;

		/**
		 * Private field containing any data to be processed on the client.
		 */
		private Object data;

		/**
		 * Private field containing query execution errors.
		 */
		private String error;

		public GenericResponse(String message) {
				this.message = message;
		}

		/**
		 * Formation of a response from the server after field validation.
		 *
		 * @param allErrors list of validation errors.
		 * @param error error name.
		 */
		public GenericResponse(List<ObjectError> allErrors, String error) {
				this.error = error;
				String temp = allErrors.stream().map(e -> {
						if (e instanceof FieldError) {
								return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\""
										+ e.getDefaultMessage() + "\"}";
						} else {
								return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e
										.getDefaultMessage() + "\"}";
						}
				}).collect(Collectors.joining(","));

				this.message = "[" + temp + "]";
		}
}