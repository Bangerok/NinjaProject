package ru.bangerok.ninja.controller.payload.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;
import ru.bangerok.ninja.validation.annotation.ValidEmail;

/**
 * Payload java класс для парсинга данных запроса регистрации в параметры rest метода.
 * <p>
 * Используется, например, здесь: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@PasswordMatches
public class RegisterRequest {

		/**
		 * Private поле, содержащее имя пользователя при регистрации с клиента.
		 */
		@NotNull
		@NotEmpty
		private String username;

		/**
		 * Private поле, содержащее электронную почту пользователя при регистрации с клиента.
		 */
		@ValidEmail
		@NotNull
		@NotEmpty
		private String email;

		/**
		 * Private поле, содержащее пароль пользователя при регистрации с клиента. Второе поле делает то
		 * же самое - повторение пароля.
		 */
		@NotNull
		@NotEmpty
		private String password;
		private String matchingPassword;
}
