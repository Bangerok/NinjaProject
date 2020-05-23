import axios from './axiosApi'

export default {
  getUser() {
    return axios.get('/auth/user');
  },
  logout() {
    return axios.post('/logout');
  },
}