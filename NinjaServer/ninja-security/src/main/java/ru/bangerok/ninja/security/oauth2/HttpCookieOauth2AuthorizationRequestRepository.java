package ru.bangerok.ninja.security.oauth2;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.util.CookieUtils;

/**
 * <p> A class that provides functionality for storing an authorization request in cookies,
 * getting it, and deleting it. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@Component
public class HttpCookieOauth2AuthorizationRequestRepository implements
    AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

  public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
  public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
  private static final int COOKIE_EXPIRE_SECONDS = 180;

  /**
   * Method for getting authorization request from regular request cookie.
   *
   * @param request request.
   * @return {@link OAuth2AuthorizationRequest} derived from cookies.
   */
  @Override
  public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
    return CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
        .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
        .orElse(null);
  }

  /**
   * Method for saving authorization request.
   *
   * @param request  request.
   * @param response request response.
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
        CookieUtils.serialize(authorizationRequest), COOKIE_EXPIRE_SECONDS);
    String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
    if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
      CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin,
          COOKIE_EXPIRE_SECONDS);
    }
  }

  /**
   * Method for deleting an authorization request.
   *
   * @param request request.
   * @return {@link OAuth2AuthorizationRequest} authorization request after deletion.
   */
  @Override
  public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
    return this.loadAuthorizationRequest(request);
  }

  /**
   * Method for removing authorization cookies.
   *
   * @param request  request.
   * @param response response.
   */
  public void removeAuthorizationRequestCookies(HttpServletRequest request,
                                                HttpServletResponse response) {
    CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
    CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
  }
}