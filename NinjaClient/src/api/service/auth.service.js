import axios from '../axios.api'

export default {
  getUser() {
    return axios.get('/auth/user');
  },
  login(payload) {
    return axios.post('/auth/login', payload);
  },
  register(payload) {
    return axios.post('/auth/register', payload);
  },
  logout() {
    return axios.post('/logout');
  },
}