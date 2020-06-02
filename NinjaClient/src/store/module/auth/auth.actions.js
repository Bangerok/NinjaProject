import authApi from "../../../api/service/auth.service";
import i18n from "./../../../i18/i18n";

const authActions = {
  async getCurrentUser({commit}) {
    await authApi.getUser().then(response => {
      commit(
          'setCurrentUser',
          response.data
      );
    });
  },
  async login({commit}, payload) {
    await authApi.login(payload).then(response => {
      localStorage.setItem("jwt-token", response.data.accessToken);
      document.location.replace(new URL(location.href).origin);
    }).catch(() => {
      let notificationSettings = {
        color: 'error',
        text: i18n.tc('errors.invalid.credential'),
      }
      commit('setOptionsNotification', notificationSettings, {root: true});
    });
  },
  async register({commit}, payload) {
    let notificationSettings = null;

    await authApi.register(payload).then(response => {
      notificationSettings = {
        color: 'success',
        text: i18n.tc(response.data.message),
      }
    }).catch((error) => {
      if (error.response.data.errors) {
        return Promise.reject(error);
      } else {
        notificationSettings = {
          color: 'error',
          text: i18n.tc(error.response.data.message),
        }
      }
    });

    if (notificationSettings) {
      commit('setOptionsNotification', notificationSettings, {root: true});
    }
  },
  async callLogout({commit}) {
    localStorage.removeItem("jwt-token");
    commit(
        'setCurrentUser',
        null
    );
    document.location.replace(new URL(location.href).origin);
  },
}

export default authActions