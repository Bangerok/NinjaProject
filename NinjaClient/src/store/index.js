import Vue from 'vue'
import Vuex from 'vuex'
import state from '../state/index'
import mutations from './../api/mutations'
import actions from './../api/actions'

Vue.use(Vuex);

let store = new Vuex.Store({
  state: state,
  mutations: mutations,
  actions: actions,
  modules: {}
})

export default store
