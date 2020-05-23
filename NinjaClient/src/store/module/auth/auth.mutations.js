const authMutations = {
  setCurrentUser(state, payload) {
    state.user = payload;
  },
}

export default authMutations