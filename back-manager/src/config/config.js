import qs from 'qs';
import axios from 'axios';

// const IP_PORT = {
//   // 接口调试
//   test: ' http://localhost:3000',
//   chencai: 'http://192.168.96.208:8080',
// };

const objHeaders = {
  'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
};

/**
 * ajax初步封装
 *
 * @param {String} url 请求地址
 * @param {Object} params 请求参数
 * @param {String} method 请求方法
 */
function ajaxs({
  url,
  params = {},
  method = 'POST',
  qsStatus = true,
  headers = objHeaders,
} = {}) {
  return new Promise((resolve, reject) => {
    axios({
      withCredentials: true,
      url,
      data: qsStatus ? qs.stringify(Object.assign({}, params)) : params,
      headers,
      method,
      timeout: 200000,
    }).then(
      res => {
        if (res.data.status === 200) {
          resolve(res.data);
        } else {
          // console.log('数据有误');
        }
      },
      err => {
        reject(err);
      },
    );
  });
}

export default ajaxs;
