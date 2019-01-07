package com.auts.ljtmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.auts.ljtmanager.model.dao.device.DeviceBinWatcherModel;

public interface DeviceBinWatcherMapper {
    int deleteByPrimaryKey(Long id);

    @Insert("insert into tbl_bin_watcher (f_id, device_id,sort_no, volumn, sszl, wd, work_status, "
    		+ "error_code, error_msg, create_time, update_time) "
            + "values (#{record.fId},#{record.deviceId}, #{record.sortNo},#{record.volumn},#{record.sszl},#{record.wd},#{record.workStatus}, "
            + "#{record.errorCode},#{record.errorMsg},#{record.createTime},#{record.updateTime})"
            )
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(@Param("record") DeviceBinWatcherModel record);

    int insertSelective(DeviceBinWatcherModel record);

    DeviceBinWatcherModel selectByPrimaryKey(Long id);
    
    @Select("SELECT * FROM tbl_bin_watcher a WHERE #{fId} = a.f_id")
    @Results({
    	@Result(property = "id", column = "id"),
    	@Result(property = "fId", column = "f_id"),
    	@Result(property = "deviceId", column = "device_id"),
    	@Result(property = "sortNo", column = "sort_no"),
    	@Result(property = "volumn", column = "volumn"),
    	@Result(property = "sszl", column = "sszl"),
    	@Result(property = "wd", column = "wd"),
    	@Result(property = "workStatus", column = "work_status"),
    	@Result(property = "errorCode", column = "error_code"),
    	@Result(property = "errorMsg", column = "error_msg"),
    	@Result(property = "createTime", column = "create_time"),
    	@Result(property = "updateTime", column = "update_time")
    })
    List<DeviceBinWatcherModel> selectDeviceBinByFId(@Param("fId") Long id);

    int updateByPrimaryKeySelective(DeviceBinWatcherModel record);

    int updateByPrimaryKey(DeviceBinWatcherModel record);
}