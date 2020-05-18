import Vue from 'vue'
import Vuex from 'vuex'
import state from './state/index'
import mutations from './state/mutations'
import actions from './state/actions'
import getters from './state/getters'
import authModule from "./module/auth";
import settingsModule from "./module/settings";

Vue.use(Vuex);

let store = new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
    auth: authModule,
    settings: settingsModule,
  }
})

export default store