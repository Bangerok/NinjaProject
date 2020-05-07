<!--suppress HtmlUnknownTag -->
<template>
  <v-app-bar app elevation="3">
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

    <v-toolbar-title class="hidden-sm-and-down font-weight-light" v-text="$t($route.name)"/>
  </v-app-bar>
</template>

<script>
  export default {
    name: 'AppBar',
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
  }
</script>

<style scoped>

</style>