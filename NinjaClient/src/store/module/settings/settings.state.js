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
     * Navigation menu settings.
     */
    navigation: {
      /**
       * Collapse/Expand the menu
       */
      minVariant: false,
    },
  }),
  mutations,
  actions,
  getters,
};