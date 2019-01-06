package com.auts.lajitong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.BanksMapper;
import com.auts.lajitong.model.dao.BankModel;
import com.auts.lajitong.service.BanksService;

@Service
public class BanksImpl implements BanksService {

    @Autowired
    BanksMapper mapper;

	@Override
	public List<BankModel> queryBankByUserid(String userId) {
		return mapper.queryBankByUserid(userId);
	}

	@Override
	public int addBank(BankModel model) {
		return mapper.addBank(model);
	}

	@Override
	public int updateBank(BankModel model) {
		return mapper.updateBankBy(model);
	}
}
