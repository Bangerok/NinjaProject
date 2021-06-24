/**
 * Settings module getter list.
 */
export default {
  /**
   * Getting user setting value by name.
   * @param state module setting state.
   * @return {(function(*): (string|boolean|null))|*} user setting value or null.
   */
  getSettingValueByName: (state) => (name) => {
    const userSetting = (state.userSettings || []).find(
        item => item.name === name
    );

    const value = (userSetting || {}).value || null;
    if (value !== null) {
      // noinspection JSCheckFunctionSignatures
      const boolValue = ["true", "false"].includes(value);
      if (boolValue) {
        // noinspection JSIncompatibleTypesComparison
        return value === "true";
      } else {
        return value;
      }
    }

    return null;
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