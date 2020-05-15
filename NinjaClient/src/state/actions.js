import authApi from "../api/auth.module";

const actions = {
  googleAuth() {
    authApi.authorized();
  },
  async googleLogout({commit}) {
    const functionLogout = () => {
      authApi.getUser().then(response => {
        commit('setCurrentUser', response.data !== '' ? response.data : null);
      })
    };

    await authApi.logout().then(functionLogout, functionLogout);
  },
  async getCurrentUser({commit}) {
    await authApi.getUser().then(response => {
      commit('setCurrentUser', response.data !== '' ? response.data : null);
    });
  },
}

export default actions