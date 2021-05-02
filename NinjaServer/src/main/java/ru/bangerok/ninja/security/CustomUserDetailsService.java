package ru.bangerok.ninja.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.security.error.ResourceNotFoundException;

/**
 * A service class that allows you to get a user in some way.
 * <p>
 * Connects here: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @since 0.3.0
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

		private final RepositoryLocator repositoryLocator;

		/**
		 * Method to get create authenticated user based on user retrieved from database via email.
		 *
		 * @param email email.
		 * @return authenticated user.
		 */
		@Override
		public UserDetails loadUserByUsername(String email)
				throws UsernameNotFoundException {
				User user = repositoryLocator.getUserRepository().findByEmail(email)
						.orElseThrow(() ->
								new UsernameNotFoundException("User not found with email : " + email)
						);

				return UserPrincipal.create(user);
		}

		/**
		 * Method to get create authenticated user based on user retrieved from database by id.
		 *
		 * @param id user ID.
		 * @return authenticated user.
		 */
		public UserDetails loadUserById(Long id) {
				User user = repositoryLocator.getUserRepository().findById(id).orElseThrow(
						() -> new ResourceNotFoundException("User", "id", id)
				);

				return UserPrincipal.create(user);
		}
}