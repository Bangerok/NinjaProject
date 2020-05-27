package ru.bangerok.ninja.security;

import ru.bangerok.ninja.domain.Privilege;
import ru.bangerok.ninja.domain.Role;
import ru.bangerok.ninja.domain.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserPrincipal implements OAuth2User, UserDetails {

		private final User user;
		private final Collection<? extends GrantedAuthority> authorities;
		private Map<String, Object> attributes;

		public UserPrincipal(User user,
				Collection<? extends GrantedAuthority> authorities) {
				this.user = user;
				this.authorities = authorities;
		}

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
				return true;
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
