package com.auts.lajitong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.auts.lajitong.model.dao.WithdrawModel;

/**
 * withdraw dao.
 */
public interface WithdrawMapper {

    @Select("select * from tbl_withdraw where user_id=#{userId} and status=0")
    List<WithdrawModel> getByUid(@Param("userId") String userId);

    @Insert("insert into tbl_withdraw(user_id, amount, withdraw_type, status, reason, create_time) "
            + "values (#{model.userId}, #{model.amount},#{model.withdrawType},#{model.status},#{model.reason},#{model.createTime})")
    int insertWithdraw(@Param("model") WithdrawModel model);
}
