import axios from '../axios.api'

/**
 * Отправка запросов в AuthController на сервере.
 */
export default {
  /**
   * Отправка запроса в rest-метод по адресу '/auth/user'.
   *
   * @return promise
   */
  getUser() {
    return axios.get('/auth/user');
  },
  /**
   * Отправка запроса в rest-метод по адресу '/auth/login'.
   *
   * @return promise
   */
  login(loginData) {
    return axios.post('/auth/login', loginData);
  },
  /**
   * Отправка запроса в rest-метод по адресу '/auth/registrationConfirm'.
   *
   * @return promise
   */
  register(registerData) {
    return axios.post('/auth/register', registerData);
  },
  /**
   * Отправка запроса в rest-метод по адресу '/auth/registrationConfirm'.
   *
   * @return promise
   */
  confirmEmail(token) {
    return axios.getWithParams('/auth/registrationConfirm', {
      token: token
    });
  },
}