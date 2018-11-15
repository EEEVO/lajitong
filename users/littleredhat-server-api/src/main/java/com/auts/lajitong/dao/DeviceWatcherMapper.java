package com.auts.lajitong.dao;

import com.auts.lajitong.model.dao.device.DeviceWatcherModel;

public interface DeviceWatcherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceWatcherModel record);

    int insertSelective(DeviceWatcherModel record);

    DeviceWatcherModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceWatcherModel record);

    int updateByPrimaryKey(DeviceWatcherModel record);
}