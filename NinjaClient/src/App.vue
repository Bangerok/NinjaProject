<template>
  <div id="app">
    <v-app>
      <the-notification-msg></the-notification-msg>
      <the-progress-bar/>
      <the-toolbar v-if="user"></the-toolbar>
      <the-sidebar v-if="user"></the-sidebar>
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
  import TheToolbar from "./components/TheToolbar";
  import TheSidebar from "./components/TheSidebar";
  import {mapState, mapActions} from 'vuex';

  /**
   * Построение скелета страниц системы.
   */
  export default {
    components: {TheNotificationMsg, TheProgressBar, TheToolbar, TheSidebar},
    computed: mapState("auth", {"user": state => state.user}),
    methods: mapActions("auth", ["getCurrentUser", "confirmEmail"]),
    created() {
      const uri = new URL(location.href);
      const jwtToken = uri.searchParams.get("token");
      if (jwtToken) {
        localStorage.setItem("jwt-token", jwtToken);
        document.location.replace(uri.origin);
        this.$forceUpdate();
      }

      const confirmEmailToken = uri.searchParams.get("confirmEmailToken")
      if (confirmEmailToken) {
        // noinspection JSValidateTypes
        this.confirmEmail(confirmEmailToken);
        console.log("Confirmation email token: " + confirmEmailToken);
      }
    },
    mounted() {
      if (localStorage.getItem("jwt-token")) {
        // noinspection JSValidateTypes
        this.getCurrentUser();
      }
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
