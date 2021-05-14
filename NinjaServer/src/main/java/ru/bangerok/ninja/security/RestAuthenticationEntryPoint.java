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
 * The class that is called when a user tries to access a protected resource without authentication.
 * In this case, we just return 401 Unauthorized response.
 * <p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

		private static final Logger logger = LoggerFactory
				.getLogger(RestAuthenticationEntryPoint.class);

		/**
		 * Method for sending authentication error to client.
		 *
		 * @param httpServletRequest  request.
		 * @param httpServletResponse response.
		 */
		@Override
		public void commence(HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
				logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
				httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						e.getLocalizedMessage());
		}
}