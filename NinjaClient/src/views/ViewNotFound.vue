<template>
  <div class="col-md-12 col-md-offset-2 text-center">
    <h2 class="title text-danger">{{ $t('pages.notFound.title') }}</h2>
    <h2 class="title">{{ $t('pages.notFound.description') }}</h2>
  </div>
</template>

<script>
  /**
   * Страница с информацией о том, что текущая страница не существует.
   */
  export default {
    name: "ViewNotFound"
  }
</script>

<style scoped>

</style>