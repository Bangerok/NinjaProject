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
  login(payload) {
    return axios.post('/auth/login', payload);
  },
  /**
   * Отправка запроса в rest-метод по адресу '/auth/register'.
   *
   * @return promise
   */
  register(payload) {
    return axios.post('/auth/register', payload);
  },
}