<template>
  <v-snackbar right top timeout="3000"
      v-model="notification.show"
      :color="notification.color"
  >
    {{ notification.text }}
    <v-btn text @click="setNotificationShow">
      {{ $t('buttons.closeBtn') }}
    </v-btn>
  </v-snackbar>
</template>

<script>
  import {mapMutations, mapState} from "vuex";

  /**
   * Компонент для показа окна уведомлений о различных событий в системе.
   */
  export default {
    name: "NotificationMsg",
    computed: mapState({'notification': state => state.notification}),
    methods: mapMutations(['setNotificationShow']),
  }
</script>

<style scoped>

</style>