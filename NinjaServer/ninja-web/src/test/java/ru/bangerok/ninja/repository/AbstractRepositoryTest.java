package ru.bangerok.ninja.repository;

import javax.annotation.PostConstruct;
import ru.bangerok.ninja.EntitiesFactory;

/**
 * <p> An abstract base class for tests containing the initialization of the test data factory. </p>
 *
 * @author v.kuznetsov
 * @since 0.3.9
 */
public abstract class AbstractRepositoryTest {

  protected EntitiesFactory factory;

  @PostConstruct
  void init() {
    factory = new EntitiesFactory();
  }
}