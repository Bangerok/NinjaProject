package ru.bangerok.ninja.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p> Java class containing settings for generating JWT tokens. </p>
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Getter
@Component
public class JwtProperties {

  /**
   * Private field, which stores information about the secret of the token for its
   * generation.
   */
  @Value("${app.auth.tokenSecret:none}")
  private String tokenSecret;

  /**
   * Private field that stores the duration of the token.
   */
  @Value("${app.auth.tokenExpirationMsec:0}")
  private long tokenExpirationMsec;
}