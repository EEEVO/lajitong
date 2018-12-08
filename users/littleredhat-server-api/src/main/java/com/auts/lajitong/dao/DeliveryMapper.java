package com.auts.lajitong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auts.lajitong.model.dao.LitteredhatDeliveryListModel;
import com.auts.lajitong.model.dao.UserModel;

/**
 * 订单表，投递记录.
 * @author huangrongwei
 *
 */
public interface DeliveryMapper {

    @Select("select * from tbl_order where user_id=#{user_id}")
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
