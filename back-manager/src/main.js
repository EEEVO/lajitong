import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store/index';
import './plugins/element';
import common from './lib/common';

Vue.config.productionTip = false;
Vue.use(common);

window.vm = new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
