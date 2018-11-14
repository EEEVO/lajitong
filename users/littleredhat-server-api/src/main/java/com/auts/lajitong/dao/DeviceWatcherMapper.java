package com.auts.lajitong.dao;

import com.auts.lajitong.model.dao.device.DeviceWatcher;

public interface DeviceWatcherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceWatcher record);

    int insertSelective(DeviceWatcher record);

    DeviceWatcher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceWatcher record);

    int updateByPrimaryKey(DeviceWatcher record);
}