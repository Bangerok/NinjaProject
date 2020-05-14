import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'navigation.menu.general',
    icon: 'fa-home',
    // lazy-loaded
    component: () => import('./../views/General.vue'),
    meta: {
      showInMenu: true,
    }
  },
  {
    path: '/news',
    name: 'navigation.menu.news',
    icon: 'fa-rss',
    // lazy-loaded
    component: () => import('./../views/InDevelopment.vue'),
    meta: {
      showInMenu: true,
    }
  },
  {
    path: '/info',
    name: 'navigation.menu.info',
    icon: 'fa-info',
    // lazy-loaded
    component: () => import('./../views/AboutUs.vue'),
    meta: {
      showInMenu: true,
    }
  },
  {
    path: '/login',
    // lazy-loaded
    component: () => import('./../views/Login.vue'),
    meta: {
      showInMenu: false,
    }
  },
  {
    path: "*",
    name: "navigation.menu.notFound",
    // lazy-loaded
    component: () => import('./../views/NotFound.vue'),
    meta: {
      showInMenu: false,
    }
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
