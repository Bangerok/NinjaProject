<template>
  <div>
    <div v-for="(generalProperty, name) in filteredItemData(itemData)" :key="name">
      <div class="text-center font-weight-bold">{{ $t('pages.vacancies.data.titles.' + name) }}:</div>
      <div v-for="(subProperty, subName) in generalProperty" :key="subName">
        <span class="font-weight-medium">{{ $t('pages.vacancies.data.subTitles.' + subName) }}:</span>
        {{ subProperty }}
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ComponentVacancyCard",
    props: {
      itemData: {
        type: Object,
        default:  () => ({}),
      },
    },
    methods: {
      filteredItemData(itemData) {
        let filteredItemData = {};
        for (let name in itemData) {
          if (Object.prototype.hasOwnProperty.call(itemData, name)) {
            if (name !== 'title' && name !== 'createdDate') {
              filteredItemData[name] = itemData[name];
            }
          }
        }
        return filteredItemData;
      }
    }
  }
</script>

<style scoped>

</style>