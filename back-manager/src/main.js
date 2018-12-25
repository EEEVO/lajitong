import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store/index';
import './plugins/element';
import './assets/less/formatter.less';

import ajax from './config/config'; // 引入封装的ajax
import getUrl from './config/getUrl'; // 引入getUrl
import common from './lib/common';

Vue.config.productionTip = false;
Vue.prototype.ajaxs = ajax;
Vue.prototype.getUrl = getUrl;

Vue.use(common);

window.vm = new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
