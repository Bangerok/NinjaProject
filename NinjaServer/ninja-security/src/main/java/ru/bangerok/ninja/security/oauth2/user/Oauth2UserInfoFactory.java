package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.auth.Oauth2AuthenticationProcessingException;
import ru.bangerok.ninja.service.MessageService;

/**
 * <p> A factory class that returns an oAuth2 authorization object with attributes obtained from an
 * external provider. Extensible to work with multiple external providers, not just Google. </p>
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
   * @return {@link AbstractOauth2UserInfo} after oAuth2 authorization.
   * @throws Oauth2AuthenticationProcessingException if a non-Google provider is used for
   *                                                 authorization.
   */
  public AbstractOauth2UserInfo getOauth2UserInfo(AuthProvider registrationId,
                                                  Map<String, Object> attributes)
      throws Oauth2AuthenticationProcessingException {
    if (AuthProvider.GOOGLE.equals(registrationId)) {
      return new GoogleOauth2UserInfo(registrationId, attributes);
    } else {
      throw new Oauth2AuthenticationProcessingException(messageService.getMessageWithArgs(
          "auth.error.provider.not.supported", new Object[] {registrationId})
      );
    }
  }
}