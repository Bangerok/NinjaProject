package ru.bangerok.ninja.security.oauth2;

import static ru.bangerok.ninja.enumeration.Roles.ROLE_USER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.exception.auth.Oauth2AuthenticationProcessingException;
import ru.bangerok.ninja.exception.resource.ResourceNotFoundException;
import ru.bangerok.ninja.persistence.dao.RoleRepository;
import ru.bangerok.ninja.persistence.dao.UserRepository;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.security.oauth2.user.AbstractOauth2UserInfo;
import ru.bangerok.ninja.security.oauth2.user.Oauth2UserInfoFactory;
import ru.bangerok.ninja.service.MessageService;

/**
 * <p> A service class that updates or registers a new user using data received from an external
 * authorization provider. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CustomOauth2UserService extends DefaultOAuth2UserService {

  private final MessageService messageService;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final Oauth2UserInfoFactory userInfoFactory;

  /**
   * Method for getting user authentication after successful authorization from external
   * provider.
   *
   * @param oauth2UserRequest oauth2 authorization request.
   * @return {@link OAuth2User} after successful authorization.
   * @throws Oauth2AuthenticationProcessingException email not found from OAuth2 provider.
   * @throws ResourceNotFoundException               a role with this name was not found.
   */
  @Override
  public OAuth2User loadUser(OAuth2UserRequest oauth2UserRequest)
      throws Oauth2AuthenticationProcessingException, ResourceNotFoundException {
    var oauth2User = super.loadUser(oauth2UserRequest);
    return processOauth2User(oauth2UserRequest, oauth2User);
  }

  /**
   * Method for creating an authentication user after successful authorization from an external
   * provider.
   *
   * @param oauth2UserRequest oauth2 authorization request.
   * @param oauth2User        oauth2 user with data after authorization.
   * @return {@link OAuth2User} after creating it.
   * @throws Oauth2AuthenticationProcessingException email not found from OAuth2 provider.
   * @throws ResourceNotFoundException               a role with this name was not found.
   */
  private OAuth2User processOauth2User(OAuth2UserRequest oauth2UserRequest, OAuth2User oauth2User)
      throws Oauth2AuthenticationProcessingException, ResourceNotFoundException {
    var oauth2UserInfo = userInfoFactory
        .getOauth2UserInfo(
            AuthProvider.valueOf(
                oauth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()),
            oauth2User.getAttributes());

    final var email = oauth2UserInfo.getEmail();
    if (!StringUtils.hasText(email)) {
      throw new Oauth2AuthenticationProcessingException(messageService.getMessageWithArgs(
          "auth.error.email.not.found", new Object[] {email})
      );
    }

    var user = userRepository.findByEmail(email)
        .map(
            value -> updateExistingUser(value, oauth2UserInfo)
        ).orElseGet(
            () -> registerNewUser(oauth2UserInfo)
        );

    return UserPrincipal.create(user, oauth2User.getAttributes());
  }

  /**
   * Method for saving a new user to the database.
   *
   * @param abstractOauth2UserInfo oAuth2 user data.
   * @return registered {@link User}.
   * @throws ResourceNotFoundException a role with this name was not found.
   */
  private User registerNewUser(AbstractOauth2UserInfo abstractOauth2UserInfo)
      throws ResourceNotFoundException {
    var user = new User();
    user.setAuthProvider(abstractOauth2UserInfo.getProviderId());
    user.setProviderId(abstractOauth2UserInfo.getId());
    user.setFullname(abstractOauth2UserInfo.getName());
    user.setEmail(abstractOauth2UserInfo.getEmail());
    user.setAvatar(abstractOauth2UserInfo.getImageUrl());
    user.setLastVisit(LocalDateTime.now());
    user.setEmailVerified(true);

    var userRole = roleRepository.findByValue(ROLE_USER).orElseThrow(
        () -> new ResourceNotFoundException(messageService.getMessage("role.error.not.found"))
    );
    var roles = Stream.of(userRole)
        .collect(Collectors.toCollection(ArrayList::new));
    user.setRoles(roles);

    return userRepository.save(user);
  }

  /**
   * Method for updating a user in a database.
   *
   * @param existingUser           existing user.
   * @param abstractOauth2UserInfo oauth2 user data.
   * @return updated {@link User}.
   */
  private User updateExistingUser(User existingUser,
                                  AbstractOauth2UserInfo abstractOauth2UserInfo) {
    existingUser.setFullname(abstractOauth2UserInfo.getName());
    existingUser.setAvatar(abstractOauth2UserInfo.getImageUrl());
    existingUser.setLastVisit(LocalDateTime.now());
    return userRepository.save(existingUser);
  }
}