package ru.bangerok.ninja.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import ru.bangerok.ninja.controller.AuthController;

/**
 * Payload java класс для парсинга данных запроса регистрации в параметры rest метода.
 * <p>
 * Используется, например, здесь: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
public class SignUpRequest {

		/**
		 * Private поле, содержащее имя пользователя при регистрации с клиента.
		 */
		@NotBlank
		private String username;

		/**
		 * Private поле, содержащее электронную почту пользователя при регистрации с клиента.
		 */
		@NotBlank
		@Email
		private String email;

		/**
		 * Private поле, содержащее пароль пользователя при регистрации с клиента.
		 */
		@NotBlank
		private String password;
}
