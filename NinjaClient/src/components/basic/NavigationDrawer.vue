<template>
  <div>
    <v-navigation-drawer app clipped :mini-variant="minVariant" color="navigation" floating
                         mobile-break-point="320">
      <v-list dense class="py-0">
        <v-list-item two-line>
          <v-list-item-avatar style="margin-left: -10px;">
            <v-icon large color="orange darken-2">fa-bolt</v-icon>
          </v-list-item-avatar>

          <v-list-item-content class="pl-2">
            <v-list-item-title>{{ $t('navigation.companyName') }}</v-list-item-title>
            <v-list-item-subtitle>{{ $t('navigation.companyDescription') }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider></v-divider>

        <v-list-item
            v-for="item in links"
            :key="item.title"
            :to="item.hasSubmenus ? '' : item.path"
            v-on:click="item.hasSubmenus ? showSubmenus = !showSubmenus : ''"
        >
          <v-tooltip right>
            <template v-slot:activator="{ on }">
              <v-list-item-icon v-on="on" class="justify-center">
                <v-icon dense>{{ item.icon }}</v-icon>
              </v-list-item-icon>
            </template>
            <span class="overline">{{ $t(item.name) }}</span>
          </v-tooltip>

          <v-list-item-content>
            <v-list-item-title class="overline">{{ $t(item.name) }}</v-list-item-title>
          </v-list-item-content>

          <template v-if="item.hasSubmenus && !item.isSubmenu">
            <v-list-item-icon>
              <v-icon v-show="!showSubmenus">mdi-chevron-right</v-icon>
              <v-icon v-show="showSubmenus">mdi-chevron-down</v-icon>
            </v-list-item-icon>
          </template>
        </v-list-item>
      </v-list>

      <template v-slot:append>
        <v-divider v-show="!minVariant"></v-divider>
        <v-container v-show="!minVariant" class="overline pb-0 mb-n1">
          <p class="text-center">&copy; Copyright, 2020</p>
        </v-container>
      </template>
    </v-navigation-drawer>
  </div>
</template>

<script>
  import logoIcon from "./../../assets/logo_app_bar.png"

  export default {
    name: "NavigationDrawer",
    data: function () {
      return {
        showSubmenus: false,
        logoIcon: logoIcon
      }
    },
    computed: {
      minVariant() {
        return this.$store.state.navigation.minVariant;
      },
      links() {
        let links = this.$store.state.navigation.links;
        let filteredLinksList = [];
        for (let i in links) {
          if (Object.prototype.hasOwnProperty.call(links, i)) {
            if (links[i].path !== '*' && links[i].path !== '/login' && ((links[i].isSubmenu
                && this.showSubmenus)
                || (!links[i].isSubmenu))) {
              filteredLinksList.push(links[i]);
            }
          }
        }

        return filteredLinksList;
      },
    }
  }
</script>

<style scoped>

</style>