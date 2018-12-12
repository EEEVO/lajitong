import Cookies from 'js-cookie';
import axios from 'axios';
import config from './config';
import { showFullScreenLoading, hideFullScreenLoading } from './loading';

export function getToken() {
  const token = Cookies.get('token');
  return token;
}

export const invalid = () => {
  window.vm.$message('登录失效，请重新登录！');
  // window.vm.$store.dispatch('removeLoginInfo');
  // window.vm.$router.push('/login');
};

const instance = axios.create({
  baseURL: config.backendBaseUrl,
  timeout: 30000,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
});

// 添加请求拦截器
/*eslint no-param-reassign: ["error", { "props": false }]*/
instance.interceptors.request.use(
  axiosConfig => {
    axiosConfig.headers['X-User-Token'] = getToken();
    showFullScreenLoading(); //开始加载动画
    return axiosConfig;
  },
  // 对请求错误做些什么
  error => Promise.reject(error),
);

// 添加响应拦截器
instance.interceptors.response.use(
  // 对响应数据做点什么
  response => {
    // 10 匿名请求
    // 20 拒绝访问
    // 100 已登出
    if (
      response.data.code === 10 ||
      response.data.code === 20 ||
      response.data.code === 100
    ) {
      invalid();
    }
    hideFullScreenLoading(); //结束加载动画
    return response.data;
  },
  // 对响应错误做点什么
  error => {
    hideFullScreenLoading();
    if (error.response) {
      if (error.response.status === 401) {
        invalid();
      } else {
        window.vm.$message('网络异常');
      }
    } else if (error.request) {
      // 超时
      if (error.request.readyState === 4 && error.request.status === 0) {
        window.vm.$message('请求超时');
      } else {
        window.vm.$message('请求失败');
      }
    }
    return error;
  },
);

export default instance;
