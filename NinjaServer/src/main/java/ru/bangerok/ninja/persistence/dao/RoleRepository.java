package ru.bangerok.ninja.persistence.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bangerok.ninja.rest.controllers.auth.AuthController;
import ru.bangerok.ninja.persistence.model.user.Role;

/**
 * Repository java class for communicating with the database and working with the role entity.
 * <p>
 * Used for example here: {@link AuthController}.
 *
 * @author v.kuznetsov
 * @since 0.3.3
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

		/**
		 * Method for finding a role by its name in the database.
		 *
		 * @param name role name.
		 * @return Optional with found role.
		 */
		Optional<Role> findByName(String name);
}