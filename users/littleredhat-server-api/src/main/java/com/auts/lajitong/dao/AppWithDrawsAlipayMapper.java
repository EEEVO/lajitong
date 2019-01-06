package com.auts.lajitong.dao;

import org.apache.ibatis.annotations.*;

import com.auts.lajitong.model.dao.AppWithDrawsWeixinPayDaoModel;

import java.util.List;

public interface AppWithDrawsAlipayMapper {

    @Insert(" insert into sw_user_withdraws_weixin(uid,cost,weixin_account,partner_trade_no, " +
            " payment_no , phone, nick_name ,create_time,update_time,withdraw_time,status) "
            + " values(#{uid},#{cost}, #{weixinAccount},#{partnerTradeNo}, #{paymentNo}, " +
            " #{phoneNum}, #{nickname}, #{createTime}, #{updateTime}, #{withdrawTime}, #{status}) ")
    public void addWxWithdrawOrder(AppWithDrawsWeixinPayDaoModel model);

    @Update(" update sw_user_withdraws_weixin set payment_no = #{paymentNo}, " +
            " update_time = #{updateTime}, withdraw_time = #{withdrawTime}, status = 1 " +
            " where uid = #{uid} and partner_trade_no = #{partnerTradeNo} and " +
            " weixin_account = #{weixinAccount} and status = 0")
    public void updateWxWithdrawOrder(AppWithDrawsWeixinPayDaoModel model);

    @Update(" update sw_user_withdraws_weixin set wx_err_code = #{wxErrCode} " +
            " where uid = #{uid} and partner_trade_no = #{partnerTradeNo}  " +
            " and status = 0")
    public void updateWxWithdrawOrderErrCode(@Param("uid") String uid, @Param("partnerTradeNo") String partnerTradeNo, @Param("wxErrCode") String wxErrCode);

    @Select(" select * from sw_user_withdraws_weixin " +
            " where uid = #{uid} and status=0 limit 1 ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "cost", column = "cost"),
            @Result(property = "weixinAccount", column = "weixin_account"),
            @Result(property = "partnerTradeNo", column = "partner_trade_no"),
            @Result(property = "paymentNo", column = "payment_no"),
            @Result(property = "phoneNum", column = "phone"),
            @Result(property = "nickname", column = "nick_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "withdrawTime", column = "withdraw_time"),
            @Result(property = "status", column = "status"),
            @Result(property = "wxErrCode", column = "wx_err_code")
    })
    public List<AppWithDrawsWeixinPayDaoModel> queryProcessingWxWithdrawOrderByUid(@Param("uid") String uid);

}
