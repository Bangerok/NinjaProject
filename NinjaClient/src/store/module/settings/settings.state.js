import mutations from "./settings.mutations";
import actions from "./settings.actions";
import getters from "./settings.getters";

let settingsModule = {
  namespaced: true,
  state: () => ({
    loading: false,
    notification: {
      color: null,
      text: null,
      show: false,
    },
    navigation: {
      minVariant: false,
    },
  }),
  mutations,
  actions,
  getters,
}

export default settingsModule