package com.auts.lajitong.model.dao;

import com.auts.lajitong.model.common.BaseDaoModel;

/**
 * 微信提现申请对应数据库表tbl_with_draws_weixin
 *
 */
public class AppWithDrawsWeixinPayDaoModel extends BaseDaoModel {

    //斐讯云账号uid
    private String uid;

    //提现金额
    private String cost;

    //微信账号，实际就是openid
    private String weixinAccount;

    //创建时间
    private long createTime;

    //更新时间
    private long updateTime;

    //状态,0未付款，1已付款
    private int status;

    //手机号
    private String phoneNum;

    //昵称
    private String nickname;

    //商户订单号
    private String partnerTradeNo;

    //微信单号
    private String paymentNo;

    //提现时间
    private String withdrawTime;

    //微信transfers接口错误码
    private String wxErrCode;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getWeixinAccount() {
        return weixinAccount;
    }

    public void setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }


    public String getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(String withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public String getWxErrCode() {
        return wxErrCode;
    }

    public void setWxErrCode(String wxErrCode) {
        this.wxErrCode = wxErrCode;
    }
}
