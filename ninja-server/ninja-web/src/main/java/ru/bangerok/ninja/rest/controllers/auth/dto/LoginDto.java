package ru.bangerok.ninja.rest.controllers.auth.dto;

/**
 * <p> Payload java record for parsing authorization request data into rest method parameters. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public record LoginDto(
    // Private field containing the user's email when authorizing from the client.
    String email,

    // Private field containing the user's password when authorizing from the client.
    String password) {
}