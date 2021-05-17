// noinspection JSUnresolvedFunction

import authApi from "../../../api/service/auth.service";
import i18n from "./../../../i18/i18n";
import router from './../../../router/router';

/**
 * Auth module actions list.
 */
export default {
  /**
   * Sending a request to the server to receive and save the data of an
   * authorized user.
   *
   * @param commit module mutation data.
   * @return {Promise<void>} for further processing if needed.
   */
  async getCurrentUser({commit}) {
    const response = await authApi.getUser();
    console.log(response);
    commit('setCurrentUser', response.data);

    return response;
  },
  /**
   * Sending a request to the server for user authorization by email and password.
   * Obtaining an authentication token and writing it to localStorage.
   * If authorization failed, then displaying a window with an error.
   *
   * @param commit module mutation data.
   * @param loginRequestData authorization data.
   * @return {Promise<void>} for further processing if needed.
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
   * User registration according to the data passed to the method. If registration is not successful, then a window with an error will be displayed.
   *
   * @param commit module mutation data.
   * @param registerRequestData registration data.
   * @return {Promise<void>} for further processing if needed.
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
      if (error.response.data.error) {
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
   * Verification of the email specified by the user during registration
   * by sending a confirmation token to the server for verification.
   *
   * @param commit module mutation data.
   * @param verifyToken verification token.
   * @return {Promise<void>} for further processing if needed.
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
   * Sending a new email verification token to the mail to replace the expired
   * one.
   *
   * @param commit module mutation data.
   * @param expiredVerifyToken expired token.
   * @return {Promise<void>} for further processing if needed.
   */
  async reSendVerificationTokenEmail({commit}, expiredVerifyToken) {
    await authApi.reSendVerificationTokenEmail(expiredVerifyToken).then(
        ({data}) => {
          commit(
              'setOptionsNotification',
              {color: 'success', text: data.message},
              {root: true}
          );
        });
  },
  /**
   * Logging out the user and redirecting to the login page.
   *
   * @param commit module mutation data.
   */
  callLogout({commit}) {
    commit('setCurrentUser', null);

    localStorage.removeItem("jwt-token");
    router.go(0);
  },
};