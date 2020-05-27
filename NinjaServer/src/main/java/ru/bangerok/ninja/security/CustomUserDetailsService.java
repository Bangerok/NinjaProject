package ru.bangerok.ninja.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bangerok.ninja.config.SecurityConfig;
import ru.bangerok.ninja.domain.User;
import ru.bangerok.ninja.exception.ResourceNotFoundException;
import ru.bangerok.ninja.repo.UserRepository;

/**
 * Сервисный класс, который позволяет получить пользователя каким либо образом.
 * <p>
 * Подключается здесь: {@link SecurityConfig}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

		private final UserRepository userRepository;

		public CustomUserDetailsService(UserRepository userRepository) {
				this.userRepository = userRepository;
		}

		@Override
		@Transactional
		public UserDetails loadUserByUsername(String email)
				throws UsernameNotFoundException {
				User user = userRepository.findByEmail(email)
						.orElseThrow(() ->
								new UsernameNotFoundException("User not found with email : " + email)
						);

				return UserPrincipal.create(user);
		}

		@Transactional
		public UserDetails loadUserById(Long id) {
				User user = userRepository.findById(id).orElseThrow(
						() -> new ResourceNotFoundException("User", "id", id)
				);

				return UserPrincipal.create(user);
		}

		@Transactional
		public UserDetails loadUserByProviderId(String id) {
				User user = userRepository.findByProviderId(id).orElseThrow(
						() -> new ResourceNotFoundException("User not found with provider", "id", id)
				);

				return UserPrincipal.create(user);
		}
}