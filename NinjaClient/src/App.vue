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
import TheNotificationMsg from "./components/TheNotificationMsg";
import TheProgressBar from "./components/TheProgressBar";
import TheToolbar from "./components/TheToolbar";
import TheSidebar from "./components/TheSidebar";
import TheRouterView from "./components/TheRouterView";
import BaseDialog from "./components/base/dialog/BaseDialog";
import {mapActions, mapState} from 'vuex';

/**
 * Построение скелета страниц системы.
 */
export default {
  components: {
    TheNotificationMsg, TheProgressBar, TheToolbar, TheSidebar, TheRouterView,
    BaseDialog,
  },
  data: () => ({
    /**
     * Флаг скрытия/отображения диалога подтверждения запроса нового токена верификации
     * электронной почты.
     */
    showConfirmResendTokenDialog: false,
    /**
     * Истекший токен верификации.
     */
    expiredVerifyToken: '',
  }),
  computed: mapState("auth", {"user": state => state.user}),
  methods: {
    ...mapActions("auth", ["getCurrentUser", "confirmEmail", "reSendVeryficationTokenEmail"]),
    /**
     * Метод для отправки запроса на сервер для отправки на почту нового токена верификации
     * электронной почты.
     */
    confirmDialog() {
      // noinspection JSValidateTypes
      this.reSendVeryficationTokenEmail(this.expiredVerifyToken);
      this.showConfirmResendTokenDialog = false;
    }
  },
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
