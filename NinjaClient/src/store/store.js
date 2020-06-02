import Vue from 'vue'
import Vuex from 'vuex'
import state from './state/state'
import mutations from './state/mutations'
import actions from './state/actions'
import getters from './state/getters'
import authModule from "./module/auth/auth.state";
import settingsModule from "./module/settings/settings.state";

Vue.use(Vuex);

/**
 * Конфигурация внутреннего хранилища системы.
 */
export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
    auth: authModule,
    settings: settingsModule,
  }
})