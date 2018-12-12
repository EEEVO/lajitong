import Vue from 'vue';
import Vuex from 'vuex';
// import createLogger from 'vuex/dist/logger';
import app from './modules/app';

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== 'production';

export default new Vuex.Store({
  modules: {
    app,
  },
  strict: debug,
  // plugins: debug ? [createLogger()] : [],
});
