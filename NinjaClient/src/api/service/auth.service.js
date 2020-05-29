import axios from '../axios.api'

export default {
  getUser() {
    return axios.get('/auth/user');
  },
  login(payload) {
    return axios.post('/auth/login', payload);
  },
  signUp(payload) {
    return axios.post('/auth/signup', payload);
  },
  logout() {
    return axios.post('/logout');
  },
}