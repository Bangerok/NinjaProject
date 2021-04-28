<template>
  <v-progress-linear indeterminate color="teal"
                     v-show="loading"
  />
</template>

<script>
import {mapState} from "vuex";

/**
 * Компонент для показа строки состояния выполнения запросов к серверу.
 */
export default {
  name: "TheProgressBar",
  computed: mapState({'loading': state => state.loading})
}
</script>

<style scoped>

</style>