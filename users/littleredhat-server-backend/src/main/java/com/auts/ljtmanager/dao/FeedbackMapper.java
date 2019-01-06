package com.auts.ljtmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.auts.ljtmanager.model.dao.FeedbackModel;

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
    
    @Select("<script>"
		+ "SELECT * FROM tbl_feedback a "
		+ " WHERE 1=1"
		+ "<if test='status != \"\"'>"
		+ 	" and #{status} = a.status "
		+ "</if>"
//		+ "<if test='startDate != \"\"'>"
//		+ 	" and #{startDate} &lt;= a.create_time "
//		+ "</if>"
//		+ "<if test='endDate != \"\"'>"
//		+ 	" and #{endDate} &gt;= a.create_time "
//		+ "</if>"
		+ " order by create_time desc "
		+ "</script>")
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
    List<FeedbackModel> queryFeedbackByStatus(@Param("status") String status, @Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;
    
	  @Select("<script>"
		+ "SELECT count(*) FROM tbl_feedback a "
		+ " WHERE a.status = #{status} "
//		+ "<if test='startDate != \"\"'>"
//		+ 	" and #{startDate} &lt;= a.order_date "
//		+ "</if>"
//		+ "<if test='endDate != \"\"'>"
//		+ 	" and #{endDate} &gt;= a.order_date "
//		+ "</if>"
		+ "</script>")
//	@Select("SELECT count(*) FROM tbl_feedback")
	int queryFeedbacksCnt(@Param("status") String status, @Param("startDate") String startDate, @Param("endDate") String endDate);
	    
}