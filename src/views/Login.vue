<script setup>
import authV1MaskDark from '@/assets/images/pages/auth-v1-mask-dark.png'
import authV1MaskLight from '@/assets/images/pages/auth-v1-mask-light.png'
import authV1Tree2 from '@/assets/images/pages/authv1tree2.png'
import authV1Tree from '@/assets/images/pages/auth-v1-tree.png'
import {computed, ref} from "vue";
import requestApi from "@/api/requestApi";
import { useRouter } from 'vue-router'
const router = useRouter()
const form = ref({
  email: '',
  password: '',
  remember: false,
})

let loginMsg=ref("xxx")
let snackbar = ref(false)

const isPasswordVisible = ref(false)

const login=()=>{
  requestApi.login(form.value)
    .then(res => {
      if(res.data.code===0) {
        localStorage.setItem('loginFlag',JSON.stringify(res.data.data));
        loginMsg = "登陆成功"
        snackbar.value = true
        setTimeout(() => {
          router.push({name: 'Home'})
        }, 1000)
      }
      else{
        loginMsg = "登陆失败: "+res.data.msg
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
          <VCardText class="pt-2">
            <h5 class="text-h5 font-weight-semibold mb-1">
<!--              Welcome to <br>-->
<!--              GeoDataResource! 👋🏻-->
              异构流域水文模拟资源的结构化描述与表达管理系统
            </h5>
            <p class="mb-0">
              Please sign-in to your account and start the adventure
            </p>
          </VCardText>

          <VCardText>
            <VForm @submit.prevent="() => {}">
              <VRow>
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

                  <!-- remember me checkbox -->
                  <div class="d-flex align-center justify-space-between flex-wrap mt-1 mb-4">
                    <VCheckbox
                      v-model="form.remember"
                      label="Remember me"
                    />

                    <a
                      class="ms-2 mb-1"
                      href="javascript:void(0)"
                    >
                      Forgot Password?
                    </a>
                  </div>

                  <!-- login button -->
                  <VBtn
                    block
                    @click="login()"
                  >
                    Login
                  </VBtn>
                </VCol>

                <v-snackbar
                  v-model="snackbar"
                  :timeout="2000"
                >
                  {{ loginMsg }}

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

                <!-- create account -->
                <VCol
                  cols="12"
                  class="text-center text-base"
                >
                  <span>New on our platform?</span>
                  <RouterLink
                    class="text-primary ms-2"
                    to="Register"
                  >
                    Create an account
                  </RouterLink>
                </VCol>

                <VCol
                  cols="12"
                  class="d-flex align-center"
                >
                  <VDivider/>
                  <!--                <span class="mx-4">or</span>-->
                  <VDivider/>
                </VCol>

                <!-- auth providers -->
                <!--            <VCol-->
                <!--              cols="12"-->
                <!--              class="text-center"-->
                <!--            >-->
                <!--              <AuthProvider />-->
                <!--            </VCol>-->
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
