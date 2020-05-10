import Vue from 'vue'
import Vuex from 'vuex'
import state from '../state/index'
import mutations from './../api/mutations'
import actions from './../api/actions'
import getters from './../api/getters'

Vue.use(Vuex);

let store = new Vuex.Store({
  state: state,
  mutations: mutations,
  actions: actions,
  getters: getters,
  modules: {}
})

export default store