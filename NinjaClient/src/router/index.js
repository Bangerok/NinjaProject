import Vue from 'vue'
import VueRouter from 'vue-router'
import index from '../state'

Vue.use(VueRouter);

const routes = index.navigation.links;

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
});

export default router
