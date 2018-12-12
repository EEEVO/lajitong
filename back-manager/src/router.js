import Vue from 'vue';
import Router from 'vue-router';
import Cookies from 'js-cookie';
import Home from './views/Home.vue';
import ViewContainer from './views/monitor/ViewContainer.vue';
import { setTitle } from './lib/util';
// import config from './lib/config';
import store from './store';

Vue.use(Router);

const Rules = () => import('./views/monitor/Rules.vue');

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    // 首页
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        title: '首页',
        icon: 'el-icon-menu',
      },
    },
    // 折叠菜单
    {
      path: '/monitor',
      name: '二级菜单',
      component: ViewContainer,
      meta: {
        title: '二级菜单',
        icon: 'el-icon-view',
      },
      children: [
        {
          path: '/monitor/rules',
          name: '二级菜单-1',
          component: Rules,
          meta: { title: '二级菜单-1' },
        },
      ],
    },
  ],
});

router.beforeEach((to, from, next) => {
  const browserHeaderTitle = to.name;
  store.commit('SET_BROWSERHEADERTITLE', {
    browserHeaderTitle,
  });
  const token = Cookies.get('token');
  if (!token || token === 'undefined') {
    // window.location.href = config.portalsUrl
  }
  next();
});
router.afterEach(() => {
  const { browserHeaderTitle } = store.getters;
  setTitle(browserHeaderTitle);
});

export default router;
