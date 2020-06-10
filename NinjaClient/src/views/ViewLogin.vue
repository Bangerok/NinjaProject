<template>
  <v-container class="full-height" fluid>
    <v-layout justify-center align-center><v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="4">
          <v-card height="100%" class="elevation-12">
            <v-toolbar flat>
              <v-spacer/>
              <v-toolbar-title>{{$t('pages.auth.login.formName')}}</v-toolbar-title>
              <v-spacer/>
            </v-toolbar>
            <v-card-text>
              <v-form>
                <v-text-field
                    v-model="email"
                    :label="$t('pages.auth.general.email')"
                    prepend-icon="fa-user"
                />

                <v-text-field
                    v-model="password"
                    :label="$t('pages.auth.general.password')"
                    :append-icon="isShowPassword ? 'fa-eye' : 'fa-eye-slash'"
                    :type="isShowPassword ? 'text' : 'password'"
                    @click:append="isShowPassword = !isShowPassword"
                    prepend-icon="fa-lock"
                />
              </v-form>
            </v-card-text>
            <v-card-actions class="justify-center mt-n10">
              <v-col class="text-center">
                <v-row class="flex-column">
                  <v-col>
                    <v-btn @click="auth" :disabled="email === '' || password === ''" text color="primary">
                      {{ $t('buttons.authBtn') }}
                    </v-btn>
                    <v-btn to="/register" text color="green darken-1" >
                      {{ $t('buttons.registerBtn') }}
                    </v-btn>
                  </v-col>
                  <v-col class="mb-n7 mt-n5">
                    <v-divider class="mb-1"/>
                    <v-btn text href="api/login/google?redirect_uri=http://localhost:8000">
                      <v-icon color="error" class="mr-2">
                        mdi-google
                      </v-icon>
                      {{ $t('pages.auth.login.google') }}
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
  import {mapActions} from "vuex";

  /**
   * Страница для авторизации пользователя.
   */
  export default {
    name: "ViewLogin",
    data: () => ({
      /**
       * Флаг скрытия/отображения вводимого пароля пользователя.
       */
      isShowPassword: false,
      /**
       * Значение электронной почты при авторизации пользователя.
       */
      email: '',
      /**
       * Значение пароля при авторизации пользователя.
       */
      password: '',
    }),
    methods: {
      ...mapActions('auth', ['login']),

      /**
       * Запуск авторизации пользователя.
       */
      auth() {
        const payload = {
          email: this.email,
          password: this.password
        };

        // noinspection JSValidateTypes
        this.login(payload);
      },
    },
  }
</script>

<style scoped>

</style>