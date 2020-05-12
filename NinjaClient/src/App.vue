<template>
  <div id="app">
    <v-app>
      <notification-msg></notification-msg>
      <app-bar></app-bar>
      <navigation-drawer></navigation-drawer>
      <v-content>
        <v-container fluid>
        <router-view style="margin: 20px"></router-view>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
  import NotificationMsg from "./components/basic/NotificationMsg";
  import AppBar from "./components/basic/AppBar";
  import NavigationDrawer from "./components/basic/NavigationDrawer";
  import service from "./api/actions";

  export default {
    components: {NotificationMsg, AppBar, NavigationDrawer},
    methods: {
      async logout() {
        await service.logout();
      },
      async updateProfile() {
        const data = await service.getUser();
        this.$store.state.user = await data.json();
      }
    },
    mounted() {
      this.logout();
      this.updateProfile();
    }
  }
</script>

<style scoped>

</style>
