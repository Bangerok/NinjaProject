const authMutations = {
  setMinVariant(state, payload) {
    localStorage.setItem('minVariant', payload);
    state.navigation.minVariant = payload;
  },
}

export default authMutations