<!--suppress HtmlUnknownTag -->
<template>
  <v-app-bar color="secondary" app elevation="0">
    <v-btn
        class="mr-3"
        elevation="1"
        fab
        small
        @click="setMinVariant(!minVariant)"
    >
      <v-icon v-if="minVariant">
        mdi-view-list
      </v-icon>

      <v-icon v-else>
        mdi-dots-vertical
      </v-icon>
    </v-btn>

    <v-toolbar-title>
      {{ $t($route.name) }}
    </v-toolbar-title>

    <v-spacer/>
    <v-icon v-if="nightMode" class="mr-3 mt-n1">
      mdi-weather-sunny
    </v-icon>
    <v-icon v-else class="mr-3 mt-n1">
      mdi-weather-night
    </v-icon>
    <v-switch v-model="nightMode" inset :color="nightMode ? 'white' : ''" class="mt-4"/>
  </v-app-bar>
</template>

<script>
  export default {
    name: 'AppBar',
    data: function () {
      return {
        nightMode: true,
      }
    },
    computed: {
      minVariant() {
        return this.$store.state.navigation.minVariant;
      },
    },
    methods: {
      setMinVariant: function (minVariant) {
        this.$store.commit('setMinVariant', minVariant);
      },
    },
    watch: {
      nightMode() {
        this.$vuetify.theme.dark = this.nightMode;
      }
    }
  }
</script>

<style scoped>

</style>