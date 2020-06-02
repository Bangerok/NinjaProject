/**
 * Список мутаций модуля Auth.
 */
export default {
  /**
   * Мутация установки данных авторизованного пользователя.
   */
  setCurrentUser(state, payload) {
    state.user = payload;
  },
}