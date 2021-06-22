package ru.bangerok.ninja.rest.payload.request;

import javax.validation.constraints.NotNull;
import ru.bangerok.ninja.validation.annotation.PasswordMatches;
import ru.bangerok.ninja.validation.annotation.ValidEmail;
import ru.bangerok.ninja.validation.annotation.ValidPassword;
import ru.bangerok.ninja.validation.annotation.single.Match;

/**
 * <p> Payload java record for parsing registration request data into rest method parameters. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@PasswordMatches()
public record RegisterRequest(
    // Private field containing the username when registering from a client.
    @NotNull(message = "errors.invalid.empty.username")
    String name,

    // Private field containing the user's email when registering from a client.
    @NotNull(message = "errors.invalid.empty.email")
    @ValidEmail(message = "errors.invalid.email")
    String email,

    // Private field containing the user's password when registering from a client.
    @ValidPassword
    String password,

    // Private field containing re-entering the user's password when registering from a client.
    @Match(message = "errors.invalid.matchingPassword")
    String matchingPassword) {
}