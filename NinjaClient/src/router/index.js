import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    // lazy-loaded
    component: () => import('./../views/General.vue'),
    meta: {
      icon: 'fa-home',
      title: 'navigation.menu.general',
      showInMenu: true,
      requiresAuthorization: true
    }
  },
  {
    path: '/news',
    name: 'News',
    // lazy-loaded
    component: () => import('./../views/InDevelopment'),
    meta: {
      icon: 'fa-rss',
      title: 'navigation.menu.news',
      showInMenu: true,
      requiresAuthorization: true
    }
  },
  {
    path: '/info',
    name: 'Info',
    // lazy-loaded
    component: () => import('./../views/AboutUs'),
    meta: {
      icon: 'fa-info',
      title: 'navigation.menu.info',
      showInMenu: true,
      requiresAuthorization: true
    }
  },
  {
    path: '/login',
    // lazy-loaded
    component: () => import('./../views/Login'),
    meta: {
      showInMenu: false,
      requiresAuthorization: false
    }
  },
  {
    path: "*",
    name: "navigation.menu.notFound",
    // lazy-loaded
    component: () => import('./../views/NotFound'),
    meta: {
      showInMenu: false,
      requiresAuthorization: true
    }
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router
