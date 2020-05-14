import Vue from 'vue'

let userService = Vue.resource('/auth/create-or-get-user');

const CSRF_TOKEN_OBJECT = document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`));
const CSRF_TOKEN = CSRF_TOKEN_OBJECT ? CSRF_TOKEN_OBJECT[1] : '';

const actions = {
  authorized() {
    document.location.replace('http://localhost:9000/login/google');
  },
  getUser() {
    return userService.get();
  },
  logout() {
    return Vue.http.post('/logout', {}, {
      headers: {
        'X-XSRF-TOKEN': CSRF_TOKEN
      }
    });
  }
}

export default actions