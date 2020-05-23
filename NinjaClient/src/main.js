import Vue from 'vue'
import App from './App.vue'
import i18n from "./i18/i18n";
import store from './store/store'
import router from './router/router'
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false;

new Vue({
  i18n,
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount('#app');