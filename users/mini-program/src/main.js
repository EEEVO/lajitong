import Vue from 'vue'
import App from './App'
import './styles/index.less'
import store from './store'
import msg from './utils/msg'
import http from './api/http'
import MpvueRouterPatch from 'mpvue-router-patch'


Vue.prototype.$store = store;

Vue.use(msg)
Vue.use(http)
Vue.use(MpvueRouterPatch)

Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue(App)
app.$mount()
