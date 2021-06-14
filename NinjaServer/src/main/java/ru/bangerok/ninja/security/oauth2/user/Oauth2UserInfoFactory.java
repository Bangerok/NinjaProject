package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.auth.Oauth2AuthenticationProcessingException;
import ru.bangerok.ninja.security.oauth2.CustomOauth2UserService;
import ru.bangerok.ninja.service.MessageService;

/**
 * <p> A factory class that returns an oAuth2 authorization object with attributes obtained from an
 * external provider. Extensible to work with multiple external providers, not just Google. </p>
 * Used for example here: {@link CustomOauth2UserService}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Component
public class Oauth2UserInfoFactory {

  private final MessageService messageService;

  /**
   * Static object method with user data for oAuth2 authorization.
   *
   * @param registrationId authorization provider type.
   * @param attributes     user attributes obtained from an external authorization provider
   * @return {@link Oauth2UserInfo} after oAuth2 authorization.
   * @throws Oauth2AuthenticationProcessingException if a non-Google provider is used for
   *                                                 authorization.
   */
  public Oauth2UserInfo getOauth2UserInfo(String registrationId, Map<String, Object> attributes)
      throws Oauth2AuthenticationProcessingException {
    if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
      return new GoogleOauth2UserInfo(attributes);
    } else {
      throw new Oauth2AuthenticationProcessingException(messageService.getMessageWithArgs(
          "auth.error.provider.not.supported", new Object[] {registrationId})
      );
    }
  }
}