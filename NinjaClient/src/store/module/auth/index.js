import mutations from "./mutations";
import actions from "./actions";
import getters from "./getters";

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