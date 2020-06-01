import authApi from "../../../api/service/auth.service";

const authActions = {
  async getCurrentUser({commit}) {
    await authApi.getUser().then(response => {
      commit(
          'setCurrentUser',
          response && response.data !== '' ? response.data : null
      );
    });
  },
  async register({commit}, payload) {
    await authApi.register(payload).then(response => {
      commit(
          'setOptionsNotification', {
            color: 'success',
            text: response.data.message
          }, {
            root: true
          }
      );
      console.log(response.data)
    });
  },
  async callLogout() {
    await authApi.logout().then(() => {
      localStorage.removeItem("jwt-token");
      localStorage.removeItem("oauth2");
      document.location.replace(new URL(location.href).origin);
    });
  },
}

export default authActions