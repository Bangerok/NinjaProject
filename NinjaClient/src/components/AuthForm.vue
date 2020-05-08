<template>
  <v-form
      ref="form"
      v-model="valid"
      lazy-validation
  >
    <v-text-field
        v-model="name"
        :counter="50"
        :rules="nameRules"
        :label="$t('components.auth.name')"
        required
    ></v-text-field>

    <v-text-field
        v-model="email"
        :rules="emailRules"
        label="E-mail"
        required
    ></v-text-field>

    <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="auth"
    >
      {{ $t('buttons.authBtn') }}
    </v-btn>

    <v-btn
        color="error"
        class="mr-4"
        @click="reset"
    >
      {{ $t('buttons.clearBtn') }}
    </v-btn>
  </v-form>
</template>

<script>
  export default {
    name: "AuthForm",
    data: () => ({
      valid: true,
      name: '',
      nameRules: [],
      email: '',
      emailRules: [],
    }),
    created() {
      this.emailRules = [
        v => !!v || this.getLocalizationMsg('components.auth.emailRequired'),
        v => /.+@.+\..+/.test(v) || this.getLocalizationMsg('components.auth.isValidEmailMsg'),
      ];

      this.nameRules = [
        v => !!v || this.getLocalizationMsg('components.auth.nameRequired'),
        v => (v && v.length <= 10) ||  this.getLocalizationMsg('components.auth.isValidNameMsg'),
      ];
    },
    methods: {
      getLocalizationMsg: function(name) {
        return this.$t(name);
      },
      auth () {
        const notificationOptions = {};
        if (this.$refs.form.validate()) {
          notificationOptions.color = 'success';
          notificationOptions.text = this.getLocalizationMsg('notification.authSuccess');
        } else {
          notificationOptions.color = 'error';
          notificationOptions.text = this.getLocalizationMsg('notification.authError');
        }

        this.$store.commit('setOptionsNotification', notificationOptions);
      },
      reset () {
        this.$refs.form.reset()
      },
    },
  }
</script>

<style scoped>

</style>