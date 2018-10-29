export default {
    install(Vue, option) {
        Vue.prototype.$toast = (txt, icon, duration) => {
            wx.showToast({
                title: txt || "网络异常，请重试！",
                icon: icon ? icon : "none",
                duration: duration ? duration : 2000
            });
        };

        Vue.prototype.$showloading = (params, mask) => {
            if (Object.prototype.toString.call(params) === "[object Object]") {
                if (wx.showLoading) {
                    // 基础库 1.1.0 微信6.5.6版本开始支持，低版本需做兼容处理
                    wx.showLoading({
                        title: params.title || "请稍候",
                        mask: params.mask
                    });
                } else {
                    // 低版本采用Toast兼容处理并将时间设为20秒以免自动消失
                    wx.showToast({
                        title: params.title || "请稍候",
                        icon: "loading",
                        mask: params.mask,
                        duration: 20000
                    });
                }
            } else {
                if (wx.showLoading) {
                    // 基础库 1.1.0 微信6.5.6版本开始支持，低版本需做兼容处理
                    wx.showLoading({
                        title: params || "请稍候",
                        mask
                    });
                } else {
                    // 低版本采用Toast兼容处理并将时间设为20秒以免自动消失
                    wx.showToast({
                        title: params || "请稍候",
                        icon: "loading",
                        mask,
                        duration: 20000
                    });
                }
            }
        };

        Vue.prototype.$hideloading = () => {
            if (wx.hideLoading) {
                // 基础库 1.1.0 微信6.5.6版本开始支持，低版本需做兼容处理
                wx.hideLoading();
            } else {
                wx.hideToast();
            }
        };
    }
};
