export default {
    baseUrlForDev: 'http://47.100.163.14/', // 测试环境

    baseUrlForProd: '', // 生产环境

    // banner
    banner: 'littleredhat//v1/get_banners',

    // 获取验证码
    getCode: 'littleredhat/users/v1/verification',

    // 登录校验
    login: 'littleredhat//users/v1/login',

    // 获取个人信息
    getUserInfo: 'littleredhat/users/v1/infos',

    // 投递记录
    deliveryList: 'littleredhat//v1/delivery/lists',

    // 帮助中心
    helpCenterList: 'littleredhat/users/v1/queryAllHelpCenter',

    // 修改个人信息
    editUserInfo: 'littleredhat/users/v1/modify'

};
