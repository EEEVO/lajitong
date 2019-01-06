package com.auts.ljtmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.auts.ljtmanager.model.dao.device.DeviceWatcherModel;

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

    @Select("<script>"
		+ "SELECT * FROM tbl_device_watcher a "
		+ " WHERE 1=1 "
		+ "<if test='deviceId != \"\"'>"
		+ 	" and #{deviceId} = a.device_id "
		+ "</if>"
		+ "</script>")
    @Results({
    	@Result(property = "id", column = "id"),
    	@Result(property = "deviceId", column = "device_id"),
    	@Result(property = "gpsLng", column = "gps_lng"),
    	@Result(property = "gpsLat", column = "gps_lat"),
    	@Result(property = "signalIntensit", column = "signal_intensit"),
    	@Result(property = "firmwareVersion", column = "firmware_version"),
    	@Result(property = "charingVoltage", column = "charing_voltage"),
    	@Result(property = "charingCurrent", column = "charing_current"),
    	@Result(property = "dailyCharge", column = "daily_charge"),
    	@Result(property = "dailyElectricity", column = "daily_electricity"),
    	@Result(property = "mainboardTemperature", column = "mainboard_temperature"),
    	@Result(property = "batteryTemperature", column = "battery_temperature"),
    	@Result(property = "batteryVoltage", column = "battery_voltage"),
    	@Result(property = "sortSum", column = "sort_sum"),
    	@Result(property = "mainboardId", column = "mainboard_id"),
    	@Result(property = "wsTime", column = "ws_time"),
    	@Result(property = "capacity", column = "capacity"),
    	@Result(property = "weight", column = "weight"),
    	@Result(property = "temperatur", column = "temperatur"),
    	@Result(property = "waterLine", column = "water_line"),
    	@Result(property = "createTime", column = "create_time"),
    	@Result(property = "updateTime", column = "update_time")
    })
	List<DeviceWatcherModel> queryDevices(@Param("deviceId") String deviceId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
    @Select("<script>"
		+ "SELECT count(*) FROM tbl_device_watcher a "
		+ " WHERE 1=1 "
		+ "<if test='deviceId != \"\"'>"
		+ 	" and #{deviceId} = a.device_id "
		+ "</if>"
		+ "</script>")
	int queryDevicesCnt(@Param("deviceId") String deviceId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
    int insertSelective(DeviceWatcherModel record);

    DeviceWatcherModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceWatcherModel record);

    int updateByPrimaryKey(DeviceWatcherModel record);
}