<template>
  <v-container class="full-height" fluid>
    <v-layout justify-center align-center>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="4">
          <v-card height="100%" class="elevation-12">
            <v-toolbar flat>
              <v-spacer/>
              <v-toolbar-title>{{$t('pages.auth.register.formName')}}</v-toolbar-title>
              <v-spacer/>
            </v-toolbar>

            <v-card-text>
              <v-form>
                <v-text-field
                    v-model="email"
                    :label="$t('pages.auth.general.email')"
                    :error="isError('email')"
                    :error-messages="errorMessage('email')"
                    prepend-icon="fa-at"
                />

                <v-text-field
                    v-model="username"
                    :label="$t('pages.auth.register.username')"
                    :error="isError('username')"
                    :error-messages="errorMessage('username')"
                    prepend-icon="fa-user"
                />

                <v-text-field
                    v-model="password"
                    :label="$t('pages.auth.general.password')"
                    :error="isError('password')"
                    :error-messages="errorMessage('password')"
                    :append-icon="isShowPassword ? 'fa-eye' : 'fa-eye-slash'"
                    :type="isShowPassword ? 'text' : 'password'"
                    @click:append="isShowPassword = !isShowPassword"
                    prepend-icon="fa-lock"
                />

                <v-text-field
                    v-model="matchingPassword"
                    :label="$t('pages.auth.register.matchingPassword')"
                    :error="isError('PasswordMatches')"
                    :error-messages="errorMessage('PasswordMatches')"
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
                    <v-btn text color="primary" @click="submit">
                      {{ $t('buttons.registerBtn') }}
                    </v-btn>

                    <v-btn to="/login" text color="teal">
                      {{ $t('buttons.backBtn') }}
                    </v-btn>
                  </v-col>

                  <v-col class="mb-n4 mt-n5">
                    <v-divider class="mb-1"/>
                    <v-btn text href="api/login/google?redirect_uri=http://localhost:3000">
                      <v-icon color="error" class="mr-2">mdi-google</v-icon>
                      {{ $t('pages.auth.register.google') }}
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
   * Страница для регистрации пользователя.
   */
  export default {
    name: "ViewRegister",
    data: () => ({
      /**
       * Флаг скрытия/отображения вводимых паролей пользователя.
       */
      isShowPassword: false,
      /**
       * Значение электронной почты для регистрации.
       */
      email: '',
      /**
       * Значение имени пользователя для регистрации.
       */
      username: '',
      /**
       * Значение пароля для регистрации.
       */
      password: '',
      /**
       * Значение повторно набранного пароля для регистрации.
       */
      matchingPassword: '',
      /**
       * Список ошибок валидации введенных данных регистрации.
       */
      errors: [],
    }),
    methods: {
      ...mapActions('auth', ['register']),
      /**
       * Отправка данных на сервер для валидации и завершения регистрации.
       */
      submit() {
        // noinspection JSValidateTypes
        this.register({
          username: this.username,
          email: this.email,
          password: this.password,
          matchingPassword: this.matchingPassword
        }).then(() => {
          this.$router.push("/login");
        }).catch((response) => {
          const errors = response.data.errors;

          this.errors = errors.map(e => {
            let weight;
            let message;
            if (e.code.includes('Empty') || e.code.includes('Null')) {
              weight = 2;
              message = "errors.invalid.empty." + e.field;
            } else {
              weight = 1;
              // noinspection JSUnresolvedVariable
              message = e.defaultMessage;
            }

            return {
              field: e.field ? e.field : e.code,
              weight: weight,
              message: message,
            }
          })
        });
      },
      /**
       * Проверка поля на наличие ошибок, полученных после валидации данных регистрации.
       */
      isError(fieldName) {
        return this.errors.filter(e => e.field === fieldName).length > 0;
      },
      /**
       * Получение значения для локализации ошибки для поля, полученной после валидации
       * данных регистрации. Возвращается значение самой весомой ошибки.
       */
      errorMessage(fieldName) {
        const filteringErrors = this.errors.filter(e => e.field === fieldName);
        if (!filteringErrors.length) {
          return '';
        }

        const sortWeightFilteringErrors = filteringErrors.sort((a, b) => {
          if (a.weight < b.weight) {
            return 1;
          }

          if (a.weight > b.weight) {
            return -1;
          }

          return 0;
        });

        return this.$t(sortWeightFilteringErrors[0].message);
      },
    },
  }
</script>

<style scoped>

</style>