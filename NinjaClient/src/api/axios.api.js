import axios from 'axios'
import store from '../store/store'

function request(method, url, params, data, additionalHeaders) {
  let axiosConfig = {
    method: method,
    url: '/api' + url
  }

  const headers = {
    'Content-Type': 'application/json',
  }

  if (localStorage.getItem("jwt-token")) {
    headers['Authorization'] = 'Bearer ' + localStorage.getItem("jwt-token");
  }

  if (localStorage.getItem("oauth2")) {
    headers['oauth2'] = localStorage.getItem("oauth2");
  }

  axiosConfig.headers = Object.assign(headers, additionalHeaders);

  if (params) {
    axiosConfig.params = params;
  }

  if (data) {
    axiosConfig.data = data;
  }

  if (headers) {
    axiosConfig.headers = headers;
  }

  store.commit('settings/setLoading', true);

  axios.interceptors.response.use(function (response) {
    store.commit('settings/setLoading', false);
    return Promise.resolve(response);
  }, function (error) {
    store.commit('settings/setLoading', false);
    console.log(error);
  });

  return axios(axiosConfig);
}

function get(url) {
  return request('get', url, null, null, null)
}

function post(url, data) {
  data = data || {}
  return request('post', url, null, data, null)
}

function put(url, data) {
  data = data || {}
  return request('put', url, null, data, null)
}

function remove(url) {
  return request('delete', url, null, null, null)
}

export default {get, post, put, remove, request}