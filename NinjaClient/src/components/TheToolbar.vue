<!--suppress HtmlUnknownTag -->
<template>
  <v-app-bar
    color="toolbar"
    app
    elevation="2"
    clipped-left
  >
    <v-card
      flat
      max-width="295px"
      max-height="100%"
      color="appbar"
      class="mt-n4 ml-n5"
    >
      <v-list-item two-line>
        <v-list-item-avatar>
          <v-icon
            large
            color="orange darken-4"
          >
            fa-bolt
          </v-icon>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ $t('navigation.companyName') }}</v-list-item-title>
          <v-list-item-subtitle>{{ $t('navigation.companyDescription') }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-card>

    <v-tooltip
      bottom
      nudge-right="75px"
      nudge-bottom="5px"
    >
      <template #activator="{ on }">
        <v-list-item-icon
          class="justify-center mt-1"
          v-on="on"
        >
          <v-app-bar-nav-icon
            class="mr-3 ml-n2"
            @click="maxMenu = !maxMenu"
          >
            <v-icon v-if="maxMenu">
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

    <!--suppress JSUnresolvedVariable -->
    <v-toolbar-title class="pl-2 font-weight-medium">
      {{ $t($route.meta.title) }}
    </v-toolbar-title>

    <v-spacer />

    <v-btn
      text="mr-3"
      link
      @click="callLogout"
    >
      <v-icon class="mr-1">
        fa-sign-out-alt
      </v-icon>
      {{ $t('buttons.exitBtn') }}
    </v-btn>
    <v-tooltip
      bottom
      nudge-bottom="5px"
    >
      <template #activator="{ on }">
        <v-list-item-icon
          class="justify-center mt-3"
          v-on="on"
        >
          <v-card
            flat
            max-width="130px"
            max-height="40px"
            color="appbar"
          >
            <v-select
              v-model="locale"
              flat
              prepend-icon="fa-globe"
              :items="items"
            />
          </v-card>
        </v-list-item-icon>
      </template>
      <span class="overline">{{ $t('tooltips.changeLanguageSystem') }}</span>
    </v-tooltip>

    <v-tooltip
      bottom
      nudge-top="5px"
    >
      <template #activator="{ on }">
        <v-list-item-icon
          class="justify-center mt-0"
          v-on="on"
        >
          <v-icon
            v-if="nightMode"
            class="mr-3 mt-n1"
          >
            fa-sun
          </v-icon>
          <v-icon
            v-else
            class="mr-3 mt-n1"
          >
            fa-moon
          </v-icon>
          <v-switch
            v-model="nightMode"
            inset
            :color="nightMode ? 'white' : ''"
            class="mt-4"
          />
        </v-list-item-icon>
      </template>
      <span class="overline">{{ $t('tooltips.nightMode') }}</span>
    </v-tooltip>
  </v-app-bar>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';

/**
 * Component for showing top information menu.
 */
export default {
  name: 'TheToolbar',
  data: () => ({
    /**
     * On/off the night mode of the system design.
     */
    nightMode: true,
    /**
     * Selected system localization language.
     */
    locale: 'en',
    /**
     * Selected show/hide full menu.
     */
    maxMenu: false,
    /**
     * List of available system locales for switching between them.
     */
    items: [
      {
        text: 'Русский',
        value: 'ru',
      }, {
        text: 'English',
        value: 'en',
      },
    ],
  }),
  computed: mapGetters('settings', ['getSettingValueByName', 'getSettingByName']),
  watch: {
    /**
     * Saving the selected system design mode.
     */
    nightMode() {
      const isNew = this.saveSetting({name: 'nightMode', value: this.nightMode.toString()});
      if (isNew) {
        this.$vuetify.theme.dark = this.nightMode;
      }
    },
    /**
     * Saving the selected system language.
     */
    locale() {
      const isNew = this.saveSetting({name: 'locale', value: this.locale});
      if (isNew) {
        this.$i18n.locale = this.locale;
      }
    },
    /**
     * Saving the selected flag of show/hide full menu.
     */
    maxMenu() {
      this.saveSetting({name: 'maxMenu', value: this.maxMenu.toString()});
    },
  },
  /**
   * System setup at the stage of component mounting.
   */
  mounted() {
    const nightMode = this.getSettingValueByName('nightMode');
    // noinspection JSIncompatibleTypesComparison
    if (nightMode !== null) {
      this.$vuetify.theme.dark = nightMode;
      this.nightMode = nightMode;
    }

    const locale = this.getSettingValueByName('locale');
    if (locale) {
      this.$i18n.locale = locale;
      this.locale = locale;
    }

    const maxMenu = this.getSettingValueByName('maxMenu');
    if (maxMenu) {
      this.maxMenu = maxMenu;
    }
  },
  methods: {
    ...mapActions('auth', ['callLogout']),
    ...mapActions('settings', ['saveSetting']),
  },
};
</script>

<style scoped>

</style>