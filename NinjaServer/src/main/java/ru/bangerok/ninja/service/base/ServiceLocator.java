package ru.bangerok.ninja.service.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.service.MailService;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.service.UserService;

/**
 * Service class to get the services you want.
 * <p>
 * Used for example here: {@link AuthController}.
 *
 * @author v.kuznetsov
 * @since 0.4.3
 */
@Service
@Data
@AllArgsConstructor
public class ServiceLocator {

		private final UserService userService;
		private final MailService mailService;
		private final MessageService messageService;
}