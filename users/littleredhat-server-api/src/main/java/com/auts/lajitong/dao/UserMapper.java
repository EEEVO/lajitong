package com.auts.lajitong.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.auts.lajitong.model.dao.UserModel;

/**
 * 用户属性入口.
 * @author huangrongwei
 *
 */
public interface UserMapper {

    @Update("update tbl_user set nick_name = #{nickName}, sex=#{sex} where id=#{id}")
    int updateUser(@Param("id") String id, @Param("nickName") String nickName,@Param("sex") int sex);

    @Update("update tbl_user set nick_name = #{nickName} where id=#{id}")
    int updateUserNickName(@Param("id") String id, @Param("nickName") String nickName);

    @Update("update tbl_user set sex=#{sex} where id=#{id}")
    int updateUserSex(@Param("id") String id, @Param("sex") int sex);

    @Insert("insert into tbl_user (id, account_id, nick_name, sex, status, total_profit, wxs_open_id, create_time) "
            + "values (#{model.id}, #{model.accountId},#{model.nickName},#{model.sex},#{model.status},#{model.totalProfit},#{model.wxsOpenId},#{model.createTime})")
    int addUser(@Param("model") UserModel model);
}
