package bangerok.ninja.repository;

import bangerok.ninja.EntitiesFactory;
import javax.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class AbstractRepositoryTest {

		protected EntitiesFactory factory;

		@PostConstruct
		void init() {
				factory = new EntitiesFactory();
		}
}
