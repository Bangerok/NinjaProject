import axios from 'axios'

export default {
  authorized() {
    document.location.replace('http://localhost:9000/login/google');
  },
  getUser() {
    return axios.get('/api/auth/create-or-get-user');
  },
  logout() {
    const CSRF_TOKEN_OBJECT = document.cookie.match(
        new RegExp(`XSRF-TOKEN=([^;]+)`));

    const headers = {
      'X-XSRF-TOKEN': CSRF_TOKEN_OBJECT
          ? CSRF_TOKEN_OBJECT[1] : '',
      'Access-Control-Allow-Origin': '*'
    }

    return axios.post('/api/logout', headers);
  }
}