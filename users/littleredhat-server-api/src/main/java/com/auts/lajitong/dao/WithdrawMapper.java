package com.auts.lajitong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.auts.lajitong.model.dao.WithdrawModel;

/**
 * withdraw dao.
 */
public interface WithdrawMapper {

    @Select("select * from tbl_withdraw where user_id=#{userId} and status=0")
    @Results({
    	@Result(property = "id", column = "id"),
    	@Result(property = "userId", column = "user_id"),
    	@Result(property = "amount", column = "amount"),
    	@Result(property = "withdrawType", column = "withdraw_type"),
    	@Result(property = "orderNo", column = "order_no"),
    	@Result(property = "status", column = "status"),
    	@Result(property = "reason", column = "reason"),
    	@Result(property = "createTime", column = "create_time")
    })
    List<WithdrawModel> getByUid(@Param("userId") String userId);

    @Insert("insert into tbl_withdraw(user_id, amount, withdraw_type, order_no, status, reason, create_time) "
            + "values (#{model.userId}, #{model.amount},#{model.withdrawType},#{model.orderNo},#{model.status},#{model.reason},#{model.createTime})")
    int insertWithdraw(@Param("model") WithdrawModel model);
}
