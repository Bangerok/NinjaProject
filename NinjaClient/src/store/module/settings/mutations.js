const authMutations = {
  setMinVariant(state, payload) {
    localStorage.setItem('minVariant', payload);
    state.navigation.minVariant = payload;
  },
  setOptionsNotification(state, payload) {
    state.notification.color = payload.color;
    state.notification.text = payload.text;
    state.notification.show = !state.notification.show;
  },
  setNotificationShow(state) {
    state.notification.show = !state.notification.show;
  },
}

export default authMutations