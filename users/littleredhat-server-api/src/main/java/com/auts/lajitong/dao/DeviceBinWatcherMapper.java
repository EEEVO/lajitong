package com.auts.lajitong.dao;

import com.auts.lajitong.model.dao.device.DeviceBinWatcherModel;

public interface DeviceBinWatcherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceBinWatcherModel record);

    int insertSelective(DeviceBinWatcherModel record);

    DeviceBinWatcherModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceBinWatcherModel record);

    int updateByPrimaryKey(DeviceBinWatcherModel record);
}