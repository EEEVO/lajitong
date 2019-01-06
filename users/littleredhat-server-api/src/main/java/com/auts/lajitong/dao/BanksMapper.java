package com.auts.lajitong.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.auts.lajitong.model.dao.BankModel;

/**
 * @author huangrongwei
 *
 */
public interface BanksMapper {

    @Update("update tbl_banks set bankname = #{model.nickName}, bankno=#{model.bankno} username=#{model.username} where id=#{model.id}")
    int updateBankBy(@Param("model") BankModel model);

    @Insert("insert into tbl_banks (user_id, bankname, bankno, username, status, create_time) "
            + "values (#{model.user_id}, #{model.bankname},#{model.bankno},#{model.username},#{model.status},#{model.create_time})")
    int addBank(@Param("model") BankModel model);

    @Select("select * from tbl_banks where user_id=#{userId} and status=0 limit 1")
    BankModel queryBankByUserid(@Param("userId") String userId);
}
