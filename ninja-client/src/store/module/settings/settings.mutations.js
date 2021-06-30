/**
 * Settings module mutation list.
 */
export default {
  /**
   * Saving all user settings.
   *
   * @param state settings module state.
   * @param userSettings saved user settings.
   */
  setUserSettings(state, userSettings) {
    state.userSettings = userSettings;
  },
  /**
   * Added new user setting.
   *
   * @param state settings module state.
   * @param userSetting new user setting.
   */
  addUserSetting(state, userSetting) {
    state.userSettings = [
      ...state.userSettings,
      userSetting,
    ];
  },
  /**
   * Update old user setting.
   *
   * @param state settings module state.
   * @param userSetting updated old user setting.
   */
  updateUserSetting(state, userSetting) {
    const updateIndex = state.userSettings.findIndex(
        item => item.id === userSetting.id
    );

    state.userSettings = [
      ...state.userSettings.slice(0, updateIndex),
      userSetting,
      ...state.userSettings.slice(updateIndex + 1),
    ];
  }
};