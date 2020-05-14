<template>
  <div id="app">
    <v-app>
      <notification-msg></notification-msg>
      <app-bar v-if="user !== null"></app-bar>
      <navigation-drawer v-if="user !== null"></navigation-drawer>
      <v-content>
        <v-container fluid>
          <transition appear name="slide-fade">
            <router-view style="margin: 20px"></router-view>
          </transition>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
  import NotificationMsg from "./components/basic/NotificationMsg";
  import AppBar from "./components/basic/AppBar";
  import NavigationDrawer from "./components/basic/NavigationDrawer";
  import {mapState, mapActions} from 'vuex';

  export default {
    components: {NotificationMsg, AppBar, NavigationDrawer},
    computed: mapState(['user']),
    methods: {
      ...mapActions(['getUser']),
    },
    watch: {
      user(newValue) {
        if (newValue === null) {
          if (this.$route.path !== 'login') {
            this.$router.replace('login');
          }
        }
      }
    },
    beforeCreate() {
      let currentLocation = document.location.href;
      if (currentLocation.indexOf("#") !== -1) {
        currentLocation = currentLocation.replace("#", "");
        document.location.replace(currentLocation);
        this.$forceUpdate();
      }
    },
    created() {
      // noinspection JSValidateTypes
      this.getUser().then(() => {
        if (this.user != null) {
          if (this.$route.path !== '/' || window.location.pathname === '/login') {
            this.$router.replace('/');
          }
        } else {
          if (this.$route.path !== 'login') {
            this.$router.replace('login');
          }
        }
      });
    },
  }
</script>

<!--suppress CssUnusedSymbol -->
<style scoped>
  .slide-fade-enter-active {
    transition: all .3s ease;
  }

  .slide-fade-leave-active {
    transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
</style>
