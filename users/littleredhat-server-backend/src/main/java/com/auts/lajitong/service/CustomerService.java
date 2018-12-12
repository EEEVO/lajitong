package com.auts.lajitong.service;

import com.auts.lajitong.model.common.PageInfo;
import com.auts.lajitong.model.dao.CustomerModel;

public interface CustomerService {

	void addCustomer(CustomerModel customer);

	PageInfo queryCustomerList(String nameSearch, int pageNumber, int pageSize);

	void editCustomer(CustomerModel financer);

	void delCustomer(String uid);

	void btrvCustomer(String uids);

	void handleCancel(String uid);

	void handleNormal(String uid);

	void handleSwitch(String uid);

}
