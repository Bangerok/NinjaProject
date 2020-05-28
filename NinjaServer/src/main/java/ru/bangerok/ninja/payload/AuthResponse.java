package ru.bangerok.ninja.payload;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.bangerok.ninja.controller.AuthController;

/**
 * Payload java класс для отправки ответа от сервера в контроллере с данными аутентификации
 * пользователя.
 * <p>
 * Используется, например, здесь: {@link AuthController#authenticateUser}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@RequiredArgsConstructor
public class AuthResponse {

		/**
		 * Private поле, содержащее токен аутентификации пользователя.
		 */
		private final String accessToken;

		/**
		 * Private поле, содержащее тип токена аутентификации пользователя.
		 */
		private String tokenType = "Bearer";
}
