import authApi from "./../api/auth";

const actions = {
  authorized() {
    authApi.authorized();
  },
  async getUser({ commit }) {
    await authApi.getUser().then(data => {
      commit('setCurrentUser', data.body !== "" ? data.body : null);
    });
  },
  async logout({ commit }) {
    const functionLogout = () => {
      commit('setCurrentUser', null);
    };

    await authApi.logout().then(functionLogout, functionLogout);
  },
}

export default actions