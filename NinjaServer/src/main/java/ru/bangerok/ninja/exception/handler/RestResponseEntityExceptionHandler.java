package ru.bangerok.ninja.exception.handler;

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
import ru.bangerok.ninja.controller.payload.response.ApiResponse;
import ru.bangerok.ninja.exception.resource.ResourceAlreadyExistException;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
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

		/**
		 * Exception handle method for handling 400 error - Bad request, associated with not passing the
		 * validation of the transmitted data to the server.
		 *
		 * @param ex method-related exception.
		 * @return {@link ResponseEntity<ApiResponse>} from the server.
		 */
		@ExceptionHandler({MethodArgumentNotValidException.class})
		public ResponseEntity<ApiResponse> handleBadRequest(MethodArgumentNotValidException ex) {
				logger.error(ex.getClass().getName());
				BindingResult result = ex.getBindingResult();
				ApiResponse bodyOfResponse = new ApiResponse(result.getAllErrors(), result.getObjectName());
				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

		/**
		 * Exception handle method for handling 401 errors - Unauthorized when the user uses invalid
		 * credentials.
		 *
		 * @param ex method-related exception.
		 * @return {@link ResponseEntity<ApiResponse>} from the server.
		 */
		@ExceptionHandler({AuthenticationException.class})
		public ResponseEntity<ApiResponse> handleAuthentication(RuntimeException ex) {
				logger.error("401 - Unauthorized.", ex);
				return getResponseForSingleError(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		/**
		 * Exception handle method for handling 404 error - Not found, related to the fact that the user
		 * was not found under any circumstances.
		 *
		 * @param ex method-related exception.
		 * @return {@link ResponseEntity<ApiResponse>} from the server.
		 */
		@ExceptionHandler({ResourceNotFoundException.class})
		public ResponseEntity<ApiResponse> handleResourceNotFound(RuntimeException ex) {
				logger.error("404 - Not found.", ex);
				return getResponseForSingleError(ex.getMessage(), HttpStatus.NOT_FOUND);
		}

		/**
		 * Exception handle method for handling 409 error - Conflict related to the conflict of the
		 * specified email by the user when registering with another user that already exists in the
		 * database.
		 *
		 * @param ex method-related exception.
		 * @return {@link ResponseEntity<ApiResponse>} from the server.
		 */
		@ExceptionHandler({ResourceAlreadyExistException.class})
		public ResponseEntity<ApiResponse> handleResourceAlreadyExist(RuntimeException ex) {
				logger.error("409 - conflict.", ex);
				return getResponseForSingleError(ex.getMessage(), HttpStatus.CONFLICT);
		}

		/**
		 * Exception handle method for handling 500 error - Internal Server Error, with incorrect
		 * authentication of the email provider when sending notifications to users.
		 *
		 * @param ex method-related exception.
		 * @return {@link ResponseEntity<ApiResponse>} from the server.
		 */
		@ExceptionHandler({MailAuthenticationException.class})
		public ResponseEntity<ApiResponse> handleMailAuth(RuntimeException ex) {
				logger.error("500 - Internal Server Error.", ex);
				return getResponseForSingleError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/**
		 * Exception handle method for handling all other server errors - Internal Server Error.
		 *
		 * @param ex method-related exception.
		 * @return {@link ResponseEntity<ApiResponse>} from the server.
		 */
		@ExceptionHandler({Exception.class})
		public ResponseEntity<ApiResponse> handleInternal(RuntimeException ex) {
				logger.error("500 - Internal Server Error.", ex);
				return getResponseForSingleError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/**
		 * Formation of a response from the server with a description of the error.
		 *
		 * @param message    error message.
		 * @param statusCode error code.
		 * @return {@link ResponseEntity<ApiResponse>} from the server.
		 */
		private ResponseEntity<ApiResponse> getResponseForSingleError(String message,
				HttpStatus statusCode) {
				return new ResponseEntity<>(new ApiResponse(message), new HttpHeaders(), statusCode);
		}
}