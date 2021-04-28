<template>
  <v-main class="content text--primary">
    <transition appear name="slide-fade">
      <router-view style="margin: 20px"></router-view>
    </transition>
  </v-main>
</template>

<script>
export default {
  name: "TheRouterView"
}
</script>

<style scoped>

</style>