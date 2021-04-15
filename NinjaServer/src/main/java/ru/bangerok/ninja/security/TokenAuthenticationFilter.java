package ru.bangerok.ninja.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.bangerok.ninja.config.SecurityConfig;

/**
 * Класс, который используется для чтения токена аутентификации JWT из запроса, его проверки и
 * установки SecurityContext Spring Security, если токен действителен.
 * <p>
 * Подключается здесь: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

		private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

		@Autowired
		private TokenProvider tokenProvider;

		@Autowired
		private CustomUserDetailsService customUserDetailsService;

		/**
		 * Метод для предварительной проверки аутентификационных данных в запросе перед пропуском его
		 * дальше.
		 *
		 * @param request     данные запроса.
		 * @param response    данные ответа.
		 * @param filterChain цепочка запросов.
		 */
		@Transactional
		@Override
		protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
				@NonNull FilterChain filterChain) throws ServletException, IOException {
				try {
						String jwt = getJwtFromRequest(request);

						if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
								long id = tokenProvider.getUserIdFromToken(jwt);

								UserDetails userDetails = customUserDetailsService.loadUserById(id);

								UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
										userDetails, null, userDetails.getAuthorities());
								authentication
										.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

								SecurityContextHolder.getContext().setAuthentication(authentication);
						}
				} catch (Exception ex) {
						logger.error("Could not set user authentication in security context", ex);
				}

				filterChain.doFilter(request, response);
		}

		/**
		 * Метод для получения из заголовков запроса токена аутентификации.
		 *
		 * @param request данные запроса.
		 * @return строка с токеном, если он есть, иначе null
		 */
		private String getJwtFromRequest(HttpServletRequest request) {
				String bearerToken = request.getHeader("Authorization");
				if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
						return bearerToken.substring(7);
				}
				return null;
		}
}
