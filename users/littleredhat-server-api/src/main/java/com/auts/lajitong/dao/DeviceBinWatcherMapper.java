package com.auts.lajitong.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.auts.lajitong.model.dao.device.DeviceBinWatcherModel;

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

    int updateByPrimaryKeySelective(DeviceBinWatcherModel record);

    @Update("update tbl_bin_watcher set sort_no=#{record.sortNo}, "
    		+ "volumn=#{record.volumn}, "
    		+ "sszl=#{record.sszl}, "
    		+ "wd=#{record.wd}, "
    		+ "work_status=#{record.workStatus}, "
    		+ "error_code=#{record.errorCode}, "
    		+ "error_msg=#{record.errorMsg}, "
    		+ "update_time=#{record.updateTime} "
    		+ " where device_id=#{record.deviceId}")
    int updateByPrimaryKey(DeviceBinWatcherModel record);
}