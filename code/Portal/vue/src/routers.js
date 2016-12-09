import Vue from 'vue'
import Router from 'vue-router'

import createMessagePage from './views/create_message.vue';
import listMessagePage from './views/list_message.vue';

Vue.use(Router);

export default new Router({
  mode: 'hash',
  scrollBehavior: () => ({y: 0}),
  routes: [
    {
      path: '/',
      component: listMessagePage
    },
    {
      path: '/create',
      component: createMessagePage
    },
    {
      path: '/edit/:id',
      component: createMessagePage
    }
  ]
});
