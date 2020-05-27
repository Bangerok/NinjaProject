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

		@NotBlank
		private String username;

		@NotBlank
		@Email
		private String email;

		@NotBlank
		private String password;
}
