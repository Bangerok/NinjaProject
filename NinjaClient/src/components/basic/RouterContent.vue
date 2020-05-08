<template>
  <v-content>
    <router-view style="margin: 20px"></router-view>
    <app-footer></app-footer>
  </v-content>
</template>

<script>
  import AppFooter from "./AppFooter";

  export default {
    name: "RouterContent",
    components: {AppFooter},
  }
</script>

<style scoped>

</style>