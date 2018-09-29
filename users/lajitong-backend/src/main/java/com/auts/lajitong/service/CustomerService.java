package com.auts.lajitong.service;

import java.util.List;

import com.auts.lajitong.model.dao.CustomerModel;

public interface CustomerService {

	int queryCustomerCountByFuid(String financerId);

	List<CustomerModel> queryCustomerByFUID(int pageNo, int pageSize, String financerId);

	List<CustomerModel> queryCustomerForOrder(String financerId);

	void addCustomer(CustomerModel customer);

	void delCustomer(String uid);

	void editCustomer(CustomerModel customer);

	CustomerModel queryCustomerByUid(String uid);
}