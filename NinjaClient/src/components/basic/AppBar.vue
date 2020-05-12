<!--suppress HtmlUnknownTag -->
<template>
  <v-app-bar color="appbar" app elevation="2" clipped-left >
    <v-btn
        class="mr-3 ml-n2"
        elevation="1"
        fab
        small
        @click="setMinVariant(!minVariant)"
    >
      <v-icon v-if="minVariant">
        fa-bars
      </v-icon>

      <v-icon v-else>
        fa-ellipsis-v
      </v-icon>
    </v-btn>

    <v-toolbar-title class="pl-2 font-weight-medium">
      {{ $t($route.name) }}
    </v-toolbar-title>

    <v-spacer/>
    <v-icon v-if="nightMode" class="mr-3 mt-n1">
      fa-sun
    </v-icon>
    <v-icon v-else class="mr-3 mt-n1">
      fa-moon
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