import axios from 'axios'
import store from '../store/store'

const TOKEN_TYPE = "jwt-token"
const AUTH_TYPE = "oauth2"
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

  if (localStorage.getItem("oauth2")) {
    headers[AUTH_TYPE] = localStorage.getItem(AUTH_TYPE);
  }

  axiosConfig.headers = Object.assign(headers, additionalHeaders);

  if (params) {
    axiosConfig.params = params;
  }

  if (data) {
    axiosConfig.data = data;
  }

  store.commit(LOADING, true);

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
  return request('get', url, null, null, null)
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

export default {get, post}