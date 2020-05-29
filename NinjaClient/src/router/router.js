import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    // lazy-loaded
    component: () => import('../views/ViewInDevelopment'),
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
    component: () => import('../views/ViewInDevelopment'),
    meta: {
      icon: 'fa-info',
      title: 'navigation.menu.info',
      showInMenu: true,
      requiresAuthorization: true
    }
  },
  {
    path: '/login',
    name: 'Login',
    // lazy-loaded
    component: () => import('../views/ViewLogin'),
    meta: {
      showInMenu: false,
      requiresAuthorization: false
    }
  },
  {
    path: '/register',
    name: 'Register',
    // lazy-loaded
    component: () => import('../views/ViewRegister'),
    meta: {
      showInMenu: false,
      requiresAuthorization: false
    }
  },
  {
    path: "*",
    name: "NotFound",
    // lazy-loaded
    component: () => import('../views/ViewNotFound'),
    meta: {
      title: 'navigation.menu.notFound',
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
