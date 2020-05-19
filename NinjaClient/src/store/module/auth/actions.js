import authApi from "./../../../api/authApi";
import i18n from "./../../../i18/i18n";
import store from "./../../../store/index";

const authActions = {
  async getCurrentUser({commit}) {
    await authApi.getUser().then(response => {
      commit('setCurrentUser', response.data !== '' ? response.data : null);
    }).catch((e) => {
      if (e.response.status === 500) {
        store.commit('settings/setOptionsNotification', {
          color: 'error',
          text: i18n.tc('errors.code500')
        });
      }
    });
  },
}

export default authActions