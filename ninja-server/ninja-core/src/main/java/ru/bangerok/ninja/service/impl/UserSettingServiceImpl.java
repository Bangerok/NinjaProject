package ru.bangerok.ninja.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;
import ru.bangerok.ninja.persistence.dao.UserSettingRepository;
import ru.bangerok.ninja.persistence.model.user.User;
import ru.bangerok.ninja.persistence.model.user.UserSetting;
import ru.bangerok.ninja.service.MessageService;
import ru.bangerok.ninja.service.UserSettingService;

/**
 * <p> Service class (implementation) for working with user setting entity. </p>
 *
 * @author v.kuznetsov
 * @since 0.5.8
 */
@RequiredArgsConstructor
@Slf4j
@SessionScope
@Transactional
@Service
public class UserSettingServiceImpl implements UserSettingService {

  private final UserSettingRepository userSettingRepo;
  private final MessageService messageService;

  @Override
  public List<UserSetting> getAll(User user) {
    return userSettingRepo.findAllByUser(user);
  }

  @Override
  public UserSetting create(UserSetting userSetting, User user) {
    userSetting.setUser(user);
    configuringUserLanguage(userSetting);
    return userSettingRepo.save(userSetting);
  }

  @Override
  public UserSetting update(UserSetting userSettingFromDb, UserSetting userSetting) {
    userSettingFromDb.setValue(userSetting.getValue());
    configuringUserLanguage(userSettingFromDb);
    return userSettingRepo.save(userSettingFromDb);
  }

  private void configuringUserLanguage(UserSetting userSetting) {
    if (userSetting.getName().equals("locale")) {
      messageService.setLocale(userSetting.getValue());
    }
  }
}