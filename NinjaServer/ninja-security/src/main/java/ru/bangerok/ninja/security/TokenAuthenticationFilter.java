package ru.bangerok.ninja.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;

/**
 * <p> The class that is used to read the JWT authentication token from the request, validate it
 * and set the SecurityContext Spring Security if the token is valid. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private TokenProvider tokenProvider;
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  public void setTokenProvider(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Autowired
  public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
    this.customUserDetailsService = customUserDetailsService;
  }

  /**
   * Method for pre-checking the authentication data in the request before passing it further.
   *
   * @param request     request data.
   * @param response    response data.
   * @param filterChain chain of requests.
   */
  @Transactional
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    try {
      var jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        var id = tokenProvider.getUserIdFromToken(jwt);
        checkingAuthorization(request, id);
      }
    } catch (Exception e) {
      logger.error("Could not set user authentication in security context", e);
    }

    filterChain.doFilter(request, response);
  }

  /**
   * Checking user authorization by his id.
   *
   * @param request request data.
   * @param id      the user ID in the database, obtained from the JWT token after decryption.
   */
  private void checkingAuthorization(HttpServletRequest request, long id) {
    try {
      var userDetails = customUserDetailsService.loadUserById(id);
      var authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities()
      );
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (ResourceNotFoundException e) {
      SecurityContextHolder.getContext().setAuthentication(null);
    }
  }

  /**
   * Method for getting an authentication token from request headers.
   *
   * @param request request data.
   * @return a string with a token, if any, otherwise null.
   */
  private String getJwtFromRequest(HttpServletRequest request) {
    var bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}