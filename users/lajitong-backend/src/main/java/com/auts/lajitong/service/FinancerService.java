package com.auts.lajitong.service;

import com.auts.lajitong.model.dao.FinancerModel;

public interface FinancerService {

	FinancerModel queryFinancerByUID(String uid);

	void addFinancer(FinancerModel financer);

	void delFinancer(String uid);

	void btrvFinancer(String uids);

	int editFinancer(FinancerModel financer);

	String queryFinancerIDByUID(String userID);
}