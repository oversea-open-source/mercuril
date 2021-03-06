import Vue from 'vue'
import Router from 'vue-router'

import createMessagePage from './views/create_message.vue';
import listMessagePage from './views/list_message.vue';
import addSubscriber from './views/add_subscribe.vue';
import list_log from './views/list_log.vue';
import list_log_detail from './views/list_log_detail.vue';

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
      path: '/message/create',
      component: createMessagePage
    },
    {
      path: '/message/:id',
      component: createMessagePage
    },
    {
      path: '/subscriber/create/:messageQueueId',
      component: addSubscriber
    },
    {
      path: '/subscriber/:messageQueueId/:id',
      component: addSubscriber
    },
    {
      path: '/messagelog',
      component: list_log
    },
    {
      path: '/messagelog/:id',
      component: list_log_detail
    }
  ]
});
