package ru.bangerok.ninja.persistence.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.UserSetting;
import ru.bangerok.ninja.rest.controllers.user.UserSettingsController;

/**
 * Repository java class for communicating with the database and working with the user setting
 * entity.
 * <p>
 * Used for example here: {@link UserSettingsController}.
 *
 * @author v.kuznetsov
 * @since 0.5.8
 */
@Repository
public interface UserSettingRepository extends ReactiveCrudRepository<UserSetting, Long> {
		/**
		 * Getting a complete list of user settings.
		 *
		 * @param user linked user.
		 * @return Optional with found user settings.
		 */
		Flux<UserSetting> findAllByUser(User user);
}