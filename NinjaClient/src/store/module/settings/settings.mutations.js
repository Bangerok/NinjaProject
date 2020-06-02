/**
 * Список мутаций модуля Settings.
 */
export default {
  /**
   * Мутация сохранения флага для минимизации меню-навигации.
   */
  setMinVariant(state, payload) {
    localStorage.setItem('minVariant', payload);
    state.navigation.minVariant = payload;
  },
}