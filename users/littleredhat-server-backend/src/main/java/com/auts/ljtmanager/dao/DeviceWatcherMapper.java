package com.auts.ljtmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.auts.ljtmanager.model.dao.device.DeviceWatcherModel;
import com.auts.ljtmanager.model.dao.order.OrderModel;

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

	@Select("SELECT * FROM tbl_device_watcher")
    @Results({
    	@Result(property = "id", column = "id"),
    	@Result(property = "deviceId", column = "device_id"),
    	@Result(property = "createTime", column = "create_time"),
    	@Result(property = "updateTime", column = "update_time")
    })
	List<DeviceWatcherModel> queryDevices(@Param("deviceId") String deviceId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Select("SELECT count(*) FROM tbl_device_watcher")
	int queryDevicesCnt(@Param("deviceId") String deviceId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
    int insertSelective(DeviceWatcherModel record);

    DeviceWatcherModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceWatcherModel record);

    int updateByPrimaryKey(DeviceWatcherModel record);
}