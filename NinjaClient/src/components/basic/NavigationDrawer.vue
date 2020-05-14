<template>
  <div>
    <v-navigation-drawer app clipped :mini-variant="navigation.minVariant" color="navigation" floating
                         mobile-break-point="320">
      <v-list dense class="py-0">
        <v-list-item
            v-for="(item, key) in filteredNavigationLinks"
            :key="key"
            :to="item.path"
        >
          <v-tooltip :disabled="!navigation.minVariant" right nudge-right="15px">
            <template v-slot:activator="{ on }">
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

      <template v-slot:append>
        <v-divider v-show="!navigation.minVariant"></v-divider>
        <v-container v-show="!navigation.minVariant" class="overline pb-0 mb-n1">
          <p class="text-center">&copy; Copyright, 2020</p>
        </v-container>
      </template>
    </v-navigation-drawer>
  </div>
</template>

<script>
  import logoIcon from "./../../assets/logo_app_bar.png"
  import {mapState} from "vuex";

  export default {
    name: "NavigationDrawer",
    data: function () {
      return {
        showSubmenus: false,
        logoIcon: logoIcon
      }
    },
    computed: {
      ...mapState(['navigation']),
      filteredNavigationLinks() {
        return this.$router.options.routes.filter(route => route.meta.showInMenu);
      },
    }
  }
</script>

<style scoped>

</style>