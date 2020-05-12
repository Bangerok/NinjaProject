import '@fortawesome/fontawesome-free/css/all.css';
import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
  icons: {
    iconfont: 'fa',
  },
  theme:{
    themes: {
      light: {
        navigation: '#f3f4f7',
        appbar: '#ffffff',
        tertiary: '#e5e8ee',
      },
      dark: {
        navigation: '#27282a',
        appbar: '#1c1d1f',
        tertiary: '#303132'
      }
    },
    dark: true
  }
});
