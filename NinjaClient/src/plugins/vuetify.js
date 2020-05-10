import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
  theme:{
    themes: {
      light: {
        primary: '#f3f4f7',
        secondary: '#ffffff',
        tertiary: '#e5e8ee',
      },
      dark: {
        primary: '#27282a',
        secondary: '#1c1d1f',
        tertiary: '#303132'
      }
    },
    dark: true
  }
});
