import Vue from 'vue'
import Router from 'vue-router'

import indexPage from './views/index.vue';

Vue.use(Router);

export default new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    {
      path: '',
      component: indexPage
    }
  ]
});
