package ru.bangerok.ninja.controller;

import javax.validation.Valid;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bangerok.ninja.controller.payload.request.LoginRequest;
import ru.bangerok.ninja.controller.payload.request.RegisterRequest;
import ru.bangerok.ninja.controller.payload.response.ApiResponse;
import ru.bangerok.ninja.controller.payload.response.AuthResponse;
import ru.bangerok.ninja.persistence.model.User;
import ru.bangerok.ninja.security.CurrentUser;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.UserService;

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

		private final UserService userService;

		public AuthController(UserService userService) {
				this.userService = userService;
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
				return userService.getCurrentUser(userPrincipal);
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
				String token = userService.creatingTokenForAuthenticateUser(loginRequest);
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
				User savedUser = userService.registerNewUserAccount(registerRequest);

				return new ApiResponse(
						true,
						"User " + savedUser.getUsername() + " registered successfully"
				);
		}
}
