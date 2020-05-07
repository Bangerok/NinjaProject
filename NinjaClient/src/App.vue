<template>
  <div id="app">
    <v-app>
      <notification-msg></notification-msg>
      <app-bar></app-bar>
      <navigation-drawer></navigation-drawer>
      <router-content></router-content>
    </v-app>
  </div>
</template>

<script>
  import NotificationMsg from "./components/basic/NotificationMsg";
  import AppBar from "./components/basic/AppBar";
  import NavigationDrawer from "./components/basic/NavigationDrawer";
  import RouterContent from "./components/basic/RouterContent";

  export default {
    components: {NotificationMsg, AppBar, NavigationDrawer, RouterContent},
  }
</script>

<style scoped>

</style>
