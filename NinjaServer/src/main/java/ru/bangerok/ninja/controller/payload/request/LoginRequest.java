package ru.bangerok.ninja.controller.payload.request;

import lombok.Getter;
import lombok.Setter;
import ru.bangerok.ninja.controller.AuthController;

/**
 * Payload java class for parsing authorization request data into rest method parameters.
 * <p>
 * Used for example here: {@link AuthController#authenticateUser}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@Getter
@Setter
public class LoginRequest {

		/**
		 * Private field containing the user's email when authorizing from the client.
		 */
		private String email;

		/**
		 * Private field containing the user's password when authorizing from the client.
		 */
		private String password;
}