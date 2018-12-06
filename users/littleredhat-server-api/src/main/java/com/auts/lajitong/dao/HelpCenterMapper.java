package com.auts.lajitong.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.auts.lajitong.model.dao.HelpCenter;

public interface HelpCenterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HelpCenter record);

    int insertSelective(HelpCenter record);

    HelpCenter selectByPrimaryKey(Long id);
    
    @Select("select * from tbl_help_center")
    @Results({
    	@Result(property = "id", column = "id"),
    	@Result(property = "title", column = "title"),
    	@Result(property = "content", column = "content"),
    	@Result(property = "createTime", column = "create_time"),
    	@Result(property = "updateTime", column = "update_time")
    })
    List<HelpCenter> queryAllHelpCenter();

    int updateByPrimaryKeySelective(HelpCenter record);

    int updateByPrimaryKey(HelpCenter record);
}