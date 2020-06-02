<!--suppress HtmlUnknownTag -->
<template>
  <v-app-bar color="toolbar" app elevation="2" clipped-left>
    <v-card flat max-width="295px" max-height="100%" color="appbar" class="mt-n4 ml-n5">
      <v-list-item two-line>
        <v-list-item-avatar>
          <v-icon large color="orange darken-4">fa-bolt</v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ $t('navigation.companyName') }}</v-list-item-title>
          <v-list-item-subtitle>{{ $t('navigation.companyDescription') }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-card>

    <v-tooltip bottom nudge-right="60px">
      <template #activator="{ on }">
        <v-list-item-icon v-on="on" class="justify-center mt-1">
          <v-app-bar-nav-icon class="mr-3 ml-n2" @click="setMinVariant(!navigation.minVariant)">
            <v-icon v-if="navigation.minVariant">
              fa-bars
            </v-icon>

            <v-icon v-else>
              fa-ellipsis-v
            </v-icon>
          </v-app-bar-nav-icon>
        </v-list-item-icon>
      </template>
      <span class="overline">{{ $t('tooltips.minVariant') }}</span>
    </v-tooltip>

    <v-toolbar-title class="pl-2 font-weight-medium">
      {{ $t($route.meta.title) }}
    </v-toolbar-title>

    <v-spacer/>

    <v-btn text class="mr-3" link @click="callLogout">
      <v-icon class="mr-1">
        fa-sign-out-alt
      </v-icon>
      Выйти
    </v-btn>
    <v-tooltip bottom>
      <template #activator="{ on }">
        <v-list-item-icon v-on="on" class="justify-center mt-3">
          <v-card flat max-width="130px" max-height="40px" color="appbar">
            <v-select
                flat
                v-model="locale"
                :items="items"
                prepend-icon="fa-globe"
            />
          </v-card>
        </v-list-item-icon>
      </template>
      <span class="overline">{{ $t('tooltips.changeLanguageSystem') }}</span>
    </v-tooltip>

    <v-tooltip bottom nudge-top="10px">
      <template #activator="{ on }">
        <v-list-item-icon v-on="on" class="justify-center mt-0">
          <v-icon v-if="nightMode" class="mr-3 mt-n1">
            fa-sun
          </v-icon>
          <v-icon v-else class="mr-3 mt-n1">
            fa-moon
          </v-icon>
          <v-switch v-model="nightMode" inset :color="nightMode ? 'white' : ''" class="mt-4"/>
        </v-list-item-icon>
      </template>
      <span class="overline">{{ $t('tooltips.nightMode') }}</span>
    </v-tooltip>
  </v-app-bar>
</template>

<script>
  import {mapState, mapMutations, mapActions} from 'vuex';

  /**
   * Компонент для показа верхнего информационного меню.
   */
  export default {
    name: 'TheToolbar',
    data: function () {
      return {
        /**
         * Включен/выключен ночной режим оформления системы
         */
        nightMode: true,
        /**
         * Выбранный язык локализации системы
         */
        locale: 'ru',
        /**
         * Список доступных локалей системы для переключения между ними
         */
        items: [
          {
            text: 'Русский',
            value: 'ru'
          }, {
            text: 'English',
            value: 'en'
          }
        ]
      }
    },
    computed: mapState('settings', {'navigation': state => state.navigation}),
    methods: {
      ...mapMutations('settings', ['setMinVariant']),
      ...mapActions('auth', ['callLogout']),
    },
    /**
     * Настраиваем систему после монтирования компонента
     */
    mounted() {
      let nightMode = localStorage.getItem('nightMode');
      if (nightMode) {
        nightMode = nightMode === "true";
        this.$vuetify.theme.dark = nightMode;
        this.nightMode = nightMode;
      }

      let locale = localStorage.getItem('locale');
      if (locale) {
        this.$i18n.locale = locale;
        this.locale = locale;
      }
    },
    watch: {
      /**
       * Сохраняем выбранный режим оформления системы в localStorage
       */
      nightMode() {
        this.$vuetify.theme.dark = this.nightMode;
        localStorage.setItem('nightMode', this.nightMode);
      },
      /**
       * Сохраняем выбранный язык системы в localStorage
       */
      locale() {
        this.$i18n.locale = this.locale;
        localStorage.setItem('locale', this.locale);
      },
    }
  }
</script>

<style scoped>

</style>