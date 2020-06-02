import mutations from "./settings.mutations";
import actions from "./settings.actions";
import getters from "./settings.getters";

/**
 * Конфигурация состояния для модуля Settings.
 */
export default {
  namespaced: true,
  state: () => ({
    navigation: {
      minVariant: false,
    },
  }),
  mutations,
  actions,
  getters,
}