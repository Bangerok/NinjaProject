package ru.bangerok.ninja.persistence.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bangerok.ninja.controller.AuthController;
import ru.bangerok.ninja.persistence.model.user.Role;

/**
 * Repository java класс для связи с базой данных и работой с сущностью роли.
 * <p>
 * Используется, например, здесь: {@link AuthController}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

		/**
		 * Метод для поиска роли по ее имени в базе данных.
		 *
		 * @param name наименование роли.
		 * @return Optional с найденной ролью.
		 */
		Optional<Role> findByName(String name);
}
