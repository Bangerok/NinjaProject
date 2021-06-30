<template>
  <v-progress-linear
    v-if="loading"
    indeterminate
    color="teal"
  />
</template>

<script>
import {mapState} from "vuex";

/**
 * Component for displaying the status bar of execution of requests to the server.
 */
export default {
  name: "TheProgressBar",
  computed: mapState({'loading': state => state.loading}),
};
</script>

<style scoped>

</style>