// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: 'Home',
        name: 'Home',
        meta: {
          needLogin: false //需要加校检判断的路由
        },
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.

        component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
      },
      {
        path: 'ModelHub',
        name: 'ModelHub',
        meta: {
          needLogin: false //需要加校检判断的路由
        },
        component: () => import(/* webpackChunkName: "home" */ '@/views/ModelHub.vue'),
      },
      {
        path: 'DataHub',
        name: 'DataHub',
        component: () => import(/* webpackChunkName: "home" */ '@/views/DataHub.vue'),
      },
      {
        path: 'Editor',
        name: 'Editor',
        meta: {
          needLogin: true //需要加校检判断的路由
        },
        component: () => import(/* webpackChunkName: "home" */ '@/views/Editor.vue'),
      },
      {
        path: 'ScriptHub',
        name: 'ScriptHub',
        meta: {
          needLogin: true //需要加校检判断的路由
        },
        component: () => import(/* webpackChunkName: "home" */ '@/views/ScriptHub.vue'),
      },
      {
        path: 'Visualization',
        name: 'Visualization',
        meta: {
          needLogin: true //需要加校检判断的路由
        },
        component: () => import(/* webpackChunkName: "home" */ '@/views/Visualization.vue'),
      },
      {
        path: 'MySpace',
        name: 'MySpace',
        meta: {
          needLogin: true //需要加校检判断的路由
        },
        component: () => import(/* webpackChunkName: "home" */ '@/views/MySpace.vue'),
      },
    ],
  },
  {
    path: '/Login',
    name: 'Login',
    meta: {
      needLogin: false //需要加校检判断的路由
    },
    component: () => import(/* webpackChunkName: "home" */ '@/views/Login.vue'),
  },
  {
    path: '/Register',
    name: 'Register',
    meta: {
      needLogin: false //需要加校检判断的路由
    },
    component: () => import(/* webpackChunkName: "home" */ '@/views/Register.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach(function(to, from, next) {
  if (to.meta.needLogin) {

    //极简判断
    if (localStorage.getItem("loginFlag")) {
      next();
    } else {
      next({
        name: "Login"
      });
    }
  } else {
    //表示不需要登录
    next(); //继续往后走
  }
})


export default router
