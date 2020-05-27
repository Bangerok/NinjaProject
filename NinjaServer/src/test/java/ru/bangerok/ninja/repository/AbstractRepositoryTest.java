package ru.bangerok.ninja.repository;

import javax.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bangerok.ninja.EntitiesFactory;

/**
 * Абстрактный базовый класс для тестов, содержащий инициализацию фабрики тестовых данных.
 * <p>
 * Используется, например, здесь: {@link UserRepositoryTest}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@SpringBootTest
public abstract class AbstractRepositoryTest {

		protected EntitiesFactory factory;

		@PostConstruct
		void init() {
				factory = new EntitiesFactory();
		}
}
