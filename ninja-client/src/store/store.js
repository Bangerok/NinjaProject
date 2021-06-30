import Vue from 'vue';
import Vuex from 'vuex';
import state from './state/state';
import mutations from './state/mutations';
import actions from './state/actions';
import getters from './state/getters';
import auth from "./module/auth/auth.state";
import settings from "./module/settings/settings.state";

Vue.use(Vuex);

/**
 * System internal storage configuration.
 */
export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters,
  modules: {
    auth,
    settings,
  },
});