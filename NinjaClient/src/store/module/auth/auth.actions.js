import authApi from "../../../api/service/auth.service";
import i18n from "./../../../i18/i18n";

/**
 * Список действий модуля Auth.
 */
export default {
  /**
   * Действие для отправки запроса на сервер для получения и сохранения
   * данных авторизованного пользователя.
   */
  async getCurrentUser({commit}) {
    await authApi.getUser().then(response => {
      commit(
          'setCurrentUser',
          response.data
      );
    });
  },
  /**
   * Действие для отправки запроса на сервер для авторизации пользователя
   * по email и паролю. Получение токена аутентификации и запись его
   * в localStorage. Если авторизация не получилась, то вывод окна с ошибкой.
   */
  async login({commit}, payload) {
    await authApi.login(payload).then(response => {
      localStorage.setItem("jwt-token", response.data.accessToken);
      document.location.replace(new URL(location.href).origin);
    }).catch(() => {
      let notificationSettings = {
        color: 'error',
        text: i18n.tc('errors.invalid.credential'),
      }
      commit('setOptionsNotification', notificationSettings, {root: true});
    });
  },
  /**
   * Действие для отправки запроса на сервер для регистрации пользователя
   * по переданным в метод данных. Если регистрация не удалась, то вывод
   * окна с ошибкой.
   */
  async register({commit}, payload) {
    let notificationSettings = null;

    await authApi.register(payload).then(response => {
      notificationSettings = {
        color: 'success',
        text: i18n.tc(response.data.message),
      }
    }).catch((error) => {
      if (error.response.data.errors) {
        return Promise.reject(error);
      } else {
        notificationSettings = {
          color: 'error',
          text: i18n.tc(error.response.data.message),
        }
      }
    });

    if (notificationSettings) {
      commit('setOptionsNotification', notificationSettings, {root: true});
    }
  },
  /**
   * Действие для выполнения logout пользователя системы и перенаправление
   * на страницу с авторизацией.
   */
  async callLogout({commit}) {
    localStorage.removeItem("jwt-token");
    commit(
        'setCurrentUser',
        null
    );
    document.location.replace(new URL(location.href).origin);
  },
}