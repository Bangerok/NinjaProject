package ru.bangerok.ninja.security.oauth2.user;

import java.util.Map;

/**
 * <p> Class for user data obtained through Google authorization provider. </p>
 * Used for example here: {@link Oauth2UserInfoFactory}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public class GoogleOauth2UserInfo extends Oauth2UserInfo {

  public GoogleOauth2UserInfo(Map<String, Object> attributes) {
    super(attributes);
  }

  @Override
  public String getId() {
    return (String) attributes.get("sub");
  }

  @Override
  public String getName() {
    return (String) attributes.get("name");
  }

  @Override
  public String getEmail() {
    return (String) attributes.get("email");
  }

  @Override
  public String getImageUrl() {
    return (String) attributes.get("picture");
  }
}