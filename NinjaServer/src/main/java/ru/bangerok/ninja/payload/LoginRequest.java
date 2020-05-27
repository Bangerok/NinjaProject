package ru.bangerok.ninja.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

		@NotBlank
		@Email
		private String email;

		@NotBlank
		private String password;
}
