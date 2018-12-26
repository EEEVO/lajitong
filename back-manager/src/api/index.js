import Vue from 'vue';

const self = new Vue();
/**
 * 获取订单列表
 * @param pageNumber
 * @param pageSize
 * @param startDate
 * @param endDate
 * @param orderType
 * @returns {Promise<any>}
 */
const queryOrderList = (pageNumber, pageSize, startDate, endDate, orderType) => self.$get('xhmadmin/order/orderlist', {
  pageNumber,
  pageSize,
  startDate,
  endDate,
  orderType,
});

export {
  queryOrderList
};
