package com.auts.lajitong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.AppWithDrawsAlipayMapper;
import com.auts.lajitong.model.dao.AppWithDrawsWeixinPayDaoModel;
import com.auts.lajitong.service.AppWithDrawsAlipayService;

import java.util.List;

@Service
public class AppWithDrawsAlipayServiceImpl implements AppWithDrawsAlipayService {

    @Autowired
    private AppWithDrawsAlipayMapper alipayDaoMapper;


    @Override
    public void addWxWithdrawOrder(AppWithDrawsWeixinPayDaoModel model) {
        alipayDaoMapper.addWxWithdrawOrder(model);
    }

    @Override
    public void updateWxWithdrawOrder(AppWithDrawsWeixinPayDaoModel model) {
        alipayDaoMapper.updateWxWithdrawOrder(model);
    }

    @Override
    public void updateWxWithdrawOrderErrCode(String uid, String partnerTradeNo, String wxErrCode) {
        alipayDaoMapper.updateWxWithdrawOrderErrCode(uid, partnerTradeNo, wxErrCode);
    }


    @Override
    public List<AppWithDrawsWeixinPayDaoModel> queryProcessingWxWithdrawOrderByUid(String uid) {
        return alipayDaoMapper.queryProcessingWxWithdrawOrderByUid(uid);
    }
}
