<template>
  <v-container
      class="full-height"
      fluid
  >
    <v-layout
        justify-center
        align-center
    >
      <v-row
          align="center"
          justify="center"
      >
        <v-col
            cols="12"
            sm="8"
            md="4"
        >
          <v-card height="100%" class="elevation-12">
            <v-toolbar
                flat
            >
              <v-spacer/>
              <v-toolbar-title>{{$t('components.auth.formName')}}</v-toolbar-title>
              <v-spacer/>
            </v-toolbar>
            <v-card-text>
              <v-form
                  ref="form"
                  v-model="valid"
                  lazy-validation
              >
                <v-text-field
                    :label="$t('components.auth.username')"
                    name="login"
                    prepend-icon="fa-user"
                    type="text"
                    v-model="username"
                    :rules="nameRules"
                ></v-text-field>

                <v-text-field
                    :label="$t('components.auth.password')"
                    id="password"
                    name="password"
                    v-model="password"
                    :rules="passwordRules"
                    prepend-icon="fa-lock"
                    :append-icon="isShowPassword ? 'fa-eye' : 'fa-eye-slash'"
                    :type="isShowPassword ? 'text' : 'password'"
                    @click:append="isShowPassword = !isShowPassword"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions class="justify-center mt-n10">
              <v-col class="text-center">
                <v-row class="flex-column">
                  <v-col>
                    <v-btn text color="primary" @click="setOptionsNotification(auth())">
                      {{ $t('buttons.authBtn') }}
                    </v-btn>
                    <v-btn text color="teal" @click="setOptionsNotification(auth())">
                      {{ $t('buttons.registerBtn') }}
                    </v-btn>
                  </v-col>
                  <v-col class="mb-n7 mt-n5">
                    <v-divider class="mb-1"/>
                    <v-btn text
                           href="api/login/google?redirect_uri=http://localhost:8000">
                      <v-icon color="error" class="mr-2">
                        mdi-google
                      </v-icon>
                      Войдите через Google
                    </v-btn>
                  </v-col>
                </v-row>
              </v-col>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-layout>
  </v-container>
</template>

<script>
  import {mapMutations} from "vuex";

  export default {
    name: "Login",
    data: () => ({
      valid: true,
      isShowPassword: false,
      username: '',
      password: '',
      nameRules: [],
      passwordRules: [],
    }),
    created() {
      this.passwordRules = [
        v => !!v || this.$t('components.auth.passwordRequired'),
        v => (v && v.length >= 8 && v.length <= 20) || this.$t(
            'components.auth.isValidPasswordMsg'),
      ];

      this.nameRules = [
        v => !!v || this.$t('components.auth.nameRequired'),
        v => (v && v.length <= 50) || this.$t('components.auth.isValidNameMsg'),
      ];
    },
    methods: {
      ...mapMutations('settings', ['setOptionsNotification']),
      auth() {
        const notificationOptions = {};
        if (this.$refs.form.validate()) {
          notificationOptions.color = 'success';
          notificationOptions.text = this.$t('notification.authSuccess');
        } else {
          notificationOptions.color = 'error';
          notificationOptions.text = this.$t('notification.authError');
        }

        return notificationOptions;
      },
    },
  }
</script>

<style scoped>

</style>