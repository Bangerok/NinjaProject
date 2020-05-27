package bangerok.ninja.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bangerok.ninja.domain.User;
import bangerok.ninja.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest extends AbstractRepositoryTest {

		@Autowired
		private UserRepository userRepository;

		@Test
		void checkingUserFindById() {
				User newUser = userRepository.save(factory.getNewUser());

				User userFoundById = userRepository.findById(newUser.getId()).orElse(null);
				assertNotNull(userFoundById);
				userRepository.delete(userFoundById);
		}

		@Test
		void checkingUserFindByEmail() {
				User newUser = userRepository.save(factory.getNewUser());

				User userFoundByEmail = userRepository.findByEmail(newUser.getEmail()).orElse(null);
				assertNotNull(userFoundByEmail);
				userRepository.delete(userFoundByEmail);
		}

		@Test
		void checkingUserFindByProviderId() {
				User newUser = userRepository.save(factory.getNewUser());

				User userFoundByProviderId = userRepository.findByProviderId(newUser.getProviderId()).orElse(null);
				assertNotNull(userFoundByProviderId);
				userRepository.delete(userFoundByProviderId);
		}

		@Test
		void checkingUserExistByEmail() {
				User newUser = userRepository.save(factory.getNewUser());

				boolean existsByEmail = userRepository.existsByEmail(newUser.getEmail());
				assertTrue(existsByEmail);
				userRepository.delete(newUser);
		}
}