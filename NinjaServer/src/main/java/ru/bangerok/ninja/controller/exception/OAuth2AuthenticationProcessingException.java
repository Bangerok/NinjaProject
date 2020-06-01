package ru.bangerok.ninja.controller.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Класс исключения для обработки ошибки аутентификации.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {

		public OAuth2AuthenticationProcessingException(String msg) {
				super(msg);
		}
}
