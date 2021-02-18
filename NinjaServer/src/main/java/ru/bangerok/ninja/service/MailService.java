package ru.bangerok.ninja.service;

import java.util.Map;
import javax.mail.MessagingException;
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
		 * @param toEmail сформированный объект письма для отправки. Не null.
		 * @param token   токен верификации, выданный пользователю для проверки электронной почты.
		 */
		void sendVerifiedMessage(String toEmail, String token) throws MessagingException;

		/**
		 * Метод для отправки письма на электронную почту.
		 *
		 * @param to            кому отправляем.
		 * @param subject       тема сообщения.
		 * @param templateName  имя шаблона.
		 * @param templateModel данные для шаблона.
		 */
		void send(String to, String subject, String templateName, Map<String, Object> templateModel)
				throws MessagingException;
}
