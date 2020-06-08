package ru.bangerok.ninja.controller;

import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bangerok.ninja.controller.payload.request.LoginRequest;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.controller.payload.response.ApiResponse;
import ru.bangerok.ninja.controller.payload.response.AuthResponse;
import ru.bangerok.ninja.event.OnRegistrationCompleteEvent;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.security.CurrentUser;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.base.ServiceLocator;

/**
 * Контроллер для получения запросов с клиента, связанные с авторизацией/аутентификацией
 * пользователей.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@RestController
@RequestMapping("auth")
public class AuthController {

		private final ServiceLocator serviceLocator;
		private final ApplicationEventPublisher eventPublisher;

		public AuthController(ServiceLocator serviceLocator, ApplicationEventPublisher eventPublisher) {
				this.serviceLocator = serviceLocator;
				this.eventPublisher = eventPublisher;
		}

		/**
		 * Rest метод-запрос, вызывающийся с клиента для получения из базы данных информации об
		 * авторизованном пользователе. Перед отправкой ответа - проверяются права пользователя на
		 * доступ к сущности User.
		 *
		 * @param userPrincipal сущность, хранящаяся в аутентификации.
		 * @return текущий пользователь, если есть, иначе null.
		 */
		@PostAuthorize("hasPermission(returnObject, 'READ')")
		@GetMapping("/user")
		public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
				return serviceLocator.getUserService().getCurrentUser(userPrincipal);
		}

		/**
		 * Rest метод-запрос, вызывающийся с клиента для аутентификации пользователя по полученным
		 * данным.
		 *
		 * @param loginRequest данные необходимые для аутентификации пользователя.
		 * @return AuthResponse с токеном аутентификации.
		 */
		@PostMapping("/login")
		public AuthResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
				String token = serviceLocator.getUserService()
						.creatingTokenForAuthentificateUser(loginRequest);
				return new AuthResponse(token);
		}

		/**
		 * Rest метод-запрос, вызывающийся с клиента для регистрации пользователя и сохранении его в
		 * базу данных.
		 *
		 * @param registerRequest данные необходимые для регистрации пользователя.
		 * @return ApiResponse с информацией об успешной регистрации.
		 */
		@PostMapping("/register")
		public ApiResponse registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
				User registered = serviceLocator.getUserService().registerNewUserAccount(registerRequest);
				eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, ""));

				return new ApiResponse(
						true,
						serviceLocator.getMessageService().getMessage("register.success.ended")
				);
		}

		/**
		 * Rest метод-запрос, вызывающийся для верификации электронной почты зарегистрированного
		 * пользователя.
		 *
		 * @param token токен верификации пользователя.
		 * @return ApiResponse с информацией об успешной верификации или об ошибке.
		 */
		@GetMapping("/registrationConfirm")
		public ApiResponse confirmRegistration(@RequestParam("token") String token) {
				VerificationToken verificationToken = serviceLocator.getUserService()
						.getVerificationToken(token);
				if (verificationToken == null) {
						return new ApiResponse(
								false,
								serviceLocator.getMessageService().getMessage("register.error.token.invalid")
						);
				}

				if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
						return new ApiResponse(
								false,
								serviceLocator.getMessageService().getMessage("register.error.token.expired")
						);
				}

				User user = verificationToken.getUser();
				user.setEmailVerified(true);
				serviceLocator.getUserService().saveRegisteredUser(user);
				return new ApiResponse(
						true,
						serviceLocator.getMessageService().getMessage("register.success.verified.email")
				);
		}
}
