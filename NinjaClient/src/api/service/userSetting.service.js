// noinspection JSValidateJSDoc

import axios from '../axios.api';

const END_POINT = '/user/settings';

/**
 * Sending requests to UserSettingsController on the server.
 */
export default {
  /**
   * Sending a get request to a rest method at '/user/settings'.
   *
   * @return {AxiosPromise} with user settings list.
   */
  getAll() {
    return axios.get(END_POINT);
  },
  /**
   * Sending a post request to a rest method.
   *
   * @param userSetting setting data.
   * @return {AxiosPromise} with created user setting.
   */
  create(userSetting) {
    return axios.post(END_POINT, userSetting);
  },
  /**
   * Sending a put request to a rest method.
   *
   * @param userSetting setting new data.
   * @return {AxiosPromise} with updated user setting.
   */
  update(userSetting) {
    return axios.put(`${END_POINT}/${userSetting.id}`, userSetting);
  },
};