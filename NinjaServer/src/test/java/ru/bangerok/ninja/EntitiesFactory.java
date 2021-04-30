package ru.bangerok.ninja;

import java.util.Random;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.repository.AbstractRepositoryTest;

/**
 * Class for creating test instances of entities without saving to the database.
 * <p>
 * Used for example here: {@link AbstractRepositoryTest}.
 *
 * @author v.kuznetsov
 * @since 0.3.9
 */
public class EntitiesFactory {

		/**
		 * Method for generating a user for further deletion.
		 *
		 * @return generated user.
		 */
		public User getNewUser() {
				User user = new User();
				user.setPassword("TestUserPassword");
				user.setAuthProvider(AuthProvider.google);
				user.setProviderId("TestProviderId");
				user.setEmail(String.format("test.%d@example.com", getRandomNumber()));
				return user;
		}

		private int getRandomNumber() {
				return new Random().nextInt();
		}
}