<template>
  <div>
    <div class="text-center d-flex">
      <v-btn class="ml-4 mt-4" v-if="panel.length !== data.length" @click="openAll">
        {{  $t('buttons.openAll') }}
      </v-btn>
      <v-btn class="ml-4 mt-4" v-if="panel.length > 0 || panel.length === data.length"
             @click="closeAll">
        {{ $t('buttons.closeAll') }}
      </v-btn>
      <v-spacer/>
      <v-text-field
          clearable
          class="mr-4"
          :label="$t('filters.title')"
          v-model="filterString"
      />
    </div>
    <v-expansion-panels
        v-model="panel"
        multiple
        hover
        popout
        focusable
        v-if="filterData().length > 0"
    >
      <v-expansion-panel
          v-for="item in filterData()"
          :key="item.title"
      >
        <v-expansion-panel-header>
          <v-row no-gutters>
            {{ item.title }}
            <v-spacer/>
            <div class="text--secondary">
              <v-fade-transition leave-absolute>
                <v-row
                    no-gutters
                >
                  {{ item.createdDate }}
                </v-row>
              </v-fade-transition>
            </div>
          </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <component-vacancy-card v-bind:item-data="item"></component-vacancy-card>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <div v-if="filterData().length === 0" class="text-center ml-4" color="white">
      <span class="subheading">{{ $t('filters.emptyResults') }}</span>
    </div>
  </div>
</template>

<script>
  import ComponentVacancyCard from "./ComponentVacancyCard";

  export default {
    name: "ComponentList",
    components: {ComponentVacancyCard},
    props: {
      data: {
        type: Array,
        default: () => ([]),
      },
    },
    data: () => ({
      panel: [],
      filterString: null,
    }),
    watch: {
      $route () {
        this.panel = [];
      },
    },
    methods: {
      openAll() {
        this.panel = [...Array(this.data.length).keys()].map((k, i) => i)
      },
      closeAll() {
        this.panel = []
      },
      filterData() {
        if (this.filterString === null) {
          return this.data;
        }

        let filterDataArray = [];
        for (const id in this.data) {
          if (this.data[id].createdDate.indexOf(this.filterString) !== -1) {
            filterDataArray.push(this.data[id])
          }
        }
        return filterDataArray;
      },
    },
  }
</script>

<style scoped>

</style>