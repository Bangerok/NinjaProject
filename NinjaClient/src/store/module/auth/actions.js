import authApi from "./../../../api/authApi";

const authActions = {
  async getCurrentUser({commit}) {
    await authApi.getUser().then(response => {
      commit('setCurrentUser', response.data !== '' ? response.data : null);
    });
  },
  async callLogout() {
    await authApi.logout().then(() => {
      localStorage.removeItem("jwt-token");
      localStorage.removeItem("oauth2");
      const uri = new URL(location.href);
      document.location.replace(uri.origin);
    });
  },
}

export default authActions