package ru.bangerok.enterprise.ninja.exception.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception class for handling authentication failure.
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.0
 */
public class Oauth2AuthenticationProcessingException extends AuthenticationException {

  public Oauth2AuthenticationProcessingException(String msg) {
    super(msg);
  }
}