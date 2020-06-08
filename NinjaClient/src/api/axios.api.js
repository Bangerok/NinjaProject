import axios from 'axios'
import store from '../store/store'

const TOKEN_TYPE = "jwt-token"
const LOADING = "setLoading"

/**
 * Конфигурация axios.
 *
 * @return promise Axios
 */
function request(method, url, params, data, additionalHeaders) {
  let axiosConfig = {
    method: method,
    url: '/api' + url
  }

  const headers = {
    'Content-Type': 'application/json',
  }

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
 * Настройка метода GET для отправки на сервер.
 *
 * @return promise Axios
 */
function get(url) {
  return getWithParams(url, null)
}

/**
 * Настройка метода GET с параметрами для отправки на сервер.
 *
 * @return promise Axios
 */
function getWithParams(url, params) {
  return request('get', url, params, null, null)
}

/**
 * Настройка метода POST для отправки на сервер.
 *
 * @return promise Axios
 */
function post(url, data) {
  data = data || {}
  return request('post', url, null, data, null)
}

export default {get, getWithParams, post}