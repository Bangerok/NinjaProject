package ru.bangerok.enterprise.ninja.rest.controllers.user;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bangerok.enterprise.ninja.persistence.model.user.UserSetting;
import ru.bangerok.enterprise.ninja.persistence.model.views.Views;
import ru.bangerok.enterprise.ninja.rest.controllers.user.dto.UserSettingDto;
import ru.bangerok.enterprise.ninja.rest.controllers.user.mapper.UserSettingsMapper;
import ru.bangerok.enterprise.ninja.security.CurrentUser;
import ru.bangerok.enterprise.ninja.security.UserPrincipal;
import ru.bangerok.enterprise.ninja.service.AuthService;
import ru.bangerok.enterprise.ninja.service.UserSettingService;

/**
 * Controller for receiving requests from the client related to user settings.
 *
 * @author Vladislav [Bangerok] Kuznetsov.
 * @since 0.5.8
 */
@RequiredArgsConstructor
@RolesAllowed("ROLE_USER")
@RestController
@RequestMapping("user/settings")
public class UserSettingsController {

  private final AuthService userService;
  private final UserSettingService userSettingService;
  private final UserSettingsMapper mapper;

  /**
   * Rest request method called by the client to get the user's application settings.
   *
   * @param userPrincipal entity stored in authentication.
   * @return list user settings or empty list.
   */
  @GetMapping
  @JsonView(Views.UserSettingsData.class)
  public List<UserSetting> list(@CurrentUser UserPrincipal userPrincipal) {
    return userSettingService.getAll(userService.getCurrentUser(userPrincipal));
  }

  /**
   * Rest request method called by the client to save the new setting of the user's application.
   *
   * @param userSettingDto new setting data.
   * @param userPrincipal  setting user.
   * @return new user setting.
   */
  @PostMapping
  @JsonView(Views.UserSettingsData.class)
  public UserSetting create(
      @RequestBody UserSettingDto userSettingDto,
      @CurrentUser UserPrincipal userPrincipal
  ) {
    return userSettingService.create(mapper.toEntity(userSettingDto),
        userService.getCurrentUser(userPrincipal));
  }

  /**
   * Rest request method called by the client to update the setting of the user's application.
   *
   * @param userSettingFromDb old setting data.
   * @param userSettingDto    setting new data
   * @return updated user setting.
   */
  @PutMapping("/{id}")
  @JsonView(Views.UserSettingsData.class)
  public UserSetting update(
      @PathVariable("id") UserSetting userSettingFromDb,
      @RequestBody UserSettingDto userSettingDto
  ) {
    return userSettingService.update(userSettingFromDb, mapper.toEntity(userSettingDto));
  }
}