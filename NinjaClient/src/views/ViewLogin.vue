<template>
  <v-container class="full-height" fluid>
    <v-layout justify-center align-center>
      <v-row align="center" justify="center">
        <v-col cols="12" sm="8" md="4">
          <v-card height="100%" class="elevation-12">
            <v-toolbar flat>
              <v-spacer/>
              <v-toolbar-title>{{ $t('pages.auth.login.formName') }}</v-toolbar-title>
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
                    <v-btn @click="auth"
                           :disabled="!isValidStrValue(email) || !isValidStrValue(password)" text
                           color="primary">
                      {{ $t('buttons.authBtn') }}
                    </v-btn>
                    <v-btn to="/register" text color="green darken-1">
                      {{ $t('buttons.registerBtn') }}
                    </v-btn>
                  </v-col>
                  <v-col class="mb-n4 mt-n5">
                    <v-divider class="mb-1"/>
                    <v-btn text href="api/login/google?redirect_uri=http://localhost:3000">
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
 * User authorization page.
 */
export default {
  name: "ViewLogin",
  data: () => ({
    /**
     * Show/hide the password entered by the user.
     */
    isShowPassword: false,
    /**
     * Email for user authorization.
     */
    email: null,
    /**
     * Password for user authorization.
     */
    password: null,
  }),
  methods: {
    ...mapActions('auth', ['login']),
    /**
     * User authorization.
     */
    auth() {
      const payload = {
        email: this.email,
        password: this.password,
      };

      // noinspection JSValidateTypes
      this.login(payload);
    },
    /**
     * Validating a string for filling.
     *
     * @param value checked string.
     * @return {boolean} true if the string satisfies the conditions, false otherwise.
     */
    isValidStrValue(value) {
      return value && value.trim().length > 0;
    },
  },
};
</script>

<style scoped>

</style>