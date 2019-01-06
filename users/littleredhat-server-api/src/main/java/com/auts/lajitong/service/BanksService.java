package com.auts.lajitong.service;

import com.auts.lajitong.model.dao.BankModel;

public interface BanksService {
	BankModel queryBankByUserid(String userId);
   
    int addBank(BankModel model);
    int updateBank(BankModel model);
}
