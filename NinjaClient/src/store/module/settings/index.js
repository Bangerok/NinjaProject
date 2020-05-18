import mutations from "./mutations";
import actions from "./actions";
import getters from "./getters";

let settingsModule = {
  namespaced: true,
  state: () => ({
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