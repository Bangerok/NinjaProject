import authApi from "../../../api/service/auth.service";
import i18n from "./../../../i18/i18n";
import router from './../../../router/router'

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
      commit('setCurrentUser', response.data);
    });
  },
  /**
   * Действие для отправки запроса на сервер для авторизации пользователя
   * по email и паролю. Получение токена аутентификации и запись его
   * в localStorage. Если авторизация не получилась, то вывод окна с ошибкой.
   */
  async login({commit}, loginRequestData) {
    await authApi.login(loginRequestData).then(({data}) => {
      localStorage.setItem("jwt-token", data.message);
      router.go(0);
    }).catch(() => {
      commit(
          'setOptionsNotification',
          {color: 'error', text: i18n.tc('errors.invalid.credential')},
          {root: true}
      );
    });
  },
  /**
   * Действие для отправки запроса на сервер для регистрации пользователя
   * по переданным в метод данных. Если регистрация не удалась, то вывод
   * окна с ошибкой.
   */
  async register({commit}, registerRequestData) {
    await authApi.register(registerRequestData).then(response => {
      commit(
          'setOptionsNotification',
          {color: 'success', text: response.data.message},
          {root: true}
      );

      return Promise.resolve(response);
    }).catch((error) => {
      if (error.response.data.errors) {
        return Promise.reject(error);
      } else {
        commit(
            'setOptionsNotification',
            {color: 'error', text: error.response.data.message},
            {root: true}
        );
      }
    });
  },
  /**
   * Действие для выполнения проверки указанной пользователем при регистрации
   * электронной почты путем отправки токена подтверждения на сервер для
   * проверки.
   */
  async confirmEmail({commit}, verifyToken) {
    await authApi.confirmEmail(verifyToken).then(({data}) => {
      if (!data.data) {
        commit(
            'setOptionsNotification',
            {color: 'success', text: data.message},
            {root: true}
        );
      } else {
        return Promise.reject(data);
      }
    });
  },
  /**
   * Действие для отправки на почту нового токена верификации электронной почты
   * взамен истекшего.
   */
  async reSendVeryficationTokenEmail({commit}, expiredVerifyToken) {
    await authApi.reSendVeryficationTokenEmail(expiredVerifyToken).then(({data}) => {
      commit(
          'setOptionsNotification',
          {color: 'success', text: data.message},
          {root: true}
      );
    });
  },
  /**
   * Действие для выполнения logout пользователя системы и перенаправление
   * на страницу с авторизацией.
   */
  callLogout({commit}) {
    commit('setCurrentUser', null);

    localStorage.removeItem("jwt-token");
    router.go(0);
  },
}