<template>
  <v-progress-linear
      indeterminate
      color="teal"
      v-show="loading"
  ></v-progress-linear>
</template>

<script>
  import {mapState} from "vuex";

  export default {
    name: "TheProgressBar",
    computed: mapState('settings', {'loading': state => state.loading})
  }
</script>

<style scoped>

</style>