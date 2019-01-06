package com.auts.lajitong.service;


import java.util.List;

import com.auts.lajitong.model.dao.AppWithDrawsWeixinPayDaoModel;

public interface AppWithDrawsAlipayService {

    public void addWxWithdrawOrder(AppWithDrawsWeixinPayDaoModel model);

    public void updateWxWithdrawOrder(AppWithDrawsWeixinPayDaoModel model);

    public void updateWxWithdrawOrderErrCode(String uid, String partnerTradeNo, String wxErrCode);

    public List<AppWithDrawsWeixinPayDaoModel> queryProcessingWxWithdrawOrderByUid(String uid);

}
