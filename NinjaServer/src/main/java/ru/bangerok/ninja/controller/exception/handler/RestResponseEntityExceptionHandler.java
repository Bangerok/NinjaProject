package ru.bangerok.ninja.controller.exception.handler;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.bangerok.ninja.controller.exception.user.UserAlreadyExistException;
import ru.bangerok.ninja.controller.exception.user.UserNotFoundException;
import ru.bangerok.ninja.controller.payload.response.GenericResponse;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.validation.impl.PasswordConstraintValidator;

/**
 * Exception java class for catching and handling all errors thrown by controllers.
 *
 * @author v.kuznetsov
 * @since 0.4.5
 */
@ControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler {

		private static final Logger logger = LoggerFactory.getLogger(PasswordConstraintValidator.class);
		private final MessageService messageService;

		/**
		 * Exception handle method for handling 400 error - Bad request, associated with not passing the
		 * validation of the transmitted data to the server.
		 *
		 * @param ex method-related exception.
		 * @return error response from the server.
		 */
		@ExceptionHandler({MethodArgumentNotValidException.class})
		public ResponseEntity<GenericResponse> handleBadRequest(MethodArgumentNotValidException ex) {
				logger.error(ex.getClass().getName());
				BindingResult result = ex.getBindingResult();
				GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(),
						result.getObjectName());

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.BAD_REQUEST);
		}

		/**
		 * Exception handle method for handling 401 errors - Unauthorized when the user uses invalid
		 * credentials.
		 *
		 * @param ex method-related exception.
		 * @return error response from the server.
		 */
		@ExceptionHandler({AuthenticationException.class})
		public ResponseEntity<Object> handleAuthentication(RuntimeException ex) {
				logger.error("Unauthorized. 401 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						messageService.getMessage("message.email.config.error"));

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.UNAUTHORIZED);
		}

		/**
		 * Exception handle method for handling 404 error - Not found, related to the fact that the user
		 * was not found under any circumstances.
		 *
		 * @param ex method-related exception.
		 * @return error response from the server.
		 */
		@ExceptionHandler({UserNotFoundException.class})
		public ResponseEntity<Object> handleUserNotFound(RuntimeException ex) {
				logger.error("Not found. 404 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						messageService.getMessage("message.userNotFound"));

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.NOT_FOUND);
		}

		/**
		 * Exception handle method for handling 409 error - Conflict related to the conflict of the
		 * specified email by the user when registering with another user that already exists in the
		 * database.
		 *
		 * @param ex method-related exception.
		 * @return error response from the server.
		 */
		@ExceptionHandler({UserAlreadyExistException.class})
		public ResponseEntity<Object> handleUserAlreadyExist(RuntimeException ex) {
				logger.error("Conflict. 409 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						messageService.getMessage("message.regError"));

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.CONFLICT);
		}

		/**
		 * Exception handle method for handling 500 error - Internal Server Error, with incorrect
		 * authentication of the email provider when sending notifications to users.
		 *
		 * @param ex method-related exception.
		 * @return error response from the server.
		 */
		@ExceptionHandler({MailAuthenticationException.class})
		public ResponseEntity<GenericResponse> handleMailAuth(RuntimeException ex) {
				logger.error("Internal Server Error. 500 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						ex.getLocalizedMessage());

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/**
		 * Exception handle method for handling all other server errors - Internal Server Error.
		 *
		 * @param ex method-related exception.
		 * @return error response from the server.
		 */
		@ExceptionHandler({Exception.class})
		public ResponseEntity<GenericResponse> handleInternal(RuntimeException ex) {
				logger.error("Internal Server Error. 500 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						ex.getLocalizedMessage());

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
		}
}