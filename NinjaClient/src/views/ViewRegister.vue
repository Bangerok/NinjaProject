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
              <v-toolbar-title>{{$t('pages.register.formName')}}</v-toolbar-title>
              <v-spacer/>
            </v-toolbar>
            <v-card-text>
              <v-form>
                <v-text-field
                    :label="$t('pages.register.email')"
                    name="email"
                    prepend-icon="fa-at"
                    type="text"
                    v-model="email"
                ></v-text-field>

                <v-text-field
                    :label="$t('pages.register.username')"
                    name="username"
                    prepend-icon="fa-user"
                    type="text"
                    v-model="username"
                ></v-text-field>

                <v-text-field
                    :label="$t('pages.register.password')"
                    name="password"
                    v-model="password"
                    prepend-icon="fa-lock"
                    :append-icon="isShowPassword ? 'fa-eye' : 'fa-eye-slash'"
                    :type="isShowPassword ? 'text' : 'password'"
                    @click:append="isShowPassword = !isShowPassword"
                ></v-text-field>

                <v-text-field
                    :label="$t('pages.register.matchingPassword')"
                    name="matchingPassword"
                    v-model="matchingPassword"
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
                    <v-btn text color="primary" @click="submit">
                      {{ $t('buttons.registerBtn') }}
                    </v-btn>
                    <v-btn to="/login" text color="teal">
                      {{ $t('buttons.backBtn') }}
                    </v-btn>
                  </v-col>
                  <v-col class="mb-n7 mt-n5">
                    <v-divider class="mb-1"/>
                    <v-btn text
                           href="api/login/google?redirect_uri=http://localhost:8000">
                      <v-icon color="error" class="mr-2">
                        mdi-google
                      </v-icon>
                      Зарегистрируйтесь через Google
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

  export default {
    name: "Register",
    data: () => ({
      isShowPassword: false,
      email: '',
      username: '',
      password: '',
      matchingPassword: '',
    }),
    methods: {
      ...mapActions('auth', ['register']),
      submit() {
        // noinspection JSValidateTypes
        this.register({
          username: this.username,
          email: this.email,
          password: this.password,
          matchingPassword: this.matchingPassword
        });
      },
    },
  }
</script>

<style scoped>

</style>