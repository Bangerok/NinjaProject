package ru.bangerok.ninja.security.oauth2;

import static ru.bangerok.ninja.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import ru.bangerok.ninja.config.AppPropertiesConfig;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.security.error.BadRequestException;
import ru.bangerok.ninja.security.TokenProvider;
import ru.bangerok.ninja.util.CookieUtils;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Класс, используемый Spring Security при успешном выполнении аутентификации пользователя.
 * <p>
 * Подключается здесь: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

		private final TokenProvider tokenProvider;
		private final AppPropertiesConfig appProperties;
		private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

		@Autowired
		OAuth2AuthenticationSuccessHandler(TokenProvider tokenProvider,
				AppPropertiesConfig appProperties,
				HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
				this.tokenProvider = tokenProvider;
				this.appProperties = appProperties;
				this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
		}

		/**
		 * Метод, вызывающийся при успешной аутентификации пользователя, предназначенный для очистки
		 * ответа от лишних данных и делающий redirect на клиент.
		 *
		 * @param request        запрос.
		 * @param response       ответ запроса.
		 * @param authentication аутентификация.
		 */
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException {
				String targetUrl = determineTargetUrl(request, response, authentication);

				if (response.isCommitted()) {
						logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
						return;
				}

				clearAuthenticationAttributes(request, response);
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}

		/**
		 * Метод для получения целового redirect url.
		 *
		 * @param request        запрос.
		 * @param response       ответ запроса.
		 * @param authentication аутентификация.
		 * @return redirect ссылка.
		 */
		@Override
		protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) {
				Optional<String> redirectUri = CookieUtils
						.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
						.map(Cookie::getValue);

				if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
						throw new BadRequestException(
								"Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
				}

				String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

				String token = tokenProvider.createToken(authentication);

				return UriComponentsBuilder.fromUriString(targetUrl)
						.queryParam("token", token)
						.build().toUriString();
		}

		/**
		 * Метод для очистки запроса/ответа от лишних данных.
		 *
		 * @param request  запрос.
		 * @param response ответ запроса.
		 */
		protected void clearAuthenticationAttributes(HttpServletRequest request,
				HttpServletResponse response) {
				super.clearAuthenticationAttributes(request);
				httpCookieOAuth2AuthorizationRequestRepository
						.removeAuthorizationRequestCookies(request, response);
		}

		/**
		 * Метод для проверки целового redirect url на действительность.
		 *
		 * @param uri проверяемая ссылка.
		 * @return true, если ссылка действительная, иначе false.
		 */
		private boolean isAuthorizedRedirectUri(String uri) {
				URI clientRedirectUri = URI.create(uri);

				return appProperties.getOauth2().getAuthorizedRedirectUris()
						.stream()
						.anyMatch(authorizedRedirectUri -> {
								URI authorizedURI = URI.create(authorizedRedirectUri);

								return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
										&& authorizedURI.getPort() == clientRedirectUri.getPort();
						});
		}
}
