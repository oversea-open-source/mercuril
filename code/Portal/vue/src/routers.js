import Vue from 'vue'
import Router from 'vue-router'

import createMessagePage from './views/create_message.vue';

Vue.use(Router);

export default new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    {
      path: '/',
      component: createMessagePage
    }
  ]
});
