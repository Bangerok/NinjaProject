import Vue from 'vue'
import VueI18n from 'vue-i18n'
import messagesEn from "./i18nEn"
import messagesRu from "./i18nRu"

Vue.use(VueI18n);

/**
 * Объект для хранения данных локализации для различных языков.
 */
const messages = {
  en: messagesEn,
  ru: messagesRu
}

/**
 * Конфигурационный объект, используемый для локализации системы.
 */
export default new VueI18n({
  locale: 'ru',
  messages,
})