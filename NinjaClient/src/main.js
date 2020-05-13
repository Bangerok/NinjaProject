import Vue from 'vue'
import VueCookies from 'vue-cookies'
Vue.use(VueCookies);

import './api/resource'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import i18n from "./i18/i18n";
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    i18n,
    vuetify,
    render: h => h(App),
}).$mount('#app');