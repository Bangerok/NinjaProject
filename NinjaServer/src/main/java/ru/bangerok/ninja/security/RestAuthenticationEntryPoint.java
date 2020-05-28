package ru.bangerok.ninja.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.bangerok.ninja.config.SecurityConfig;

/**
 * Класс, который вызывается, когда пользователь пытается получить доступ к защищенному ресурсу без
 * аутентификации. В этом случае мы просто возвращаем 401 Unauthorized response.
 * <p>
 * Подключается здесь: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

		private static final Logger logger = LoggerFactory
				.getLogger(RestAuthenticationEntryPoint.class);

		/**
		 * Метод для отправки ошибки авторизации на клиент.
		 *
		 * @param httpServletRequest  запрос.
		 * @param httpServletResponse ответ запроса.
		 */
		@Override
		public void commence(HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse,
				AuthenticationException e) throws IOException {
				logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
				httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						e.getLocalizedMessage());
		}
}
