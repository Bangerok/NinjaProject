<template>
  <div>
    <v-navigation-drawer app clipped color="sidebar" mobile-breakpoint="320"
                         :mini-variant="!isMaxMenuShow"
    >
      <v-list class="py-0">
        <v-list-item
            v-for="(item, key) in filteredNavigationLinks"
            :key="key"
            :to="item.path"
        >
          <v-tooltip :disabled="isMaxMenuShow" right nudge-right="15px">
            <template #activator="{ on }">
              <v-list-item-icon v-on="on" class="justify-center">
                <v-icon dense>{{ item.meta.icon }}</v-icon>
              </v-list-item-icon>
            </template>
            <span class="overline">{{ $t(item.meta.title) }}</span>
          </v-tooltip>

          <v-list-item-content>
            <v-list-item-title class="overline">{{ $t(item.meta.title) }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <template #append>
        <v-divider v-if="isMaxMenuShow"/>
        <v-container v-if="isMaxMenuShow" class="overline pb-0 mb-n1">
          <p class="text-center text--disabled">&copy; Copyright, {{ getCurrentYear() }}</p>
        </v-container>
      </template>
    </v-navigation-drawer>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

/**
 * Component for displaying menu navigation through the system.
 */
export default {
  name: "TheSidebar",
  computed: {
    ...mapGetters('settings', ['getSettingValueByName']),
    /**
     * Getting a list of routes that have information in their meta data that they need to be displayed.
     *
     * @return routes filtered list
     */
    filteredNavigationLinks() {
      return this.$router.options.routes.filter(route => route.meta.showInMenu);
    },
    isMaxMenuShow() {
      const maxMenuShow = this.getSettingValueByName('maxMenu');
      if (!maxMenuShow) {
        return false;
      }
      return maxMenuShow;
    }
  },
  methods: {
    /**
     * Getting the current year for copyright.
     *
     * @return {number} this year.
     */
    getCurrentYear: () => new Date().getFullYear(),
  },
};
</script>

<style scoped>

</style>