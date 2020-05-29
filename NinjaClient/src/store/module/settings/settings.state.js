import mutations from "./settings.mutations";
import actions from "./settings.actions";
import getters from "./settings.getters";

let settingsModule = {
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

export default settingsModule