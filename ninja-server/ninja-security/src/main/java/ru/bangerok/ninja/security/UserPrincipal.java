package ru.bangerok.ninja.security;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * <p> A class that represents an authenticated Spring Security Principal. It contains the data
 * of the authenticated user. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {

  @Serial
  private static final long serialVersionUID = -4897652181513238464L;

  /**
   * Private field that stores the essence of the authenticated user.
   */
  private final User user;

  /**
   * Private field that stores the list of the authenticated user's rights.
   */
  private final Collection<? extends GrantedAuthority> authorities;

  /**
   * Private field that stores a list of attributes received from an external provider.
   */
  private transient Map<String, Object> attributes;

  /**
   * Static method to create and populate an authenticated user created in the usual way.
   *
   * @param user logged in user.
   * @return {@link UserPrincipal} authenticated user.
   */
  public static UserPrincipal create(User user) {
    var authorities = new ArrayList<GrantedAuthority>();
    for (var role : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority(role.getValue().getName()));
      for (var privilege : role.getPrivileges()) {
        authorities.add(new SimpleGrantedAuthority(privilege.getName()));
      }
    }

    return new UserPrincipal(user, authorities);
  }

  /**
   * Static method to create and populate an authenticated user created after authorization
   * through an external provider.
   *
   * @param user logged in user.
   * @return {@link UserPrincipal} authenticated user.
   */
  public static UserPrincipal create(User user, Map<String, Object> attributes) {
    var userPrincipal = UserPrincipal.create(user);
    userPrincipal.setAttributes(attributes);
    return userPrincipal;
  }

  public User getUser() {
    return user;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return Optional.ofNullable(user.getUsername()).orElse(user.getEmail());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return user.getEmailVerified();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getName() {
    return String.valueOf(user.getId());
  }
}