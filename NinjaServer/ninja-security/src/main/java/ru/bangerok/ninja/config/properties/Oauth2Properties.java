package ru.bangerok.ninja.config.properties;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * <p> Java class containing settings for oAuth2 authorization. </p>
 *
 * @author v.kuznetsov
 * @since 0.5.14
 */
@Getter
@Component
public class Oauth2Properties {

  /**
   * Private field that stores a list of redirect links used after oauth2 authorization.
   */
  private final List<String> authorizedRedirectUris =
      Collections.singletonList("http://localhost:3000");
}