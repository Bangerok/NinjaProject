import mutations from "./auth.mutations";
import actions from "./auth.actions";
import getters from "./auth.getters";

let authModule = {
  namespaced: true,
  state: () => ({
    user: null,
  }),
  mutations,
  actions,
  getters,
}

export default authModule