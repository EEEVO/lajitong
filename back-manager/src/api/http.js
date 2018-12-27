import axios from 'axios';
import qs from 'qs';

// 拦截请求
axios.interceptors.request.use(config => config, err => Promise.reject(err));

// 拦截响应
axios.interceptors.response.use(
  res => res,
  err => {
    if (err.response) {
      //   console.log(err.response);
    }
    return Promise.reject(err);
  },
);

/**
 * 统一处理请求失败
 * @param res
 * @returns {*}
 */
function errorState(err, reject) {
  reject(err);
}

/**
 * 统一处理请求成功
 * @param res
 */
function successState(res, resolve) {
  resolve(res.data);
}

function install(Vue) {
  /**
   * 封装post请求
   * @param url 请求url
   * @param params 请求参数
   * @returns {Promise<any>}
   */
  Vue.prototype.$post = (url, params) => {
    const commonParams = Object.assign({ t: new Date().getTime() }, params);
    return new Promise((resolve, reject) => {
      axios
        .post(url, qs.stringify(commonParams))
        .then(res => {
          successState(res, resolve);
        })
        .catch(error => {
          errorState(error, reject);
        })
        .finally(() => {
          // console.log('finally');
        });
    });
  };
  /**
   * 封装get请求
   * @param url 请求url
   * @param params 请求参数
   * @returns {Promise<any>}
   */
  Vue.prototype.$get = (url, params) => {
    const commonParams = Object.assign({ t: new Date().getTime() }, params);
    return new Promise((resolve, reject) => {
      axios
        .get(url, { params: commonParams })
        .then(res => {
          successState(res, resolve);
        })
        .catch(error => {
          errorState(error, reject);
        })
        .finally(() => {
          // console.log('finally');
        });
    });
  };
}

export default {
  install,
};
