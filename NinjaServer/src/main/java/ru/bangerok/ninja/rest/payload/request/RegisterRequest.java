package ru.bangerok.ninja.rest.payload.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.bangerok.ninja.rest.controllers.auth.AuthController;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;
import ru.bangerok.ninja.validation.annotation.ValidEmail;
import ru.bangerok.ninja.validation.annotation.ValidPassword;
import ru.bangerok.ninja.validation.annotation.withoutImpl.Match;

/**
 * Payload java class for parsing registration request data into rest method parameters.
 * <p>
 * Used for example here: {@link AuthController#registerUser}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@Getter
@Setter
@PasswordMatches()
public class RegisterRequest {

		/**
		 * Private field containing the username when registering from a client.
		 */
		@NotNull(message = "errors.invalid.empty.username")
		private String name;

		/**
		 * Private field containing the user's email when registering from a client.
		 */
		@NotNull(message = "errors.invalid.empty.email")
		@ValidEmail(message = "errors.invalid.email")
		private String email;

		/**
		 * Private field containing the user's password when registering from a client.
		 */
		@ValidPassword
		private String password;

		/**
		 * Private field containing re-entering the user's password when registering from a client.
		 */
		@Match(message = "errors.invalid.matchingPassword")
		private String matchingPassword;
}