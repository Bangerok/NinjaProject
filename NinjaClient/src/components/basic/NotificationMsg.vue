<template>
  <v-snackbar
      v-model="notification.show"
      :color="notification.color"
      :right="right"
      :top="top"
      :timeout="timeout"
  >
    {{ notification.text }}
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

  export default {
    name: "NotificationMsg",
    data() {
      return {
        timeout: 3000,
        top: true,
        right: true,
      }
    },
    computed: mapState(['notification']),
    methods: mapMutations(['setNotificationShow']),
  }
</script>

<style scoped>

</style>