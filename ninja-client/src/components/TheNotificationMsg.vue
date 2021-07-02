<template>
  <v-snackbar
    v-model="notification.show"
    right
    top
    :timeout="3000"
    :color="notification.color"
  >
    {{ i18n(notification.text, notification.i18n) }}
    <v-btn
      text
      @click="setNotificationShow"
    >
      {{ $t('buttons.closeBtn') }}
    </v-btn>
  </v-snackbar>
</template>

<script>
import {mapMutations, mapState} from "vuex";

/**
 * Component for displaying a notification window about various events in the system.
 */
export default {
  name: "NotificationMsg",
  computed: mapState({'notification': state => state.notification}),
  methods: {
    ...mapMutations(['setNotificationShow']),
    i18n(text, isLocalized) {
      if (isLocalized) {
        return this.$t(text);
      }

      return text;
    },
  }
};
</script>

<style scoped>

</style>