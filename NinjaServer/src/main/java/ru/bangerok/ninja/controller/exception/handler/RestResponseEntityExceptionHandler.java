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
 * Exception java класс для для отлавливания и обработки всех ошибок, бросаемых контроллерами.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@ControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler {

		private static final Logger logger = LoggerFactory.getLogger(PasswordConstraintValidator.class);
		private final MessageService messageService;

		/**
		 * Exception handle метод для обработки 400 ошибки - Bad request, связанной с не прохождением
		 * валидации передающихся данных на сервер.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибками.
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
		 * Exception handle метод для обработки 401 ошибки - Unauthorized, когда пользователь использует
		 * неверные данные для авторизации.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
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
		 * Exception handle метод для обработки 404 ошибки - Not found, связанной с тем, что не найден
		 * пользователь при каких-либо обстоятельствах.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
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
		 * Exception handle метод для обработки 409 ошибки - Conflict, связанной с конфликтом указанной
		 * электронной почты пользователем при регистрации с уже существующей в базе данных у другого
		 * пользователя.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
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
		 * Exception handle метод для обработки 500 ошибки - Internal Server Error, с некорректной
		 * аутентификацией email провайдера при отправке уведомлений пользователям.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
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
		 * Exception handle метод для обработки всех остальных серверных ошибок - Internal Server
		 * Error.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
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