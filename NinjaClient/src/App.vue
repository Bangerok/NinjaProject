<template>
  <div id="app">
    <v-app>
      <the-notification-msg/>
      <the-progress-bar/>
      <the-toolbar v-if="user"/>
      <the-sidebar v-if="user"/>
      <the-router-view/>
      <base-dialog v-bind:show="showConfirmResendTokenDialog" v-bind:confirm="confirmDialog">
        <template #content>
          {{ $t('dialogs.content.resendToken') }}
        </template>
      </base-dialog>
    </v-app>
  </div>
</template>

<script>
const TheNotificationMsg = () => import("./components/TheNotificationMsg");
const TheProgressBar = () => import("./components/TheProgressBar");
const TheToolbar = () => import("./components/TheToolbar");
const TheSidebar = () => import("./components/TheSidebar");
const TheRouterView = () => import("./components/TheRouterView");
const BaseDialog = () => import("./components/base/dialog/BaseDialog");
import {mapActions, mapState} from 'vuex';

/**
 * Building the skeleton of the system pages.
 */
export default {
  components: {
    TheNotificationMsg, TheProgressBar, TheToolbar, TheSidebar, TheRouterView,
    BaseDialog,
  },
  data: () => ({
    /**
     * Show/hide the confirmation dialog for requesting a new email verification token.
     */
    showConfirmResendTokenDialog: false,
    /**
     * Expired verification token.
     */
    expiredVerifyToken: '',
  }),
  computed: mapState("auth", {"user": state => state.user}),
  methods: {
    ...mapActions("auth", ["getCurrentUser", "confirmEmail", "reSendVerificationTokenEmail"]),
    /**
     * Receiving a new verification token for a user.
     */
    confirmDialog() {
      // noinspection JSValidateTypes
      this.reSendVerificationTokenEmail(this.expiredVerifyToken);
      this.showConfirmResendTokenDialog = false;
    },
  },
  /**
   * Checking the value of the browser address bar for the presence of various tokens and processing
   * them when switching to the application.
   */
  created() {
    const uri = new URL(location.href);
    const jwtToken = uri.searchParams.get("token");
    if (jwtToken) {
      localStorage.setItem("jwt-token", jwtToken);
      document.location.replace(uri.origin);
      this.$forceUpdate();
    }

    const confirmEmailToken = uri.searchParams.get("confirmEmailToken");
    if (confirmEmailToken) {
      // noinspection JSValidateTypes
      this.confirmEmail(confirmEmailToken).catch(error => {
        this.expiredVerifyToken = error.data;
        this.showConfirmResendTokenDialog = true;
      });
    }
  },
  /**
   * Getting user data by jwt token at the mount stage.
   */
  mounted() {
    if (localStorage.getItem("jwt-token")) {
      // noinspection JSValidateTypes
      this.getCurrentUser();
    }
  },
};
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
