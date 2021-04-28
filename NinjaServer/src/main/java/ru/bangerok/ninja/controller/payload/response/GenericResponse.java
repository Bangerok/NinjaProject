package ru.bangerok.ninja.controller.payload.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.bangerok.ninja.controller.AuthController;

/**
 * Payload java класс для отправки ответа от сервера в контроллере.
 * <p>
 * Используется, например, здесь: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class GenericResponse {

		/**
		 * Private поле, содержащее сообщение о конечном результате выполнения запроса.
		 */
		private String message;

		/**
		 * Private поле, содержащее какие либо данные для обработки на клиенте.
		 */
		private Object data;

		/**
		 * Private поле, содержащее ошибки выполнения запроса.
		 */
		private String error;

		public GenericResponse(String message) {
				super();
				this.message = message;
		}

		public GenericResponse(String message, String error) {
				super();
				this.message = message;
				this.error = error;
		}

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