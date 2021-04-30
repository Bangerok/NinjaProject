<template>
  <v-container class="full-height" fluid>
    <v-layout justify-center align-center>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="4">
          <v-card height="100%" class="elevation-12">
            <v-toolbar flat>
              <v-spacer/>
              <v-toolbar-title>{{ $t('pages.auth.register.formName') }}</v-toolbar-title>
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
                    :error-count="5"
                    :append-icon="isShowPassword ? 'fa-eye' : 'fa-eye-slash'"
                    :type="isShowPassword ? 'text' : 'password'"
                    @click:append="isShowPassword = !isShowPassword"
                    prepend-icon="fa-lock"
                />

                <v-text-field
                    v-model="matchingPassword"
                    :label="$t('pages.auth.register.matchingPassword')"
                    :error="isError('PasswordMatches')"
                    :error-messages="errorMessage('matchingPassword')"
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
 * User registration page.
 */
export default {
  name: "ViewRegister",
  data: () => ({
    /**
     * Show/hide the password entered by the user.
     */
    isShowPassword: false,
    /**
     * Email for registration.
     */
    email: null,
    /**
     * Username for registration.
     */
    username: null,
    /**
     * Password for registration.
     */
    password: '',
    /**
     * Matching password for registration.
     */
    matchingPassword: '',
    /**
     * List of validation errors for entered registration data.
     */
    errors: [],
  }),
  methods: {
    ...mapActions('auth', ['register']),
    /**
     * Sending data to the server for validation and registration completion.
     */
    submit() {
      // noinspection JSValidateTypes
      this.register({
        username: this.username,
        email: this.email,
        password: this.password,
        matchingPassword: this.matchingPassword,
      }).then(() => {
        this.$router.push("/login");
      }).catch((error) => {
        const errorsData = JSON.parse(error.response.data.message);
        this.errors = errorsData.map(e => {
          return {
            field: e.field,
            message: e['defaultMessage'],
          };
        });
      });
    },
    /**
     * Checking the field for errors received after validation of registration data.
     *
     * @param fieldName checked field.
     * @return {boolean} true if there are errors for this field, otherwise false.
     */
    isError(fieldName) {
      return this.errors.filter(e => e.field === fieldName).length > 0;
    },
    /**
     * Obtaining and localization of errors for a field received after validation of registration
     * data.
     *
     * @param fieldName field for filtering the list of errors received during validation.
     * @return {string|(*|string|VueI18n.LocaleMessages)[]} list of localized errors.
     */
    errorMessage(fieldName) {
      const filteringErrorObjects = this.errors.filter(e => e.field === fieldName);
      if (!filteringErrorObjects.length) {
        return '';
      }

      return filteringErrorObjects.map(e => {
        return fieldName === 'password' ? e.message : this.$t(e.message);
      });
    },
  },
};
</script>

<style scoped>

</style>