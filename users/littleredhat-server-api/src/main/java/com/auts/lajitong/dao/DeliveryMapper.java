package com.auts.lajitong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.auts.lajitong.model.dao.LitteredhatDeliveryListModel;

/**
 * 订单表，投递记录.
 * @author huangrongwei
 *
 */
public interface DeliveryMapper {

    @Select("select * from tbl_order where user_id=#{user_id}")
    @Results({
    	@Result(property = "id", column = "id"),
    	@Result(property = "order_id", column = "order_id"),
    	@Result(property = "user_id", column = "user_id"),
    	@Result(property = "device_id", column = "device_id"),
    	@Result(property = "bin_no", column = "bin_no"),
    	@Result(property = "order_type", column = "order_type"),
    	@Result(property = "delivery_time", column = "delivery_time"),
    	@Result(property = "weight", column = "weight"),
    	@Result(property = "price", column = "price"),
    	@Result(property = "amount", column = "amount"),
    	@Result(property = "create_time", column = "create_time"),
    	@Result(property = "update_time", column = "update_time")
    })
    public List<LitteredhatDeliveryListModel> getListByUser(String userId);

//    @Update("update tbl_user set nick_name = #{nickName}, sex=#{sex} where id=#{id}")
//    int updateUser(@Param("id") String id, @Param("nickName") String nickName,@Param("sex") int sex);
//
//    @Update("update tbl_user set nick_name = #{nickName} where id=#{id}")
//    int updateUserNickName(@Param("id") String id, @Param("nickName") String nickName);
//
//    @Update("update tbl_user set sex=#{sex} where id=#{id}")
//    int updateUserSex(@Param("id") String id, @Param("sex") int sex);
//
//    @Insert("insert into tbl_user (id, account_id, nick_name, sex, status, total_profit, wxs_open_id, create_time) "
//            + "values (#{model.id}, #{model.accountId},#{model.nickName},#{model.sex},#{model.status},#{model.totalProfit},#{model.wxsOpenId},#{model.createTime})")
//    int addUser(@Param("model") UserModel model);
//
//    @Select("select * from tbl_user where account_id=#{accountId} and status=0 limit 1")
//    @Results({
//        @Result(property = "id", column = "id"),
//        @Result(property = "accountId", column = "account_id"),
//        @Result(property = "nickName", column = "nick_name"),
//        @Result(property = "sex", column = "sex"),
//        @Result(property = "status", column = "status"),
//        @Result(property = "totalProfit", column = "total_profit"),
//        @Result(property = "wxsOpenId", column = "wxs_open_id"),
//        @Result(property = "createTime", column = "create_time")
//    })
//    UserModel queryUserByAccountId(@Param("accountId") String accountId);
}
