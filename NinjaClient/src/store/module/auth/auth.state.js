import mutations from "./auth.mutations";
import actions from "./auth.actions";
import getters from "./auth.getters";

/**
 * Конфигурация состояния для модуля Auth.
 */
export default {
  namespaced: true,
  state: () => ({
    user: null,
  }),
  mutations,
  actions,
  getters,
}