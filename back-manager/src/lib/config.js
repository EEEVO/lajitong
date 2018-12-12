// 生产环境地址
const configProduction = {
  backendBaseUrl: '', //后台地址
  portalsUrl: '',
};
// 测试环境地址
const configTest = {
  backendBaseUrl: '', //后台地址
  portalsUrl: '',
};
// 开发环境地址
const configDev = {
  backendBaseUrl: '', //test后台地址
  portalsUrl: '', //login地址
  domain: 'fjf.com',
};
const config = () => {
  if (process.env.VUE_APP_BUILD_ENV === 'production') {
    return configProduction;
  } else if (process.env.VUE_APP_BUILD_ENV === 'test') {
    return configTest;
  }
  return configDev;
};
export default config();
