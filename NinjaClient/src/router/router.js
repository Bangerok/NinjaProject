import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

/**
 * Объект, используемый для хранения доступных страниц системы
 * и используемых для их отображения компонентов, которые подклюачется
 * динамически.
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

/**
 * Конфигурационный объект для настройки роутинга в системе.
 */
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

/**
 * Проверка перед переходом наличия токена и если его нет -
 * перенаправление на страницу авторизации.
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
})

export default router
