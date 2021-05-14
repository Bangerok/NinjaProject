package ru.bangerok.ninja.security.oauth2;

import static ru.bangerok.ninja.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.config.properties.JwtProperties;
import ru.bangerok.ninja.exception.request.BadRequestException;
import ru.bangerok.ninja.security.TokenProvider;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.util.CookieUtils;

/**
 * Class used by Spring Security when user authentication succeeds.
 * <p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

		private final MessageService messageService;
		private final TokenProvider tokenProvider;
		private final JwtProperties jwtProperties;
		private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

		/**
		 * The method called upon successful user authentication, designed to clear the response from
		 * unnecessary data and redirect to the client.
		 *
		 * @param request        request.
		 * @param response       response.
		 * @param authentication authentication.
		 * @throws IOException         server redirect error..
		 * @throws BadRequestException unauthorized redirect URI.
		 */
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, BadRequestException {
				String targetUrl = determineTargetUrl(request, response, authentication);

				if (response.isCommitted()) {
						logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
						return;
				}

				clearAuthenticationAttributes(request, response);
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
		}

		/**
		 * Method for getting the target redirect URI.
		 *
		 * @param request        request.
		 * @param response       response.
		 * @param authentication authentication.
		 * @return redirect URI.
		 * @throws BadRequestException [not found/unauthorized] redirect URI.
		 */
		@Override
		protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws BadRequestException {
				Optional<String> redirectUri = CookieUtils
						.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
						.map(Cookie::getValue);

				if (redirectUri.isPresent()) {
						final String uri = redirectUri.get();
						if (!isAuthorizedRedirectUri(uri)) {
								throw new BadRequestException(messageService.getMessageWithArgs(
										"auth.error.unauthorized.uri", new Object[]{uri}
								));
						}
						String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
						String token = tokenProvider.createToken(authentication);
						return UriComponentsBuilder.fromUriString(targetUrl)
								.queryParam("token", token)
								.build().toUriString();
				}

				throw new BadRequestException(messageService.getMessage(
						"auth.error.not.found.unauthorized.uri"
				));
		}

		/**
		 * Method for clearing a request/response from unnecessary data.
		 *
		 * @param request  request.
		 * @param response response.
		 */
		protected void clearAuthenticationAttributes(HttpServletRequest request,
				HttpServletResponse response) {
				super.clearAuthenticationAttributes(request);
				httpCookieOAuth2AuthorizationRequestRepository
						.removeAuthorizationRequestCookies(request, response);
		}

		/**
		 * Method for checking the target redirect url for validity.
		 *
		 * @param uri verified link.
		 * @return true if the link is valid, otherwise false.
		 */
		private boolean isAuthorizedRedirectUri(String uri) {
				URI clientRedirectUri = URI.create(uri);
				return jwtProperties.getOauth2().getAuthorizedRedirectUris()
						.stream()
						.anyMatch(authorizedRedirectUri -> {
								URI authorizedURI = URI.create(authorizedRedirectUri);
								return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) &&
										authorizedURI.getPort() == clientRedirectUri.getPort();
						});
		}
}