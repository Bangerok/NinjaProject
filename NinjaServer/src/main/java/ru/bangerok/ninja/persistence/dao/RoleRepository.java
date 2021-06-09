package ru.bangerok.ninja.persistence.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.bangerok.ninja.enumeration.Roles;
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
public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

		/**
		 * Method for finding a role by its name in the database.
		 *
		 * @param value role value.
		 * @return Mono with found role.
		 */
		Mono<Role> findByValue(Roles value);
}