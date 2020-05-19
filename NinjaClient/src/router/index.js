import Vue from 'vue'
import VueRouter from 'vue-router'
//import store from './../store/index'

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

/*router.beforeEach((to, from, next) => {
  //if (!store.state.auth.user) {
  if (!router.app.$store.state.auth.user) {
    next({name: 'Login'});
  } else {
    next();
  }
})*/

export default router
