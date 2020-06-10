<template>
  <v-content class="content text--primary">
    <transition appear name="slide-fade">
      <router-view style="margin: 20px"></router-view>
    </transition>
  </v-content>
</template>

<script>
  export default {
    name: "TheRouterView"
  }
</script>

<style scoped>

</style>