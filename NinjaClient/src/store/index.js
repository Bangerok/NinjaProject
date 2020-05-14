import Vue from 'vue'
import Vuex from 'vuex'
import state from '../state/index'
import mutations from '../state/mutations'
import actions from '../state/actions'
import getters from '../state/getters'

Vue.use(Vuex);

let store = new Vuex.Store({
  state: state,
  mutations: mutations,
  actions: actions,
  getters: getters,
  modules: {}
})

export default store