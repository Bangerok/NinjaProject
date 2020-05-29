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
                    :rules="nameRules"
                ></v-text-field>

                <v-text-field
                    :label="$t('pages.register.username')"
                    name="username"
                    prepend-icon="fa-user"
                    type="text"
                    v-model="username"
                    :rules="nameRules"
                ></v-text-field>

                <v-text-field
                    :label="$t('pages.register.password')"
                    name="password"
                    v-model="password"
                    :rules="passwordRules"
                    prepend-icon="fa-lock"
                    :append-icon="isShowPassword ? 'fa-eye' : 'fa-eye-slash'"
                    :type="isShowPassword ? 'text' : 'password'"
                    @click:append="isShowPassword = !isShowPassword"
                ></v-text-field>

                <v-text-field
                    :label="$t('pages.register.passwordRepeat')"
                    name="password"
                    v-model="passwordRepeat"
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
                    <v-btn text color="primary" @click="register">
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
  import {mapActions, mapMutations} from "vuex";

  export default {
    name: "Register",
    data: () => ({
      valid: true,
      isShowPassword: false,
      email: '',
      username: '',
      password: '',
      passwordRepeat: '',
      nameRules: [],
      passwordRules: [],
    }),
    created() {
      this.passwordRules = [
        v => !!v || this.$t('pages.register.passwordRequired'),
        v => (v && v.length >= 8 && v.length <= 20) || this.$t(
            'pages.register.isValidPasswordMsg'),
      ];

      this.nameRules = [
        v => !!v || this.$t('pages.register.nameRequired'),
        v => (v && v.length <= 50) || this.$t('pages.register.isValidNameMsg'),
      ];
    },
    methods: {
      ...mapMutations('settings', ['setOptionsNotification']),
      ...mapActions('auth', ['signUp']),
      register() {
        // noinspection JSValidateTypes
        this.signUp({
          email: this.email,
          username: this.username,
          password: this.password
        });
      },
    },
  }
</script>

<style scoped>

</style>