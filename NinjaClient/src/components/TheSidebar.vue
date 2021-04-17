<template>
  <div>
    <v-navigation-drawer app clipped color="sidebar" mobile-breakpoint="320"
                         :mini-variant="navigation.minVariant"
    >
      <v-list class="py-0">
        <v-list-item
            v-for="(item, key) in filteredNavigationLinks"
            :key="key"
            :to="item.path"
        >
          <v-tooltip :disabled="!navigation.minVariant" right nudge-right="15px">
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
        <v-divider v-show="!navigation.minVariant"></v-divider>
        <v-container v-show="!navigation.minVariant" class="overline pb-0 mb-n1">
          <p class="text-center text--disabled">&copy; Copyright, 2020</p>
        </v-container>
      </template>
    </v-navigation-drawer>
  </div>
</template>

<script>
  import {mapState} from "vuex";

  /**
   * Компонент для показа меню-навигации по системе.
   */
  export default {
    name: "TheSidebar",
    computed: {
      ...mapState('settings', {'navigation': state => state.navigation}),
      /**
       * Метод для получения списка routes, у которых в мета данных есть информация о том,
       * что их нужно отобразить.
       *
       * @return отфильтрованный список routes
       */
      filteredNavigationLinks() {
        return this.$router.options.routes.filter(route => route.meta.showInMenu);
      },
    }
  }
</script>

<style scoped>

</style>