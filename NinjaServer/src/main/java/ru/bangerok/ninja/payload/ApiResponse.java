package ru.bangerok.ninja.payload;

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

		private final boolean success;
		private final String message;
}
