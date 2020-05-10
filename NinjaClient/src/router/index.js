import Vue from 'vue'
import VueRouter from 'vue-router'
import stateNavigation from '../state'

Vue.use(VueRouter);

const routes = stateNavigation.navigation.links;

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router
