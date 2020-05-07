const mutations = {
  setMinVariant(state, payload) {
    state.navigation.minVariant = payload
  },
  setOptionsNotification(state, payload) {
    state.notification.color = payload.color;
    state.notification.text = payload.text;
    state.notification.show = true;
  },
  setNotificationShow(state, payload) {
    state.notification.show = payload;
  },
  setVacancies(state, payload) {
    state.pages.vacancies = payload;
  },
}

export default mutations