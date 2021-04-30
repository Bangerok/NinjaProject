/**
 * List of basic mutations.
 */
export default {
  /**
   * Setting notification properties.
   *
   * @param state basic state.
   * @param payload new data.
   */
  setOptionsNotification(state, payload) {
    state.notification.color = payload.color;
    state.notification.text = payload.text;
    state.notification.show = !state.notification.show;
  },
  /**
   * Setting show/hide notification.
   *
   * @param state basic state.
   */
  setNotificationShow(state) {
    return state.notification.show = !state.notification.show;
  },
  /**
   * Setting to show / hide the status bar when making requests to the server.
   *
   * @param state basic state.
   * @param payload new data.
   */
  setLoading(state, payload) {
    return state.loading = payload;
  },
};