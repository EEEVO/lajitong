package com.auts.lajitong.dao;

import com.auts.lajitong.model.dao.device.DeviceBinWatcher;

public interface DeviceBinWatcherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceBinWatcher record);

    int insertSelective(DeviceBinWatcher record);

    DeviceBinWatcher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceBinWatcher record);

    int updateByPrimaryKey(DeviceBinWatcher record);
}