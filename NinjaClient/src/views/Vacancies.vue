<template>
  <div>
    <div class="text-center" v-if="vacanciesData.length === 0">
      <p>
        {{ $t('pages.vacancies.dontActive') }}
      </p>
    </div>
    <div v-if="vacanciesData.length !== 0">
      <component-list v-bind:data="vacanciesData"></component-list>
    </div>
  </div>
</template>

<script>
  import ComponentList from "../components/ComponentList";

  export default {
    name: "Vacancies",
    components: {ComponentList},
    computed: {
      vacanciesData() {
        return this.$store.state.pages.vacancies;
      },
    },
    watch: {
      $route() {
        this.getVacanciesData();
      }
    },
    methods: {
      getVacanciesData() {
        this.$store.dispatch('getVacanciesData', {
          language: this.$t(this.$route.name)
        });
      },
    },
    created(){
      this.getVacanciesData();
    },
  }
</script>

<style scoped>

</style>