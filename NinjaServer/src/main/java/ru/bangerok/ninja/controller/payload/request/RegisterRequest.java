package ru.bangerok.ninja.controller.payload.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;
import ru.bangerok.ninja.validation.annotation.ValidEmail;
import ru.bangerok.ninja.validation.annotation.ValidPassword;

/**
 * Payload java класс для парсинга данных запроса регистрации в параметры rest метода.
 * <p>
 * Используется, например, здесь: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@PasswordMatches(message = "{errors.invalid.matchingPassword}")
public class RegisterRequest {

		/**
		 * Private поле, содержащее имя пользователя при регистрации с клиента.
		 */
		@NotNull
		@Size(min = 1, message = "{size.userDto.firstName}")
		private String username;

		/**
		 * Private поле, содержащее электронную почту пользователя при регистрации с клиента.
		 */
		@NotNull
		@Size(min = 1, message = "{size.userDto.email}")
		@ValidEmail(message = "{errors.invalid.email}")
		private String email;

		/**
		 * Private поле, содержащее пароль пользователя при регистрации с клиента.
		 */
		@ValidPassword
		private String password;

		/**
		 * Private поле, содержащее повторный ввод пароля пользователя при регистрации с клиента.
		 */
		@NotNull
		@Size(min = 1)
		private String matchingPassword;
}
