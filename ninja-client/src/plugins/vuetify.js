import '@fortawesome/fontawesome-free/css/all.css';
import Vue from 'vue';
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

/**
 * A configuration object used to customize the display of components in the
 * system (display mode, color customization). Connecting the necessary icons.
 */
export default new Vuetify({
  icons: {
    iconfont: 'fa',
  },
  theme: {
    themes: {
      light: {
        sidebar: '#f3f4f7',
        toolbar: '#ffffff',
        content: '#e5e8ee',
      },
      dark: {
        sidebar: '#27282a',
        toolbar: '#1c1d1f',
        content: '#27292D',
      },
    },
    dark: true,
  },
});
