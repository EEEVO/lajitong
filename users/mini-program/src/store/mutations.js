import * as types from './mutations-types'
import {stateTpl} from './state'

const mutations = {
    // 还原vuex
    [types.LOGIN_OUT](state) {
        Object.assign(state, stateTpl)
    },
    // 防止多次连续网络请求
    [types.TOGGLE_REQUEST_STATUS](state, flag) {
        state.isAllowRequest = flag
    }
}

export default mutations;
