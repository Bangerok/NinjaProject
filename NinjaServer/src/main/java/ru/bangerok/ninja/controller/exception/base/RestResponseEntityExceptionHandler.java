package ru.bangerok.ninja.controller.exception.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bangerok.ninja.controller.exception.UserAlreadyExistException;
import ru.bangerok.ninja.controller.exception.UserNotFoundException;
import ru.bangerok.ninja.controller.payload.response.GenericResponse;
import ru.bangerok.ninja.service.base.ServiceLocator;

/**
 * Exception java класс для для отлавливания и обработки всех ошибок, бросаемых контроллерами.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@ControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

		private final ServiceLocator serviceLocator;

		/**
		 * Exception handle метод для обработки 400 ошибки - Bad request, связанной с несовпадением
		 * передаваемых параметров с ожидающимися на стороне сервера.
		 *
		 * @param ex      связанное с методом исключение.
		 * @param headers заголовки запроса.
		 * @param status  статус выполнения запроса.
		 * @param request данные запроса
		 * @return ответ от сервера с ошибкой.
		 */
		@Override
		protected ResponseEntity<Object> handleBindException(BindException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
				logger.error("Bad request. 400 Status Code", ex);
				BindingResult result = ex.getBindingResult();
				GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(),
						"Invalid" + result.getObjectName());

				return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
						HttpStatus.BAD_REQUEST, request);
		}

		/**
		 * Exception handle метод для обработки 400 ошибки - Bad request, связанной с непрохождением
		 * валидации передающихся данных на сервер.
		 *
		 * @param ex      связанное с методом исключение.
		 * @param headers заголовки запроса.
		 * @param status  статус выполнения запроса.
		 * @param request данные запроса
		 * @return ответ от сервера с ошибкой.
		 */
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers,
				HttpStatus status, WebRequest request) {
				logger.error("Bad request. 400 Status Code", ex);
				BindingResult result = ex.getBindingResult();
				GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(),
						"Invalid" + result.getObjectName());

				return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
						HttpStatus.BAD_REQUEST, request);
		}

		/**
		 * Exception handle метод для обработки 401 ошибки - Unauthorized, когда пользователь использует
		 * неверные данные для авторизации.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
		 */
		@ExceptionHandler({AuthenticationException.class})
		public ResponseEntity<Object> handleAuthentication(RuntimeException ex, WebRequest request) {
				logger.error("Unauthorized. 401 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						serviceLocator.getMessageService().getMessage("message.email.config.error"));

				return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
						HttpStatus.UNAUTHORIZED,
						request
				);
		}

		/**
		 * Exception handle метод для обработки 404 ошибки - Not found, связанной с тем, что не найден
		 * пользователь при каких-либо обстоятельствах.
		 *
		 * @param ex      связанное с методом исключение.
		 * @param request данные запроса
		 * @return ответ от сервера с ошибкой.
		 */
		@ExceptionHandler({UserNotFoundException.class})
		public ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
				logger.error("Not found. 404 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						serviceLocator.getMessageService().getMessage("message.userNotFound"));

				return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND,
						request);
		}

		/**
		 * Exception handle метод для обработки 409 ошибки - Conflict, связанной с конфликтованием
		 * указанной электронной почты пользователем при регистрации с уже существующей в базе данных у
		 * другого пользователя.
		 *
		 * @param ex      связанное с методом исключение.
		 * @param request данные запроса
		 * @return ответ от сервера с ошибкой.
		 */
		@ExceptionHandler({UserAlreadyExistException.class})
		public ResponseEntity<Object> handleUserAlreadyExist(RuntimeException ex,
				WebRequest request) {
				logger.error("Conflict. 409 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						serviceLocator.getMessageService().getMessage("message.regError"));

				return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT,
						request);
		}

		/**
		 * Exception handle метод для обработки 500 ошибки - Internal Server Error, с некоректной
		 * аутентификацией email провайдера при отправке уведомлений пользователям.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
		 */
		@ExceptionHandler({MailAuthenticationException.class})
		public ResponseEntity<Object> handleMail(RuntimeException ex) {
				logger.error("Internal Server Error. 500 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						serviceLocator.getMessageService().getMessage("message.email.config.error"));

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/**
		 * Exception handle метод для обработки всех остальных 500 ошибок - Internal Server Error.
		 *
		 * @param ex связанное с методом исключение.
		 * @return ответ от сервера с ошибкой.
		 */
		@ExceptionHandler({Exception.class})
		public ResponseEntity<Object> handleInternal(RuntimeException ex) {
				logger.error("500 Status Code", ex);
				GenericResponse bodyOfResponse = new GenericResponse(
						serviceLocator.getMessageService().getMessage("message.error"));

				return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(),
						HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
