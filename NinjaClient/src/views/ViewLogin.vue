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
              <v-toolbar-title>{{$t('pages.auth.login.formName')}}</v-toolbar-title>
              <v-spacer/>
            </v-toolbar>
            <v-card-text>
              <v-form>
                <v-text-field
                    :label="$t('pages.auth.general.username')"
                    name="login"
                    prepend-icon="fa-user"
                    type="text"
                    v-model="username"
                ></v-text-field>

                <v-text-field
                    :label="$t('pages.auth.general.password')"
                    id="password"
                    name="password"
                    v-model="password"
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
                    <v-btn @click="auth" text color="primary">
                      {{ $t('buttons.authBtn') }}
                    </v-btn>
                    <v-btn to="/register" text color="teal" >
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
  import authApi from "../api/service/auth.service";

  export default {
    name: "Login",
    data: () => ({
      isShowPassword: false,
      username: '',
      password: '',
    }),
    methods: {
      auth() {
        // noinspection JSValidateTypes
        const payload = {
          email: this.username,
          password: this.password
        };

        authApi.login(payload).then(response => {
          localStorage.setItem("jwt-token", response.data.accessToken);
          document.location.replace(new URL(location.href).origin);
        });
      },
    },
  }
</script>

<style scoped>

</style>