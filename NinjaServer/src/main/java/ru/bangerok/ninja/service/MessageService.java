package ru.bangerok.ninja.service;

import ru.bangerok.ninja.controller.AuthController;

/**
 * Service class for working with localization of messages on the server.
 * <p>
 * Used for example here: {@link AuthController}.
 *
 * @author v.kuznetsov
 * @since 0.4.4
 */
public interface MessageService {

		/**
		 * Method for getting a localized message.
		 *
		 * @param path message key.
		 * @return localized message.
		 */
		String getMessage(String path);

		/**
		 * Method for getting localized message with parameters.
		 *
		 * @param path message key.
		 * @param args message parameters.
		 * @return localized message.
		 */
		String getMessageWithArgs(String path, Object[] args);
}