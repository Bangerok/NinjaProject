import userSettingsApi from "../../../api/service/userSetting.service";

/**
 * Settings module actions list.
 */
export default {
  /**
   * Retrieving all user settings for an application.
   *
   * @param commit module mutation data.
   * @return {Promise<void>} for further processing if needed.
   */
  async getAllUserSettings({commit}) {
    let {data} = await userSettingsApi.getAll();
    commit('setUserSettings', data);
  },
  /**
   * Saving a user setting.
   *
   * @param commit module mutation data.
   * @param state module state data.
   * @param userSetting setting data.
   * @return {Promise<void>} for further processing if needed.
   */
  async saveUserSetting({commit, state}, userSetting) {
    const {data} = await userSettingsApi.create(userSetting);
    const index = state.userSettings.findIndex(item => item.id === data.id);

    if (index > -1) {
      commit('updateUserSetting', data);
    } else {
      commit('addUserSetting', data);
    }
  },
  /**
   * Update a user setting.
   *
   * @param commit module mutation data.
   * @param userSetting setting data.
   * @return {Promise<void>} for further processing if needed.
   */
  async updateUserSetting({commit}, userSetting) {
    const {data} = await userSettingsApi.update(userSetting);
    commit('updateUserSetting', data);
  },
};