package ru.bangerok.ninja.controller.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ApiResponse {

		/**
		 * Private поле, говорящее об успешности выполненного запроса.
		 */
		private final boolean success;

		/**
		 * Private поле, содержащее сообщение о конечном результате выполнения запроса.
		 */
		private final String message;
}
