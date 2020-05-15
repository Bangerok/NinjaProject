import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    // lazy-loaded
    component: () => import('../views/ViewGeneral.vue'),
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
    component: () => import('../views/ViewInDevelopment'),
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
    component: () => import('../views/ViewAboutUs'),
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
    component: () => import('../views/ViewLogin'),
    meta: {
      showInMenu: false,
      requiresAuthorization: false
    }
  },
  {
    path: "*",
    name: "navigation.menu.notFound",
    // lazy-loaded
    component: () => import('../views/ViewNotFound'),
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
