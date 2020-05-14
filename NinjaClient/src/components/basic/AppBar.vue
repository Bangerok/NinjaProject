<!--suppress HtmlUnknownTag -->
<template>
  <v-app-bar color="appbar" app elevation="2" clipped-left>
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
      <template v-slot:activator="{ on }">
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
      <span class="overline">Свернуть / развернуть меню</span>
    </v-tooltip>

    <v-toolbar-title class="pl-2 font-weight-medium">
      {{ $t($route.name) }}
    </v-toolbar-title>

    <v-spacer/>

    <v-tooltip bottom nudge-top="10px">
      <template v-slot:activator="{ on }">
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
      <span class="overline">Выключить / включить темный режим</span>
    </v-tooltip>
  </v-app-bar>
</template>

<script>
  import {mapState, mapMutations} from 'vuex';

  export default {
    name: 'AppBar',
    data: function () {
      return {
        nightMode: true,
      }
    },
    computed: mapState(['navigation']),
    methods: mapMutations(['setMinVariant']),
    watch: {
      nightMode() {
        this.$vuetify.theme.dark = this.nightMode;
      }
    }
  }
</script>

<style scoped>

</style>