package ru.bangerok.enterprise.ninja.security.oauth2;

import static ru.bangerok.enterprise.ninja.security.oauth2.HttpCookieOauth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.bangerok.enterprise.ninja.config.properties.JwtProperties;
import ru.bangerok.enterprise.ninja.exception.request.BadRequestException;
import ru.bangerok.enterprise.ninja.security.TokenProvider;
import ru.bangerok.enterprise.ninja.service.MessageService;
import ru.bangerok.enterprise.ninja.util.CookieUtils;

/**
 * <p> Class used by Spring Security when user authentication succeeds. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Component
public class Oauth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final MessageService messageService;
  private final TokenProvider tokenProvider;
  private final JwtProperties jwtProperties;
  private final HttpCookieOauth2AuthorizationRequestRepository
      httpCookieOauth2AuthorizationRequestRepository;

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
                                      Authentication authentication)
      throws IOException, BadRequestException {
    var targetUrl = determineTargetUrl(request, response, authentication);

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
    var redirectUri = CookieUtils
        .getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
        .map(Cookie::getValue);

    if (redirectUri.isPresent()) {
      final var uri = redirectUri.get();
      if (!isAuthorizedRedirectUri(uri)) {
        throw new BadRequestException(messageService.getMessageWithArgs(
            "auth.error.unauthorized.uri", new Object[] {uri}
        ));
      }
      var targetUrl = redirectUri.orElse(getDefaultTargetUrl());
      var token = tokenProvider.createToken(authentication);
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
    httpCookieOauth2AuthorizationRequestRepository
        .removeAuthorizationRequestCookies(request, response);
  }

  /**
   * Method for checking the target redirect url for validity.
   *
   * @param uri verified link.
   * @return true if the link is valid, otherwise false.
   */
  private boolean isAuthorizedRedirectUri(String uri) {
    var clientRedirectUri = URI.create(uri);
    return jwtProperties.getOauth2().getAuthorizedRedirectUris()
        .parallelStream()
        .anyMatch(authorizedRedirectUri -> {
          var authorizedUri = URI.create(authorizedRedirectUri);
          return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
              && authorizedUri.getPort() == clientRedirectUri.getPort();
        });
  }
}