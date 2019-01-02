package com.auts.lajitong.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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

    @Update("update tbl_device_watcher set gps_lng=#{record.gpsLng}, "
    		+ "gps_lng=#{record.gpsLng}, "
    		+ "gps_lat=#{record.gpsLng}, "
    		+ "signal_intensit=#{record.signalIntensit}, "
    		+ "firmware_version=#{record.firmwareVersion}, "
    		+ "charing_voltage=#{record.charingVoltage}, "
    		+ "charing_current=#{record.charingCurrent}, "
    		+ "daily_charge=#{record.dailyCharge}, "
    		+ "daily_electricity=#{record.dailyElectricity}, "
    		+ "mainboard_temperature=#{record.mainboardTemperature}, "
    		+ "battery_temperature=#{record.batteryTemperature}, "
    		+ "battery_voltage=#{record.batteryVoltage}, "
    		+ "sort_sum=#{record.sortSum}, "
    		+ "mainboard_id=#{record.mainboardId}, "
    		+ "ws_time=#{record.wsTime}, "
    		+ "capacity=#{record.capacity}, "
    		+ "weight=#{record.weight}, "
    		+ "temperatur=#{record.temperatur}, "
    		+ "water_line=#{record.waterLine}, "
    		+ "update_time=#{record.updateTime} "
    		+ " where device_id=#{record.deviceId}")
    int updateByPrimaryKey(DeviceWatcherModel record);
}