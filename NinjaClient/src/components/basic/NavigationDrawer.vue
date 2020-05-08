<template>
  <v-navigation-drawer app :mini-variant="minVariant" color="primary">
    <v-list dense nav class="py-0">
      <v-list-item two-line>
        <v-list-item-avatar style="margin-left: -10px;">
          <img :src="logoIcon" alt="">
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{ $t('navigation.companyName') }}</v-list-item-title>
          <v-list-item-subtitle>{{ $t('navigation.companyDescription') }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list-item
          v-for="item in links"
          :key="item.title"
          :to="item.hasSubmenus ? '' : item.path"
          link
          v-on:click="item.hasSubmenus ? showSubmenus = !showSubmenus : ''"
          v-bind:class="{ 'px-5': item.isSubmenu }"
      >
        <v-tooltip right>
          <template v-slot:activator="{ on }">
            <v-list-item-icon v-on="on">
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
          </template>
          <span>{{ $t(item.name) }}</span>
        </v-tooltip>

        <v-list-item-content>
          <v-list-item-title>{{ $t(item.name) }}</v-list-item-title>
        </v-list-item-content>

        <template v-if="item.hasSubmenus && !item.isSubmenu">
          <v-list-item-icon>
            <v-icon v-show="!showSubmenus">mdi-chevron-right</v-icon>
            <v-icon v-show="showSubmenus">mdi-chevron-down</v-icon>
          </v-list-item-icon>
        </template>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  import logoIcon from "./../../assets/logo.png"

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
            if (links[i].path !== '*' && ((links[i].isSubmenu && this.showSubmenus)
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