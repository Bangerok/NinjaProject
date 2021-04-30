/**
 * Auth module mutation list.
 */
export default {
  /**
   * Setting authorized user data.
   *
   * @param state auth module state.
   * @param payload new data.
   */
  setCurrentUser(state, payload) {
    return state.user = payload;
  },
};