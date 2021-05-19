package ru.bangerok.ninja.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.persistence.dao.UserRepository;
import ru.bangerok.ninja.persistence.dao.UserSettingRepository;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.UserSetting;
import ru.bangerok.ninja.service.UserSettingService;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserSettingServiceImpl implements UserSettingService {

		private final UserSettingRepository userSettingRepo;

		@Override
		public List<UserSetting> getAll(User user) {
				return userSettingRepo.findAllByUser(user);
		}

		@Override
		public UserSetting create(UserSetting userSetting, User user) {
				userSetting.setUser(user);
				return userSettingRepo.save(userSetting);
		}

		@Override
		public UserSetting update(UserSetting userSettingFromDb, UserSetting userSetting) {
				userSettingFromDb.setValue(userSetting.getValue());
				return userSettingRepo.save(userSettingFromDb);
		}
}