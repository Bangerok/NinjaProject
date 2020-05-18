import authApi from "../../../api/auth.module";
import i18n from "./../../../i18/i18n";

const authActions = {
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
    }).catch((e) => {
      if (e.response.status === 500) {
        console.error(i18n.tc('errors.code500'));
      }
    });
  },
}

export default authActions