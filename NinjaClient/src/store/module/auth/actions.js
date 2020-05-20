import authApi from "./../../../api/authApi";

const authActions = {
  async getCurrentUser({commit}) {
    await authApi.getUser().then(response => {
      commit('setCurrentUser', response.data !== '' ? response.data : null);
    });
  },
}

export default authActions