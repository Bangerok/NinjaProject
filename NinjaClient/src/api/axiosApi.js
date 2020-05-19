import axios from 'axios'
import store from './../store/index'

function request(method, url, params, data, headers) {
  let axiosConfig = {
    method: method,
    url: url,
  }

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
    return response;
  }, function (error) {
    store.commit('settings/setLoading', false);
    return Promise.reject(error);
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

function postWithCustomHeaders(url, headers) {
  return request('post', url, null, null, headers)
}

function put(url, data) {
  data = data || {}
  return request('put', url, null, data, null)
}

function remove(url) {
  return request('delete', url, null, null, null)
}

export default {get, post, postWithCustomHeaders, put, remove, request}