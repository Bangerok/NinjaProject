package bangerok.ninja.security;


import bangerok.ninja.domain.User;
import bangerok.ninja.exception.ResourceNotFoundException;
import bangerok.ninja.repo.UserDetailsRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

		private final UserDetailsRepo userDetailsRepo;

		public CustomUserDetailsService(UserDetailsRepo userDetailsRepo) {
				this.userDetailsRepo = userDetailsRepo;
		}

		@Override
		@Transactional
		public UserDetails loadUserByUsername(String email)
				throws UsernameNotFoundException {
				User user = userDetailsRepo.findByEmail(email)
						.orElseThrow(() ->
								new UsernameNotFoundException("User not found with email : " + email)
						);

				return UserPrincipal.create(user);
		}

		@Transactional
		public UserDetails loadUserById(Long id) {
				User user = userDetailsRepo.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("User", "id", id)
				);

				return UserPrincipal.create(user);
		}

		@Transactional
		public UserDetails loadUserByProviderId(String id) {
				User user = userDetailsRepo.findByProviderId(id).orElseThrow(
						() -> new ResourceNotFoundException("User", "id", id)
				);

				return UserPrincipal.create(user);
		}
}