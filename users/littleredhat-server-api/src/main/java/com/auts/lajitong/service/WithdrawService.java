package com.auts.lajitong.service;

import java.util.List;

import com.auts.lajitong.model.dao.WithdrawModel;

public interface WithdrawService {
    List<WithdrawModel> getByUserId(String userId);
    int saveWithdraw(WithdrawModel withdrawModel);
}
