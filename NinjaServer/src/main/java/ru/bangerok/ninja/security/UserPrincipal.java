package ru.bangerok.ninja.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ru.bangerok.ninja.persistence.model.user.Privilege;
import ru.bangerok.ninja.persistence.model.user.Role;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * A class that represents an authenticated Spring Security Principal. It contains the data of the
 * authenticated user.
 * <p>
 * Used for example here: {@link CustomUserDetailsService#loadUserById(Long)}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
public class UserPrincipal implements OAuth2User, UserDetails {

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
		private Map<String, Object> attributes;

		public UserPrincipal(User user,
				Collection<? extends GrantedAuthority> authorities) {
				this.user = user;
				this.authorities = authorities;
		}

		/**
		 * Static method to create and populate an authenticated user created in the usual way.
		 *
		 * @param user logged in user.
		 * @return authenticated user.
		 */
		public static UserPrincipal create(User user) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				for (Role role : user.getRoles()) {
						authorities.add(new SimpleGrantedAuthority(role.getName()));
						for (Privilege privilege : role.getPrivileges()) {
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
		 * @return authenticated user.
		 */
		public static UserPrincipal create(User user, Map<String, Object> attributes) {
				UserPrincipal userPrincipal = UserPrincipal.create(user);
				userPrincipal.setAttributes(attributes);
				return userPrincipal;
		}

		public Long getId() {
				return user.getId();
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