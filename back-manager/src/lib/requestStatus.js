import Cookies from 'js-cookie';
import config from './config';

export default function requestStatus(res) {
  if (res.code === 1) {
    this.errorMsg = res.msg;
  } else if (res.code === 10) {
    Cookies.delete('token', config.domain, '/');
    Cookies.delete('realName', config.domain, '/');
    this.$bus.$emit('loginBox');
    this.errorMsg = '登录失效，请重新登录！';
  } else if (res.status === 500) {
    this.errorMsg = '系统异常，请稍后再试';
  } else if (res.status === 404 || res.status === 401 || res.status === 403) {
    this.errorMsg = '网络错误';
  }
  this.$message.error(this.errorMsg);
}
