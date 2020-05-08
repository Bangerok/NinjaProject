<template>
  <v-footer app inset padless elevation="5" color="tertiary">
    <v-container>
      <v-row align="center" no-gutters>
        <v-col
            v-for="(link, i) in links"
            :key="i"
            class="text-center mb-sm-0 mb-5"
            cols="auto"
        >
          <router-link exact class="mr-3" :to="link.href" style="text-decoration: none">
            <v-btn text small>{{ $t(link.text) }}</v-btn>
          </router-link>
        </v-col>

        <v-spacer class="hidden-sm-and-down"/>

        <v-col cols="12" md="auto">
          <div class="body-1 font-weight-light pt-6 pt-md-0 text-center">
            &copy; 2020, {{ $t('navigation.companyName') }}
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-footer>
</template>

<script>
  export default {
    name: "AppFooter",
    data: function () {
      return {
        links: [
          {
            href: '/info',
            text: 'navigation.menu.info',
          },
          {
            href: '/vacancies/language1',
            text: 'navigation.menu.vacancies.title',
          },
        ],
      }
    },
  }
</script>

<style scoped>

</style>