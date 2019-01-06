package com.auts.lajitong.service;

import java.util.List;

import com.auts.lajitong.model.dao.BankModel;

public interface BanksService {
	List<BankModel> queryBankByUserid(String userId);
   
    int addBank(BankModel model);
    int updateBank(BankModel model);
}
