<template>
  <v-snackbar right top
              :timeout="3000"
              v-model="notification.show"
              :color="notification.color"
  >
    {{ $t(notification.text) }}
    <v-btn text @click="setNotificationShow">
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
  methods: mapMutations(['setNotificationShow']),
};
</script>

<style scoped>

</style>