package ru.bangerok.ninja.controller.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import ru.bangerok.ninja.controller.AuthController;

/**
 * Payload java класс для парсинга данных запроса авторизации в параметры rest метода.
 * <p>
 * Используется, например, здесь: {@link AuthController#authenticateUser}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
public class LoginRequest {

		/**
		 * Private поле, содержащее электронную почту пользователя при авторизации с клиента.
		 */
		@NotNull
		@NotEmpty
		private String email;

		/**
		 * Private поле, содержащее пароль пользователя при авторизации с клиента.
		 */
		@NotNull
		@NotEmpty
		private String password;
}
