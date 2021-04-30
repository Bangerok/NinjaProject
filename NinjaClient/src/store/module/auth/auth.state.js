import mutations from "./auth.mutations";
import actions from "./auth.actions";
import getters from "./auth.getters";

/**
 * Auth module state.
 */
export default {
  namespaced: true,
  state: () => ({
    /**
     * Current logged in user data.
     */
    user: null,
  }),
  mutations,
  actions,
  getters,
};