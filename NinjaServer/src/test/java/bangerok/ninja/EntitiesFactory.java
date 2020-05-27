package bangerok.ninja;

import bangerok.ninja.domain.User;
import bangerok.ninja.dto.AuthProvider;
import java.util.Random;

public class EntitiesFactory {

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
