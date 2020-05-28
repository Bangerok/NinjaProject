package ru.bangerok.ninja.security.oauth2;

import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.util.CookieUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Класс, который предоставляет функциональность для хранения запроса авторизации в файлах cookie,
 * его получение и удаление.
 * <p>
 * Подключается здесь: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Component
public class HttpCookieOAuth2AuthorizationRequestRepository implements
		AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

		public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
		public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
		private static final int cookieExpireSeconds = 180;

		/**
		 * Метод для получения запроса авторизации из cookie обычного запроса.
		 *
		 * @param request запрос.
		 * @return запрос авторизации.
		 */
		@Override
		public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
				return CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
						.map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
						.orElse(null);
		}

		/**
		 * Метод для сохранения запроса авторизации.
		 *
		 * @param request запрос.
		 * @param response ответ запроса.
		 */
		@Override
		public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest,
				HttpServletRequest request, HttpServletResponse response) {
				if (authorizationRequest == null) {
						CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
						CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
						return;
				}

				CookieUtils.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
						CookieUtils.serialize(authorizationRequest), cookieExpireSeconds);
				String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
				if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
						CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin,
								cookieExpireSeconds);
				}
		}

		/**
		 * Метод для удаления запроса авторизации.
		 *
		 * @param request запрос.
		 * @return запрос авторизации после удаления.
		 */
		@Override
		public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
				return this.loadAuthorizationRequest(request);
		}

		/**
		 * Метод для удаления cookies авторизации.
		 *
		 * @param request  запрос.
		 * @param response ответ.
		 */
		public void removeAuthorizationRequestCookies(HttpServletRequest request,
				HttpServletResponse response) {
				CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
				CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
		}
}
