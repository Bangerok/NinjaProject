<template>
  <div>
    <p>{{ $t('pages.technologies.web') }}</p>
    <div class="row">
      <div class="col-md-6 col-xl-2" v-for="(webCard, key) in filteredStatsCard('web')" :key="key">
        <div class="card">
          <div class="icon-big height-full">
            <img style="width: 80px" class="tech" :src="webCard.iconImg" alt="">
          </div>
        </div>
      </div>
    </div>

    <p>{{ $t('pages.technologies.database') }}</p>
    <div class="row">
      <div class="col-md-6 col-xl-2" v-for="(dbCard, key) in filteredStatsCard('database')" :key="key">
        <div class="card">
          <div class="icon-big height-full">
            <img style="width: 80px" class="tech" :src="dbCard.iconImg" alt="">
          </div>
        </div>
      </div>
    </div>

    <p>{{ $t('pages.technologies.programming') }}</p>
    <div class="row">
      <div class="col-md-6 col-xl-2" v-for="(progCard, key) in filteredStatsCard('programming')" :key="key">
        <div class="card">
          <div class="icon-big height-full">
            <img style="width: 80px" class="tech" :src="progCard.iconImg" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Technologies",
    methods: {
      filteredStatsCard(type) {
        let statsCard = this.$store.state.pages.technologies.statsCards;
        let filteredStatsCard = []
        for (let i in statsCard) {
          if (Object.prototype.hasOwnProperty.call(statsCard, i)) {
            if (statsCard[i].type === type) {
              filteredStatsCard.push(statsCard[i]);
            }
          }
        }
        return filteredStatsCard;
      }
    },
  }
</script>

<style scoped>
  .height-full {
    height: 150px;
  }
</style>