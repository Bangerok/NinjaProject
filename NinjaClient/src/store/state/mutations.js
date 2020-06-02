/**
 * Список основных мутаций.
 */
export default {
  setOptionsNotification(state, payload) {
    state.notification.color = payload.color;
    state.notification.text = payload.text;
    state.notification.show = !state.notification.show;
  },
  setNotificationShow(state) {
    state.notification.show = !state.notification.show;
  },
  setLoading(state, payload) {
    state.loading = payload;
  },
}