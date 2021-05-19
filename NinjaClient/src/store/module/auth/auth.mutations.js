/**
 * Auth module mutation list.
 */
export default {
  /**
   * Setting authorized user data.
   *
   * @param state auth module state.
   * @param userData new data.
   */
  setCurrentUser(state, userData) {
    state.user = userData;
  },
};