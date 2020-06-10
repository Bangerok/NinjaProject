package ru.bangerok.ninja.service;

import javax.validation.constraints.NotNull;
import org.springframework.mail.SimpleMailMessage;
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
		 * Метод для генерации сообщения перед отправкой его на электронную почту.
		 *
		 * @param toEmail куда отправляем.
		 * @param subject тема сообщения
		 * @param message текст сообщения.
		 * @return объект сообщения для отправки на электронную почту.
		 */
		SimpleMailMessage constructEmailMessage(String toEmail, String subject, String message);

		/**
		 * Метод для генерации верификационного сообщения перед отправкой его на электронную почту.
		 *
		 * @param emailMessage сформированный объект письма для отправки. Не null.
		 * @param token        токен верификации, выданный пользователю для проверки электронной почты.
		 * @return обновленный объект сообщения для отправки на электронную почту.
		 */
		SimpleMailMessage configureVerifiedMessage(@NotNull SimpleMailMessage emailMessage,
				String token);

		/**
		 * Метод для генерации повторного верификационного сообщения перед отправкой его на электронную
		 * почту.
		 *
		 * @param emailMessage сформированный объект письма для отправки. Не null.
		 * @param newToken     повторный токен верификации, выданный пользователю для проверки
		 *                     электронной почты.
		 * @return обновленный объект сообщения для отправки на электронную почту.
		 */
		SimpleMailMessage configureResendVerifiedMessage(@NotNull SimpleMailMessage emailMessage,
				String newToken);

		/**
		 * Метод для отправки письма на электронную почту.
		 *
		 * @param emailMessage сформированный объект письма для отправки.
		 */
		void send(SimpleMailMessage emailMessage);
}
