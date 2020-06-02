<template>
  <div id="app">
    <v-app>
      <the-notification-msg></the-notification-msg>
      <the-progress-bar/>
      <the-app-bar v-if="user"></the-app-bar>
      <the-navigation-drawer v-if="user"></the-navigation-drawer>
      <v-content class="content text--primary">
        <transition appear name="slide-fade">
          <router-view style="margin: 20px"></router-view>
        </transition>
      </v-content>
    </v-app>
  </div>
</template>

<script>
  import TheNotificationMsg from "./components/TheNotificationMsg";
  import TheProgressBar from "./components/TheProgressBar";
  import TheAppBar from "./components/TheToolbar";
  import TheNavigationDrawer from "./components/TheSidebar";
  import {mapState, mapActions, mapMutations} from 'vuex';

  export default {
    components: {TheNotificationMsg, TheProgressBar, TheAppBar, TheNavigationDrawer},
    computed: mapState("auth", {"user": state => state.user}),
    methods: {
      ...mapMutations(["setOptionsNotification"]),
      ...mapActions("auth", ["getCurrentUser"]),
    },
    beforeCreate() {
      const uri = new URL(location.href);
      const jwtToken = uri.searchParams.get("token");
      if (jwtToken !== null) {
        localStorage.setItem("jwt-token", jwtToken);
        document.location.replace(uri.origin);
        this.$forceUpdate();
      }
    },
    mounted() {
      // noinspection JSValidateTypes
      this.getCurrentUser();
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
