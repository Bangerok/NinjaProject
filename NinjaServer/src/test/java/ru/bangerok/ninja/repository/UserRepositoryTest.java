package ru.bangerok.ninja.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bangerok.ninja.persistence.dao.base.RepositoryLocator;
import ru.bangerok.ninja.persistence.model.user.User;

/**
 * Class for testing the repository working with the User entity.
 *
 * @author v.kuznetsov
 * @since 0.3.9
 */
@SpringBootTest
public class UserRepositoryTest extends AbstractRepositoryTest {

		@Autowired
		private RepositoryLocator repositoryLocator;

		/**
		 * Method for testing the search for a user in the database by id.
		 */
		@Test
		void checkingUserFindById() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				User userFoundById = repositoryLocator.getUserRepository().findById(newUser.getId())
						.orElse(null);
				assertNotNull(userFoundById);
				repositoryLocator.getUserRepository().delete(userFoundById);
		}

		/**
		 * Method for testing a user search in a database by email.
		 */
		@Test
		void checkingUserFindByEmail() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				User userFoundByEmail = repositoryLocator.getUserRepository()
						.findByEmail(newUser.getEmail()).orElse(null);
				assertNotNull(userFoundByEmail);
				repositoryLocator.getUserRepository().delete(userFoundByEmail);
		}

		/**
		 * Method for testing user search in the database by providerId.
		 */
		@Test
		void checkingUserFindByProviderId() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				User userFoundByProviderId = repositoryLocator.getUserRepository()
						.findByProviderId(newUser.getProviderId()).orElse(null);
				assertNotNull(userFoundByProviderId);
				repositoryLocator.getUserRepository().delete(userFoundByProviderId);
		}

		/**
		 * Method for testing email availability check in database.
		 */
		@Test
		void checkingUserExistByEmail() {
				User newUser = repositoryLocator.getUserRepository().save(factory.getNewUser());

				boolean existsByEmail = repositoryLocator.getUserRepository()
						.existsByEmail(newUser.getEmail());
				assertTrue(existsByEmail);
				repositoryLocator.getUserRepository().delete(newUser);
		}
}