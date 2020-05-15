<template>
  <v-snackbar
      v-model="notification.show"
      :color="notification.color"
      right
      top
      :timeout="3000"
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
    computed: mapState(['notification']),
    methods: mapMutations(['setNotificationShow']),
  }
</script>

<style scoped>

</style>