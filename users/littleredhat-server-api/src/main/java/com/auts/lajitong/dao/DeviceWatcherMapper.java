package com.auts.lajitong.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.auts.lajitong.model.dao.device.DeviceWatcherModel;

public interface DeviceWatcherMapper {
    int deleteByPrimaryKey(Long id);

    @Insert("insert into tbl_device_watcher (device_id, gps_lng, gps_lat, signal_intensit, firmware_version, "
    		+ "  charing_voltage, charing_current, daily_charge, daily_electricity, mainboard_temperature,  "
    	    + "  battery_temperature, battery_voltage, sort_sum,  mainboard_id, ws_time, capacity,  "
    	    + "  weight, temperatur, water_line, create_time, update_time) "
            + "values (#{record.deviceId},#{record.gpsLng}, #{record.gpsLat},#{record.signalIntensit},#{record.firmwareVersion}, "
    	    + "#{record.charingVoltage},#{record.charingCurrent},#{record.dailyCharge},#{record.dailyElectricity},#{record.mainboardTemperature},"
    	    + "#{record.batteryTemperature},#{record.batteryVoltage},#{record.sortSum},#{record.mainboardId},#{record.wsTime},#{record.capacity},"
            + "#{record.weight},#{record.temperatur},#{record.waterLine},#{record.createTime},#{record.updateTime})"
            )
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(@Param("record") DeviceWatcherModel record) throws Exception;

    int insertSelective(DeviceWatcherModel record);

    DeviceWatcherModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceWatcherModel record);

    int updateByPrimaryKey(DeviceWatcherModel record);
}