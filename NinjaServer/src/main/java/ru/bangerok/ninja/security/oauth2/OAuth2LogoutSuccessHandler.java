package ru.bangerok.ninja.security.oauth2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.config.SecurityConfig;

/**
 * Class used by Spring Security when user successfully logout.
 * <p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.1
 */
@RequiredArgsConstructor
@Component
public class OAuth2LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements
    LogoutSuccessHandler {

  private final HttpCookieOAuth2AuthorizationRequestRepository
      httpCookieOAuth2AuthorizationRequestRepository;

  /**
   * Method called upon successful user logout, designed to remove the authorization cookie from
   * the response.
   *
   * @param request        request.
   * @param response       response.
   * @param authentication authentication.
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