<template>
  <v-snackbar
      v-model="show"
      :color="color"
      :right="right"
      :top="top"
      :timeout="timeout"
  >
    {{ text }}
    <v-btn
        dark
        text
        @click="show = false"
    >
      {{ $t('buttons.closeBtn') }}
    </v-btn>
  </v-snackbar>
</template>

<script>
  export default {
    name: "NotificationMsg",
    data () {
      return {
        timeout: 3000,
        top: true,
        right: true,
      }
    },
    computed: {
      color() {
        return this.$store.state.notification.color;
      },
      show: {
        get() {
          return this.$store.state.notification.show;
        },
        set() {
          this.$store.commit('setNotificationShow', false);
        }
      },
      text() {
        return this.$store.state.notification.text;
      },
    },
  }
</script>

<style scoped>

</style>