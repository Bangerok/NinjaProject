import Vue from 'vue'
import VueI18n from 'vue-i18n'
import messagesEn from "./i18nEn"
import messagesRu from "./i18nRu"

Vue.use(VueI18n);

const messages = {
  en: messagesEn,
  ru: messagesRu
}

const i18 = new VueI18n({
  locale: 'ru',
  messages,
})

export default i18