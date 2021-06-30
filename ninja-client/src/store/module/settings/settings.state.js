import mutations from "./settings.mutations";
import actions from "./settings.actions";
import getters from "./settings.getters";

/**
 * Settings module state.
 */
export default {
  namespaced: true,
  state: () => ({
    /**
     * Application user settings.
     */
    userSettings: null,
  }),
  mutations,
  actions,
  getters,
};