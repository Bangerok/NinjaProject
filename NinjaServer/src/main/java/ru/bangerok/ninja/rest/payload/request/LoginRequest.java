package ru.bangerok.ninja.rest.payload.request;

import ru.bangerok.ninja.rest.controllers.auth.AuthController;

/**
 * Payload java record for parsing authorization request data into rest method parameters.
 * <p>
 * Used for example here: {@link AuthController#authenticateUser}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public record LoginRequest (
		// Private field containing the user's email when authorizing from the client.
		String email,

		// Private field containing the user's password when authorizing from the client.
		String password) {

}