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
        await service.logout().then(response => {
          console.log(response);
        }, response => {
          console.log(response);
        });
      },
      async updateProfile() {
        const data = await service.getUser();
        this.$store.state.user = await data.json();
      }
    },
    mounted() {
      let currentLocation = document.location.href;
      if (currentLocation.indexOf("#") !== -1) {
        currentLocation = currentLocation.replace("#", "");
        document.location.replace(currentLocation);
      } else {
        this.updateProfile();
        //this.logout();
      }
    }
  }
</script>

<style scoped>

</style>
