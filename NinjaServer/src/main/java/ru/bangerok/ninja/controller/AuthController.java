package ru.bangerok.ninja.controller;

import java.time.LocalDateTime;
import javax.mail.MessagingException;
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
import ru.bangerok.ninja.controller.payload.response.GenericResponse;
import ru.bangerok.ninja.event.OnRegistrationCompleteEvent;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.VerificationToken;
import ru.bangerok.ninja.security.CurrentUser;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.base.ServiceLocator;

/**
 * Controller for receiving requests from the client related to user authorization/authentication.
 *
 * @author v.kuznetsov
 * @since 0.1.8
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
		 * Rest request method called from the client to retrieve information about the authorized user
		 * from the database. Before sending a response - the user's rights to access the User entity
		 * are checked.
		 *
		 * @param userPrincipal entity stored in authentication.
		 * @return the current user, if any, otherwise null.
		 */
		@PostAuthorize("hasPermission(returnObject, 'READ')")
		@GetMapping("/user")
		public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
				return serviceLocator.getUserService().getCurrentUser(userPrincipal);
		}

		/**
		 * Rest request method called from the client to authenticate the user by the received data.
		 *
		 * @param loginRequest data required for user authentication.
		 * @return GenericResponse with authentication token.
		 */
		@PostMapping("/login")
		public GenericResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
				String token = serviceLocator.getUserService()
						.creatingTokenForAuthUser(loginRequest);
				return new GenericResponse(token);
		}

		/**
		 * Rest request method called from the client to register a user and save it to the database.
		 *
		 * @param registerRequest data required for user registration.
		 * @return GenericResponse with information about successful registration.
		 */
		@PostMapping("/register")
		public GenericResponse registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
				User registered = serviceLocator.getUserService().registerNewUserAccount(registerRequest);
				eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, ""));

				return new GenericResponse(
						serviceLocator.getMessageService().getMessage("register.success.ended")
				);
		}

		/**
		 * Rest request method called to verify the email of the registered user.
		 *
		 * @param token user verification token.
		 * @return GenericResponse with information about successful verification or error.
		 */
		@GetMapping("/registrationConfirm")
		public GenericResponse confirmRegistration(@RequestParam("token") String token) {
				VerificationToken verificationToken = serviceLocator.getUserService()
						.getVerificationToken(token);
				if (verificationToken == null) {
						return new GenericResponse(
								serviceLocator.getMessageService().getMessage("register.error.token.invalid")
						);
				}

				if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
						GenericResponse genericResponse = new GenericResponse();
						genericResponse.setData(verificationToken.getToken());
						return genericResponse;
				}

				User user = verificationToken.getUser();
				user.setEmailVerified(true);
				serviceLocator.getUserService().saveRegisteredUser(user);
				return new GenericResponse(
						serviceLocator.getMessageService().getMessage("register.success.verified.email")
				);
		}

		/**
		 * Rest request method that sends a new verification token to the user's email.
		 *
		 * @param existingToken expired user verification token.
		 * @return GenericResponse with information about sending a new token to the user's mail.
		 */
		@GetMapping("/resendRegistrationToken")
		public GenericResponse resendRegistrationToken(@RequestParam("oldToken") String existingToken)
				throws MessagingException {
				VerificationToken newToken = serviceLocator.getUserService()
						.generateNewVerificationToken(existingToken);

				User user = serviceLocator.getUserService().getUser(newToken.getToken());
				serviceLocator.getMailService().sendVerifiedMessage(user.getEmail(), newToken.getToken());

				return new GenericResponse(
						serviceLocator.getMessageService().getMessage("register.success.resend.token")
				);
		}
}