/**
 * Settings module getter list.
 */
export default {
  /**
   * Getting user setting value by name.
   * @param state module setting state.
   * @return user setting value or null.
   */
  getSettingValueByName: (state) => (name) => {
    const userSetting = (state.userSettings || []).find(
        item => item.name === name
    );
    return (userSetting || {}).value || null;
  },
  /**
   * Getting user setting by name.
   * @param state module setting state.
   * @return user setting or null.
   */
  getSettingByName: (state) => (name) => {
    const userSetting = (state.userSettings || []).find(
        item => item.name === name
    );
    return (userSetting || {}) || null;
  }
};