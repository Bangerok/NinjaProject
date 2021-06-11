package ru.bangerok.ninja.rest.controllers.user;

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
import ru.bangerok.ninja.persistence.model.user.UserSetting;
import ru.bangerok.ninja.persistence.model.views.Views;
import ru.bangerok.ninja.security.CurrentUser;
import ru.bangerok.ninja.security.UserPrincipal;
import ru.bangerok.ninja.service.UserService;
import ru.bangerok.ninja.service.UserSettingService;

/**
 * Controller for receiving requests from the client related to user settings.
 *
 * @author v.kuznetsov
 * @since 0.5.8
 */
@RequiredArgsConstructor
@RolesAllowed("ROLE_USER")
@RestController
@RequestMapping("user/settings")
public class UserSettingsController {

  private final UserService userService;
  private final UserSettingService userSettingService;

  /**
   * Rest request method called by the client to get the user's application settings.
   *
   * @param userPrincipal entity stored in authentication.
   * @return {@link List<UserSetting>} or empty.
   */
  @GetMapping
  @JsonView(Views.UserSettingsData.class)
  public List<UserSetting> list(@CurrentUser UserPrincipal userPrincipal) {
    return userSettingService.getAll(userService.getCurrentUser(userPrincipal));
  }

  /**
   * Rest request method called by the client to save the new setting of the user's application.
   *
   * @param userSetting   new setting data.
   * @param userPrincipal setting user.
   * @return new {@link UserSetting}.
   */
  @PostMapping
  @JsonView(Views.UserSettingsData.class)
  public UserSetting create(
      @RequestBody UserSetting userSetting,
      @CurrentUser UserPrincipal userPrincipal
  ) {
    return userSettingService.create(userSetting, userService.getCurrentUser(userPrincipal));
  }

  /**
   * Rest request method called by the client to update the setting of the user's application.
   *
   * @param userSettingFromDb old setting data.
   * @param userSetting       setting new data
   * @return updated {@link UserSetting}.
   */
  @PutMapping("/{id}")
  @JsonView(Views.UserSettingsData.class)
  public UserSetting update(
      @PathVariable("id") UserSetting userSettingFromDb,
      @RequestBody UserSetting userSetting
  ) {
    return userSettingService.update(userSettingFromDb, userSetting);
  }
}