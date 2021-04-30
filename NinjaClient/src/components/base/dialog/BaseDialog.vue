<template>
  <v-dialog persistent
            v-model="show"
            max-width="400"
  >
    <v-card>
      <header>
        <v-card-title class="headline justify-center">
          <slot name="header">
            {{ $t('dialogs.title.confirmation') }}
          </slot>
        </v-card-title>
        <v-divider/>
      </header>

      <main>
        <v-card-text>
          <slot name="content">
            {{ $t('dialogs.content.confirmation') }}
          </slot>
        </v-card-text>
      </main>

      <footer>
        <v-card-actions>
          <v-spacer/>
          <slot name="footer">
            <v-btn
                color="green darken-1"
                text
                @click="confirm"
            >
              {{ $t('buttons.yesBtn') }}
            </v-btn>

            <v-btn
                color="red darken-1"
                text
                @click="show = false"
            >
              {{ $t('buttons.closeBtn') }}
            </v-btn>
          </slot>
          <v-spacer/>
        </v-card-actions>
      </footer>
    </v-card>
  </v-dialog>
</template>

<script>
/**
 * Basic component for showing custom content dialog.
 */
export default {
  name: "BaseDialog",
  props: {
    confirm: Function,
    show: Boolean,
  },
};
</script>

<style scoped>

</style>