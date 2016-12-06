import Vue from 'vue';
import Element from 'element-ui';
import VueResource from 'vue-resource';

import 'element-ui/lib/theme-default/index.css';

import router from './routers';

Vue.use(Element);
Vue.use(VueResource);


const isDebug = process.env.NODE_ENV !== 'production';

// 开启错误提示
Vue.config.debug = isDebug;

Vue.component('layout', (resolve)=>{
  require(['./components/layout.vue'], resolve);
});


new Vue({
  router
}).$mount('#app');
