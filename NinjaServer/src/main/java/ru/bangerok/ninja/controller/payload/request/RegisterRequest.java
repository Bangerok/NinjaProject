package ru.bangerok.ninja.controller.payload.request;

import javax.validation.constraints.NotNull;
import lombok.Data;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;
import ru.bangerok.ninja.validation.annotation.ValidEmail;
import ru.bangerok.ninja.validation.annotation.ValidPassword;
import ru.bangerok.ninja.validation.annotation.withoutImpl.Match;

/**
 * Payload java класс для парсинга данных запроса регистрации в параметры rest метода.
 * <p>
 * Используется, например, здесь: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Data
@PasswordMatches()
public class RegisterRequest {

		/**
		 * Private поле, содержащее имя пользователя при регистрации с клиента.
		 */
		@NotNull(message = "errors.invalid.empty.username")
		private String username;

		/**
		 * Private поле, содержащее электронную почту пользователя при регистрации с клиента.
		 */
		@NotNull(message = "errors.invalid.empty.email")
		@ValidEmail(message = "errors.invalid.email")
		private String email;

		/**
		 * Private поле, содержащее пароль пользователя при регистрации с клиента.
		 */
		@ValidPassword
		private String password;

		/**
		 * Private поле, содержащее повторный ввод пароля пользователя при регистрации с клиента.
		 */
		@Match(message = "errors.invalid.matchingPassword")
		private String matchingPassword;
}