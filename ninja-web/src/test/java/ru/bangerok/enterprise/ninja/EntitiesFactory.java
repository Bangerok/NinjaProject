package ru.bangerok.enterprise.ninja;

import java.util.Random;
import ru.bangerok.enterprise.ninja.enumeration.AuthProvider;
import ru.bangerok.enterprise.ninja.persistence.model.user.User;

/**
 * <p> Class for creating test instances of entities without saving to the database. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.9
 */
public class EntitiesFactory {

  /**
   * Method for generating a user for further deletion.
   *
   * @return generated user.
   */
  public User getNewUser() {
    var user = new User();
    user.setFullname("Ivanov Ivan");
    user.setEmailVerified(false);
    user.setEmail(String.format("test.%d@example.com", getRandomNumber()));
    user.setAuthProvider(AuthProvider.LOCAL);
    return user;
  }

  private int getRandomNumber() {
    return new Random().nextInt();
  }
}