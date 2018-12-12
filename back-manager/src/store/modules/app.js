/* eslint-disable no-shadow */
const storage = window.localStorage;

const state = {
  sideBar: !+storage.getItem('sideBarStatus'),
  browserHeaderTitle: '标题',
};

// 计算属性
const getters = {
  sideBar: state => state.sideBar,
  browserHeaderTitle: state => state.browserHeaderTitle,
};

// 提交mutation
const actions = {
  toggleSiderBar({ commit }) {
    commit('TOGGLE_SIDEBAR');
  },
};

//mutation-types
const TOGGLE_SIDEBAR = 'TOGGLE_SIDEBAR';
const SET_BROWSERHEADERTITLE = 'SET_BROWSERHEADERTITLE';

// 更改状态
/*eslint no-param-reassign: ["error", { "props": false }]*/
const mutations = {
  [TOGGLE_SIDEBAR](state) {
    storage.setItem('sideBarStatus', state.sideBar ? 1 : 0);
    state.sideBar = !state.sideBar;
  },
  [SET_BROWSERHEADERTITLE](state, payload) {
    state.browserHeaderTitle = payload.browserHeaderTitle;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
