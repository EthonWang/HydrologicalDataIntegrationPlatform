<script setup>
import { useTheme } from 'vuetify'
import authV1MaskDark from '@/assets/images/pages/auth-v1-mask-dark.png'
import authV1MaskLight from '@/assets/images/pages/auth-v1-mask-light.png'
import authV1Tree2 from '@/assets/images/pages/authv1tree2.png'
import authV1Tree from '@/assets/images/pages/auth-v1-tree.png'
import {ref} from "vue";
import requestApi from "@/api/requestApi";
import { useRouter } from 'vue-router'
const router = useRouter()
const form = ref({
  name: '',
  email: '',
  password: '',
  privacyPolicies: false,
})

const isPasswordVisible = ref(false)

let registerMsg=ref("")
let snackbar = ref(false)

const register=()=> {
  requestApi.register(form.value)
    .then(res => {
      if(res.data.code===0) {
        registerMsg = "注册成功"
        snackbar.value = true
        setTimeout(() => {
          router.push({name: 'Login'})
        }, 1000)
      }
      else{
        registerMsg = "注册失败: "+res.data.msg
        snackbar.value = true
      }
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
}

</script>

<template>
  <v-app>
    <v-main>
      <v-container style="display: flex;justify-content: center;">
    <VCard
      class="auth-card pa-4 pt-10 mt-8"
      max-width="448"
    >
<!--      <VCardItem class="justify-center">-->
<!--        <template #prepend>-->
<!--          <div class="d-flex">-->
<!--            <div v-html="logo" />-->
<!--          </div>-->
<!--        </template>-->

<!--        <VCardTitle class="font-weight-semibold text-2xl text-uppercase">-->
<!--          Materio-->
<!--        </VCardTitle>-->
<!--      </VCardItem>-->

      <VCardText class="pt-2">
        <h5 class="text-h5 font-weight-semibold mb-1">
          Adventure starts here 🚀
        </h5>
        <p class="mb-0">
          Make your data management easy and fun!
        </p>
      </VCardText>

      <VCardText>
        <VForm @submit.prevent="() => {}">
          <VRow>
            <!-- Username -->
            <VCol cols="12">
              <VTextField
                v-model="form.name"
                label="Username"
              />
            </VCol>
            <!-- email -->
            <VCol cols="12">
              <VTextField
                v-model="form.email"
                label="Email"
                type="email"
              />
            </VCol>

            <!-- password -->
            <VCol cols="12">
              <VTextField
                v-model="form.password"
                label="Password"
                :type="isPasswordVisible ? 'text' : 'password'"
                :append-inner-icon="isPasswordVisible ? 'mdi-eye-off-outline' : 'mdi-eye-outline'"
                @click:append-inner="isPasswordVisible = !isPasswordVisible"
              />
              <div class="d-flex align-center mt-1 mb-4">
                <VCheckbox
                  id="privacy-policy"
                  v-model="form.privacyPolicies"
                  inline
                />
                <VLabel
                  for="privacy-policy"
                  style="opacity: 1;"
                >
                  <span class="me-1">I agree to</span>
                  <a
                    href="javascript:void(0)"
                    class="text-primary"
                  >privacy policy & terms</a>
                </VLabel>
              </div>

              <VBtn
                block
                @click="register()"
              >
                Sign up
              </VBtn>
            </VCol>
            <v-snackbar
              v-model="snackbar"
              :timeout="2000"
            >
              {{ registerMsg }}

              <template v-slot:actions>
                <v-btn
                  color="warning"
                  variant="text"
                  @click="snackbar = false"
                >
                  Close
                </v-btn>
              </template>
            </v-snackbar>

            <!-- login instead -->
            <VCol
              cols="12"
              class="text-center text-base"
            >
              <span>Already have an account?</span>
              <RouterLink
                class="text-primary ms-2"
                to="Login"
              >
                Sign in instead
              </RouterLink>
            </VCol>

            <VCol
              cols="12"
              class="d-flex align-center"
            >
              <VDivider />
<!--              <span class="mx-4"></span>-->
              <VDivider />
            </VCol>

            <!-- auth providers -->
            <VCol
              cols="12"
              class="text-center"
            >
<!--              <AuthProvider />-->
            </VCol>
          </VRow>
        </VForm>
      </VCardText>
    </VCard>

    <VImg
      class="auth-footer-start-tree d-none d-md-block"
      :src="authV1Tree"
      :width="250"
      style=" position: absolute;"
    />

    <VImg
      :src="authV1Tree2"
      class="auth-footer-end-tree d-none d-md-block"
      :width="350"
      style=" position: absolute;"
    />

    <!-- bg img -->
    <VImg
      class="auth-footer-mask d-none d-md-block"
      :src="authV1MaskLight"
      style="z-index: -9;    position: absolute;"
    />
      </v-container>
    </v-main>
  </v-app>
</template>

<style lang="scss">
@use "src/@core/scss/pages/page-auth.scss";
</style>

<!--<route lang="yaml">-->
<!--meta:-->
<!--  layout: blank-->
<!--</route>-->
