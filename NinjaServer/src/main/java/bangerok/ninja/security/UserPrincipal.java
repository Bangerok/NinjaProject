package bangerok.ninja.security;

import bangerok.ninja.domain.Privilege;
import bangerok.ninja.domain.Role;
import bangerok.ninja.domain.User;
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

		private final Long id;
		private final String username;
		private final String password;
		private final Collection<? extends GrantedAuthority> authorities;
		private Map<String, Object> attributes;

		public UserPrincipal(Long id, String email, String password,
				Collection<? extends GrantedAuthority> authorities) {
				this.id = id;
				this.username = email;
				this.password = password;
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

				return new UserPrincipal(
						user.getId(),
						Optional.ofNullable(user.getUsername()).orElse(user.getEmail()),
						user.getPassword(),
						authorities
				);
		}

		public static UserPrincipal create(User user, Map<String, Object> attributes) {
				UserPrincipal userPrincipal = UserPrincipal.create(user);
				userPrincipal.setAttributes(attributes);
				return userPrincipal;
		}

		public Long getId() {
				return id;
		}

		@Override
		public String getPassword() {
				return password;
		}

		@Override
		public String getUsername() {
				return username;
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
				return String.valueOf(id);
		}
}
