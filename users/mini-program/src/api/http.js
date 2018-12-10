import Vue from "vue";
import qs from "qs";
import apiList from "./apiList";
import { allow } from "../utils/stopRequest.js";

let self = new Vue();
const Fly = require("flyio/dist/npm/wx");
const fly = new Fly;

const isDev = process.env.NODE_ENV === "development";

fly.config.baseURL = isDev ? apiList.baseUrlForDev : apiList.baseUrlForProd;

// 需要showloading的接口
const ignoreUrl = [
    apiList.baseInfo
];

// 拦截请求
fly.interceptors.request.use(
    config => {
        if(ignoreUrl.includes(config.url)) {self.$showloading()}
        // 处理各种请求
        config.headers = {
            "content-type": "application/x-www-form-urlencoded"
        };
        return config;
    },
    err => {
        return Promise.reject(err);
    }
);

// 拦截响应
fly.interceptors.response.use(
    res => {
        // 处理何种响应
        self.$hideloading()
        allow();
        // console.log(res);
        return res.data;
    },
    err => {
        return Promise.reject(err);
    }
);

/**
 * 统一处理请求失败
 * @param res
 * @returns {*}
 */
function errorState(err, reject) {
    self.$hideloading();
    allow();
    reject(err);
}

/**
 * 统一处理请求成功
 * @param res 后台原返回值
 * @resolve resolve promise返回函数
 */
function successState(res, resolve) {
    if(res.status !== 200) {
        console.log(res.message);
        self.$toast(res.message);
    }
    resolve(res);
}


export default {
    install(Vue, options) {
        /**
         * 封装post请求
         * @param url 请求url
         * @param params 请求参数
         * @param isForm 请求参数是否以表单格式提交
         * @returns {Promise<any>}
         */
        Vue.prototype.$post = function(url, params, isForm) {

            const commonParams = Object.assign({ t: new Date().getTime() }, params);

            return new Promise((resolve, reject) => {
                fly.post(url, isForm ? qs.stringify(commonParams) : commonParams)
                    .then(res => {
                        successState(res, resolve);
                    })
                    .catch(error => {
                        errorState(error, reject);
                    })
            });
        };
        /**
         * 封装get请求
         * @param url 请求url
         * @param params 请求参数
         * @returns {Promise<any>}
         */
        Vue.prototype.$get = function(url, params) {

            const commonParams = Object.assign({ t: new Date().getTime() },params);

            return new Promise((resolve, reject) => {
                fly.get(url, commonParams)
                    .then(res => {
                        successState(res, resolve);
                    })
                    .catch(err => {
                        errorState(err, reject);
                    })
            });
        };
    }
};
