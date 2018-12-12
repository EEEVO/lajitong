package com.auts.lajitong.service;

import com.auts.lajitong.model.common.PageInfo;
import com.auts.lajitong.model.dao.order.OrderModel;

public interface OrderService {

	PageInfo queryOrders(String orderType, String startDate, String endDate, int pageNumber, int pageSize);

	/**
     * 完成打款
     */
	void orderSettle(String uid);

	void orderFailure(String uid);

	/**
	 * 完成合同
	 */
	void orderContract(String uid);

	/**
	 * 完成结佣
	 */
	void orderSettled(String uid);

	/**
	 * 完成打款
	 */
	void orderPay(String uid);

	OrderModel queryOrder(String uid);
}
