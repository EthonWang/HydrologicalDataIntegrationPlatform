<template>
  <!--  <v-app-bar flat>-->
  <!--    <v-app-bar-title>-->
  <!--      <v-icon icon="mdi-circle-slice-6" />-->

  <!--      Essentials Preset-->
  <!--    </v-app-bar-title>-->
  <!--  </v-app-bar>-->
  <v-app-bar
    class="px-3"
    color="white"
    flat
    density="compact"
  >
    <img
      width="200"
      src="@/assets/logo.svg"
    />
<!--<div><strong>异构流域水文模拟资源的结构化描述与表达管理系统</strong></div>-->
    <v-spacer></v-spacer>

    <v-tabs
      center-active
      color="grey-darken-2"
    >
      <v-tab
        v-for="link in links"
        :key="link.label"
        :to="link.route"
      >
        {{ link.label }}
      </v-tab>
    </v-tabs>
    <v-spacer></v-spacer>

    <v-btn
      v-if="!isLogin"
      color="primary"
      variant="plain"
    >
      {{ label }}
    </v-btn>
    <v-menu v-else>
      <template v-slot:activator="{ props }">
        <v-btn
          class="text-none"
          color="primary"
          v-bind="props"
        >
          {{ label }}
        </v-btn>
      </template>
      <v-list>
        <v-list-item :key="1" value="">
          <v-list-item-title @click="logout">Logout</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>

  </v-app-bar>
</template>

<script setup>
import {computed, onMounted, ref} from 'vue'
import {useRouter} from 'vue-router'

const router = useRouter()

const links = ref([
  {label: '主页', route: "Home"},
  {label: '模型仓库', route: "ModelHub"},
  {label: '数据仓库', route: "DataHub"},
  {label: '脚本仓库', route: "ScriptHub"},
  {label: '编辑器', route: "Editor"},
  {label: '可视化', route: "Visualization"},
  {label: '个人空间', route: "MySpace"},
])

let label = ref("未登录")
let isLogin = ref(false)

onMounted(() => {
  const userInfo = JSON.parse(localStorage.getItem("loginFlag"))
  console.log(userInfo)
  if (userInfo != null) {
    label.value = userInfo.email
    isLogin.value = true
  } else {
    label.value = "未登录"
  }
})

function logout() {
  localStorage.removeItem('loginFlag');
  setTimeout(() => {
    router.push({name: 'Login'})
  }, 1000)

}

</script>
