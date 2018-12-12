import axiosConfig from './axiosUtils';

/**
 * 导出到Excel
 *
 * @export
 * @param {String} path
 * @param {*} data
 * @returns
 */
export function exportExcel(path, data) {
  return new Promise(resolve => {
    axiosConfig.post(path, data).then(res => {
      if (res.code === 0) {
        const link = window.document.createElement('a');
        link.href = res.data.url;
        link.download = res.data.filename;
        link.click();
        window.vm.$message({
          message: '导出成功!',
          type: 'success',
        });
      } else {
        window.vm.$message({
          message: '导出失败!',
          type: 'error',
        });
      }
      resolve(false);
    });
  });
}

/**
 *设置浏览器头部标题
 *
 * @export
 * @param {String} title
 */
export function setTitle(title) {
  const _title = title ? `Admin-${title}` : 'Admin';
  window.document.title = _title;
}
