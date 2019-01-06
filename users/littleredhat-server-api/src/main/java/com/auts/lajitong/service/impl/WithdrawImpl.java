package com.auts.lajitong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.WithdrawMapper;
import com.auts.lajitong.model.dao.WithdrawModel;
import com.auts.lajitong.service.WithdrawService;

@Service
public class WithdrawImpl implements WithdrawService {

    @Autowired
    WithdrawMapper mapper;

    @Override
    public List<WithdrawModel> getByUserId(String userId) {
        return mapper.getByUid(userId);
    }

	@Override
	public int saveWithdraw(WithdrawModel withdrawModel) {
		return mapper.insertWithdraw(withdrawModel);
	}
}
