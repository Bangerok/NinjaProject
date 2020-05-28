package ru.bangerok.ninja.security.oauth2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.config.SecurityConfig;

/**
 * Класс, используемый Spring Security при успешном выполнении пользователем logout.
 * <p>
 * Подключается здесь: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Component
public class OAuth2LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements
		LogoutSuccessHandler {

		private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

		public OAuth2LogoutSuccessHandler(
				HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
				this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
		}

		/**
		 * Метод, вызывающийся при успешном logout пользователя, предназначенный для удаления cookie
		 * авторизации из ответа.
		 *
		 * @param request        запрос.
		 * @param response       ответ запроса.
		 * @param authentication аутентификация.
		 */
		@Override
		public void onLogoutSuccess(
				HttpServletRequest request,
				HttpServletResponse response,
				Authentication authentication) {
				httpCookieOAuth2AuthorizationRequestRepository
						.removeAuthorizationRequestCookies(request, response);
		}
}
