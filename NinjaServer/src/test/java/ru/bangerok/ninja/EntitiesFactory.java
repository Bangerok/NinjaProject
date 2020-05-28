package ru.bangerok.ninja;

import java.util.Random;
import ru.bangerok.ninja.domain.User;
import ru.bangerok.ninja.enumeration.AuthProvider;
import ru.bangerok.ninja.repository.AbstractRepositoryTest;

/**
 * Класс для создания тестовых экземпляров сущностей без сохранения в базу данных.
 * <p>
 * Используется, например, здесь: {@link AbstractRepositoryTest}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public class EntitiesFactory {

  /**
   * Метод для генерации пользователя для дальнейшего его удаления.
   *
   * @return сгенерированный пользователь.
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
