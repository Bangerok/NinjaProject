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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;

/**
 * The class that is used to read the JWT authentication token from the request, validate it and set
 * the SecurityContext Spring Security if the token is valid.
 * <p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

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
      String jwt = getJwtFromRequest(request);

      if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        long id = tokenProvider.getUserIdFromToken(jwt);

        try {
          UserDetails userDetails = customUserDetailsService.loadUserById(id);
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
          authentication
              .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (ResourceNotFoundException e) {
          SecurityContextHolder.getContext().setAuthentication(null);
        }
      }
    } catch (Exception e) {
      logger.error("Could not set user authentication in security context", e);
    }

    filterChain.doFilter(request, response);
  }

  /**
   * Method for getting an authentication token from request headers.
   *
   * @param request request data.
   * @return a string with a token, if any, otherwise null.
   */
  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}