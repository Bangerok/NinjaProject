/**
 * Settings module mutation list.
 */
export default {
  /**
   * Setting a flag to minimize menu navigation.
   *
   * @param state settings module state.
   * @param payload new data.
   */
  setMinVariant(state, payload) {
    localStorage.setItem('minVariant', payload);
    state.navigation.minVariant = payload;
  },
};