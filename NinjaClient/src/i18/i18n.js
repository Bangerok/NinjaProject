import Vue from 'vue';
import VueI18n from 'vue-i18n';
import messagesEn from "./i18nEn";
import messagesRu from "./i18nRu";

Vue.use(VueI18n);

/**
 * Object for storing localization data for different languages.
 */
const messages = {
  en: messagesEn,
  ru: messagesRu,
};

/**
 * Configuration object used to localize the system.
 */
export default new VueI18n({
  locale: 'en',
  messages,
});