package ru.bangerok.ninja.service;

import java.util.Collection;
import java.util.List;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.UserSetting;
import ru.bangerok.ninja.rest.controllers.user.UserSettingsController;

/**
 * Service class for working with user setting entity.
 * <p>
 * Used for example here: {@link UserSettingsController}.
 *
 * @author v.kuznetsov
 * @since 0.5.8
 */
public interface UserSettingService {

		/**
		 * Saving the new user application setting.
		 *
		 * @param user setting author.
		 * @return {@link List<UserSetting>} for a specific user application.
		 */
		List<UserSetting> getAll(User user);

		/**
		 * Saving the new user application setting.
		 *
		 * @param userSetting setting data.
		 * @param user        setting author.
		 * @return {@link UserSetting} for a specific user application.
		 */
		UserSetting create(UserSetting userSetting, User user);

		/**
		 * Saving the new user application setting.
		 *
		 * @param userSettingFromDb setting old data.
		 * @param userSetting       setting new data.
		 * @return updated {@link UserSetting}.
		 */
		UserSetting update(UserSetting userSettingFromDb, UserSetting userSetting);
}