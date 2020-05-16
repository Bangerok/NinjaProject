import Vue from 'vue'
import Vuex from 'vuex'
import state from './state/index'
import mutations from './state/mutations'
import actions from './state/actions'
import getters from './state/getters'
import authModule from "./module/auth";

Vue.use(Vuex);

let store = new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
    auth: authModule
  }
})

export default store