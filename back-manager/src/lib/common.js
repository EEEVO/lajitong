/* eslint-disable no-param-reassign */
import axiosConfig from './axiosUtils';
import requestStatus from './requestStatus';
import config from './config';

const install = Vue => {
  // eventBus
  Vue.prototype.$bus = new Vue();
  // 参数配置
  Vue.prototype.$config = config;
  // 网络请求
  Vue.prototype.$http = axiosConfig;
  // handle request status
  Vue.prototype.$requestStatus = requestStatus;
  // 导出excel
  Vue.prototype.$exportExcel = (path, fileName, data) => {
    axiosConfig.post(path, data, { responseType: 'blob' }).then(res => {
      const excelUrl = window.URL.createObjectURL(new Blob([res]));
      const link = window.document.createElement('a');
      link.href = excelUrl;
      link.download = `${fileName}.xlsx`;
      link.click();
      window.URL.revokeObjectURL(excelUrl);
      Vue.prototype.$bus.$message({
        message: '导出成功!',
        type: 'success',
      });
    });
  };
};
export default install;
