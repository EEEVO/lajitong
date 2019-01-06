package com.auts.lajitong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.auts.lajitong.model.dao.FeedbackModel;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Long id) throws Exception;

    @Insert("insert into tbl_feedback (user_id, type, description, reply, status, phone, create_time, update_time) "
            + " values (#{record.userId},#{record.type}, #{record.description},#{record.reply},#{record.status}, "
    	    + "#{record.phone},#{record.createTime},#{record.updateTime})"
            )
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(@Param("record") FeedbackModel record) throws Exception;

    int insertSelective(FeedbackModel record) throws Exception;

    FeedbackModel selectByPrimaryKey(Long id) throws Exception;
    
    @Select("select * from tbl_feedback where user_id=#{user_id}")
    @Results({
    	@Result(property = "id", column = "id"),
    	@Result(property = "userId", column = "user_id"),
    	@Result(property = "type", column = "type"),
    	@Result(property = "description", column = "description"),
    	@Result(property = "reply", column = "reply"),
    	@Result(property = "status", column = "status"),
    	@Result(property = "phone", column = "phone"),
    	@Result(property = "createTime", column = "create_time"),
    	@Result(property = "updateTime", column = "update_time")
    })
    List<FeedbackModel> queryFeedbackByUserId(@Param("user_id") String userId) throws Exception;

    int updateByPrimaryKeySelective(FeedbackModel record) throws Exception;

    int updateByPrimaryKey(FeedbackModel record) throws Exception;
}