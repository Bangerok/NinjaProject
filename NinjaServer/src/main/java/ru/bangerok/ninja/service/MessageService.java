package ru.bangerok.ninja.service;

import ru.bangerok.ninja.controller.AuthController;

/**
 * Сервисный класс для работы с локализацией сообщений на сервере.
 *
 * Используется, например, здесь: {@link AuthController}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public interface MessageService {

		/**
		 * Метод для получения локализованного сообщения.
		 *
		 * @param path ключ сообщения.
		 * @return локализованное сообщение.
		 */
		String getMessage(String path);

		/**
		 * Метод для получения локализованного сообщения с параметрами.
		 *
		 * @param path ключ сообщения.
		 * @param args параметры сообщения.
		 * @return локализованное сообщение.
		 */
		String getMessageWithArgs(String path, Object[] args);
}
