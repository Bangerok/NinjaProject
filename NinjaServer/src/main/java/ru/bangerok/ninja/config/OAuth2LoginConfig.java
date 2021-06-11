package ru.bangerok.ninja.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

/**
 * Configuration java class for declaring and configuring a bean for oAuth2 login settings.
 *
 * @author v.kuznetsov
 * @since 0.5.5
 */
@Configuration
public class OAuth2LoginConfig {

  /**
   * Private variable containing the client ID.
   */
  @Value("${spring.security.oauth2.client.registration.google.clientId:clientId}")
  private String clientId;

  /**
   * Private variable containing the client secret.
   */
  @Value("${spring.security.oauth2.client.registration.google.clientSecret:clientSecret}")
  private String clientSecret;

  /**
   * Constructs and return an InMemoryClientRegistrationRepository using the provided parameters.
   */
  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
  }

  /**
   * Google provider configuration for authorization.
   *
   * @return a new ClientRegistration.
   */
  private ClientRegistration googleClientRegistration() {
    return ClientRegistration.withRegistrationId("google")
        .clientId(clientId)
        .clientSecret(clientSecret)
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
        .scope("profile", "email")
        .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
        .tokenUri("https://www.googleapis.com/oauth2/v4/token")
        .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
        .userNameAttributeName(IdTokenClaimNames.SUB)
        .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
        .clientName("Google")
        .build();
  }
}