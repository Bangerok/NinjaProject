<template>
  <div id="app">
    <v-app>
      <notification-msg></notification-msg>
      <app-bar></app-bar>
      <navigation-drawer></navigation-drawer>
      <v-content>
        <router-view style="margin: 20px"></router-view>
      </v-content>
    </v-app>
  </div>
</template>

<script>
  import NotificationMsg from "./components/basic/NotificationMsg";
  import AppBar from "./components/basic/AppBar";
  import NavigationDrawer from "./components/basic/NavigationDrawer";

  export default {
    components: {NotificationMsg, AppBar, NavigationDrawer},
  }
</script>

<style scoped>

</style>
