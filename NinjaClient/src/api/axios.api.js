// noinspection JSValidateJSDoc

import axios from 'axios';
import store from '../store/store';

const TOKEN_TYPE = "jwt-token";
const LOADING = "setLoading";

/**
 * Axios configuration.
 *
 * @param method request type.
 * @param url request address.
 * @param params request parameters.
 * @param data request data for processing on the server.
 * @param additionalHeaders additional request headers.
 * @return {AxiosPromise} with a response from the server.
 */
function request(method, url, params, data, additionalHeaders) {
  let axiosConfig = {
    method: method,
    url: '/api' + url,
  };

  const headers = {
    'Content-Type': 'application/json',
  };

  if (localStorage.getItem(TOKEN_TYPE)) {
    headers['Authorization'] = 'Bearer ' + localStorage.getItem(TOKEN_TYPE);
  }

  axiosConfig.headers = Object.assign(headers, additionalHeaders);

  if (params) {
    axiosConfig.params = params;
  }

  if (data) {
    axiosConfig.data = data;
  }

  axios.interceptors.request.use(config => {
    store.commit(LOADING, true);
    return config;
  }, error => {
    store.commit(LOADING, false);
    return Promise.reject(error);
  });

  axios.interceptors.response.use(response => {
    store.commit(LOADING, false);
    return Promise.resolve(response);
  }, error => {
    store.commit(LOADING, false);
    return Promise.reject(error);
  });

  return axios(axiosConfig);
}

/**
 * Setting up the GET method for sending to the server.
 *
 * @param url request address.
 * @return {AxiosPromise} with a response from the server.
 */
function get(url) {
  return getWithParams(url, null);
}

/**
 * Setting up the GET method with parameters for sending to the server.
 *
 * @param url request address.
 * @param params request parameters.
 * @return {AxiosPromise} with a response from the server.
 */
function getWithParams(url, params) {
  return request('get', url, params, null, null);
}

/**
 * Setting up the POST method for sending to the server.
 *
 * @param url request address.
 * @param data request data for processing on the server.
 * @param params url params.
 * @return {AxiosPromise} with a response from the server.
 */
function post(url, data, params) {
  data = data || {};
  return request('post', url, params, data, null);
}

/**
 * Setting up the POST method for sending to the server.
 *
 * @param url request address.
 * @param data request data for processing on the server.
 * @param params url params.
 * @return {AxiosPromise} with a response from the server.
 */
function put(url, data, params) {
  data = data || {};
  return request('put', url, params, data, null);
}

export default {get, getWithParams, post, put};