// noinspection JSValidateJSDoc

import axios from '../axios.api';

/**
 * Sending requests to AuthController on the server.
 */
export default {
  /**
   * Sending a request to a rest method at '/auth/user'.
   *
   * @return {AxiosPromise} with a response from the server.
   */
  getUser() {
    return axios.get('/auth/user');
  },
  /**
   * Sending a request to a rest method at '/auth/login'.
   *
   * @param loginData authorization data.
   * @return {AxiosPromise} with a response from the server.
   */
  login(loginData) {
    return axios.post('/auth/login', loginData);
  },
  /**
   * Sending a request to a rest method at '/auth/registrationConfirm'.
   *
   * @param registerData registration data.
   * @return {AxiosPromise} with a response from the server.
   */
  register(registerData) {
    return axios.post('/auth/register', registerData);
  },
  /**
   * Sending a request to a rest method at '/auth/registrationConfirm'.
   *
   * @param token the token sent to the user by email.
   * @return {AxiosPromise} with a response from the server.
   */
  confirmEmail(token) {
    return axios.getWithParams('/auth/registrationConfirm', {token});
  },
  /**
   * Sending a request to a rest method at '/auth/resendRegistrationToken'.
   *
   * @param oldToken expired token.
   * @return {AxiosPromise} with a response from the server.
   */
  reSendVerificationTokenEmail(oldToken) {
    return axios.getWithParams('/auth/resendRegistrationToken', {oldToken});
  },
};