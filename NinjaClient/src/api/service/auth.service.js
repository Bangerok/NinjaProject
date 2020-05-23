import axios from '../axios.api'

export default {
  getUser() {
    return axios.get('/auth/user');
  },
  logout() {
    return axios.post('/logout');
  },
}