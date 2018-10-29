import store from '../store'
const isForbidden = () => {
    if(!store.state.isAllowRequest) {
        return true
    }
    store.commit('TOGGLE_REQUEST_STATUS',false)
}

const allow = () => {
    setTimeout(() => {
        store.commit('TOGGLE_REQUEST_STATUS',true)
    }, 1500)
}

export {
    isForbidden,
    allow
}
