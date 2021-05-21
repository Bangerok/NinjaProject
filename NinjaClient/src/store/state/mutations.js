/**
 * List of basic mutations.
 */
export default {
  /**
   * Setting notification properties.
   *
   * @param state basic state.
   * @param notification new data.
   */
  setOptionsNotification(state, notification) {
    state.notification = notification;
  },
  /**
   * Setting show/hide notification.
   *
   * @param state basic state.
   */
  setNotificationShow(state) {
    state.notification.show = !state.notification.show;
  },
  /**
   * Setting to show/hide the status bar when making requests to the server.
   *
   * @param state basic state.
   * @param isLoading show/hide data.
   */
  setLoading(state, isLoading) {
    state.loading = isLoading;
  },
};