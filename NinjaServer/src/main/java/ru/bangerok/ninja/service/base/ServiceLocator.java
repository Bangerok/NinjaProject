package ru.bangerok.ninja.service.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.service.MailService;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.service.UserService;

/**
 * Сервисный класс для получения нужных сервисов.
 * <p>
 * Используется, например, здесь: {@link AuthController}
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Service
@Data
@AllArgsConstructor
public class ServiceLocator {

		private final UserService userService;
		private final MailService mailService;
		private final MessageService messageService;
}