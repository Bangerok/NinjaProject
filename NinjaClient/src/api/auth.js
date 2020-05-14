import Vue from 'vue'

const CSRF_TOKEN_OBJECT = document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`));
const CSRF_TOKEN = CSRF_TOKEN_OBJECT ? CSRF_TOKEN_OBJECT[1] : '';

export default {
  authorized() {
    document.location.replace('http://localhost:9000/login/google');
  },
  getUser() {
    return Vue.resource('/api/auth/create-or-get-user').get();
  },
  logout() {
    Vue.http.headers.common['X-XSRF-TOKEN'] = CSRF_TOKEN;
    Vue.http.headers.common['Access-Control-Allow-Origin'] = '*';

    return Vue.http.post('/api/logout');
  }
}