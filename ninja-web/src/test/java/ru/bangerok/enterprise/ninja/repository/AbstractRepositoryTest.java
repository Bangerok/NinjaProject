package ru.bangerok.enterprise.ninja.repository;

import javax.annotation.PostConstruct;
import ru.bangerok.enterprise.ninja.EntitiesFactory;

/**
 * <p> An abstract base class for tests containing the initialization of the test data factory. </p>
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.3.9
 */
public abstract class AbstractRepositoryTest {

  protected EntitiesFactory factory;

  @PostConstruct
  void init() {
    factory = new EntitiesFactory();
  }
}