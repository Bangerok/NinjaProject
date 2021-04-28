package ru.bangerok.ninja.service;

import javax.mail.MessagingException;
import ru.bangerok.ninja.event.listener.RegistrationListener;

/**
 * Сервисный класс для работы с отправкой писем на электронную почту.
 * <p>
 * Используется, например, здесь: {@link RegistrationListener}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public interface MailService {

		/**
		 * Метод для отправки верификационного сообщения на электронную почту.
		 *
		 * @param toEmail куда отправляем.
		 * @param token   токен верификации, выданный пользователю для проверки электронной почты.
		 */
		void sendVerifiedMessage(String toEmail, String token) throws MessagingException;
}