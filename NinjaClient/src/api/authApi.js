import axios from './axiosApi'

export default {
  getUser() {
    return axios.get('/api/auth/user');
  },
}