import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

/**
 * An object used to store the available system pages and components used to
 * display them, which are dynamically connected.
 */
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
      requiresAuthorization: true,
    },
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
      requiresAuthorization: true,
    },
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
      requiresAuthorization: true,
    },
  },
  {
    path: '/login',
    name: 'Login',
    // lazy-loaded
    component: () => import('../views/ViewLogin'),
    meta: {
      showInMenu: false,
      requiresAuthorization: false,
    },
  },
  {
    path: '/register',
    name: 'Register',
    // lazy-loaded
    component: () => import('../views/ViewRegister'),
    meta: {
      showInMenu: false,
      requiresAuthorization: false,
    },
  },
  {
    path: "*",
    name: "NotFound",
    // lazy-loaded
    component: () => import('../views/ViewNotFound'),
    meta: {
      title: 'navigation.menu.notFound',
      showInMenu: false,
      requiresAuthorization: true,
    },
  },
];

/**
 * Configuration object for configuring routing in the system.
 */
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

/**
 * Checking for the presence of a token before changing the route and if it is
 * not there - redirecting to the authorization page.
 */
router.beforeEach((to, from, next) => {
  if (localStorage.getItem('jwt-token')) {
    if (to.path === '/login' || to.path === '/register') {
      next({path: '/'});
      return;
    }
  } else {
    if (to.path !== '/login' && to.path !== '/register') {
      next({path: '/login'});
      return;
    }
  }

  next();
});

export default router;
