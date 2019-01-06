package com.auts.ljtmanager.model.dao.device;

public class DeviceWatcherModel {
    private Long id;

    private String deviceId;

    private String gpsLng;

    private String gpsLat;

    private Integer signalIntensit;

    private Integer firmwareVersion;

    private Integer charingVoltage;

    private Integer charingCurrent;

    private Integer dailyCharge;

    private Integer dailyElectricity;

    private Integer mainboardTemperature;

    private Integer batteryTemperature;

    private Integer batteryVoltage;

    private Integer sortSum;

    private String mainboardId;

    private String wsTime;

    private Integer capacity;

    private Integer weight;

    private Integer temperatur;

    private Integer waterLine;

    private Long createTime;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng == null ? null : gpsLng.trim();
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat == null ? null : gpsLat.trim();
    }

    public Integer getSignalIntensit() {
        return signalIntensit;
    }

    public void setSignalIntensit(Integer signalIntensit) {
        this.signalIntensit = signalIntensit;
    }

    public Integer getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(Integer firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Integer getCharingVoltage() {
        return charingVoltage;
    }

    public void setCharingVoltage(Integer charingVoltage) {
        this.charingVoltage = charingVoltage;
    }

    public Integer getCharingCurrent() {
        return charingCurrent;
    }

    public void setCharingCurrent(Integer charingCurrent) {
        this.charingCurrent = charingCurrent;
    }

    public Integer getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(Integer dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public Integer getDailyElectricity() {
        return dailyElectricity;
    }

    public void setDailyElectricity(Integer dailyElectricity) {
        this.dailyElectricity = dailyElectricity;
    }

    public Integer getMainboardTemperature() {
        return mainboardTemperature;
    }

    public void setMainboardTemperature(Integer mainboardTemperature) {
        this.mainboardTemperature = mainboardTemperature;
    }

    public Integer getBatteryTemperature() {
        return batteryTemperature;
    }

    public void setBatteryTemperature(Integer batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
    }

    public Integer getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Integer batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Integer getSortSum() {
        return sortSum;
    }

    public void setSortSum(Integer sortSum) {
        this.sortSum = sortSum;
    }

    public String getMainboardId() {
        return mainboardId;
    }

    public void setMainboardId(String mainboardId) {
        this.mainboardId = mainboardId == null ? null : mainboardId.trim();
    }

    public String getWsTime() {
        return wsTime;
    }

    public void setWsTime(String wsTime) {
        this.wsTime = wsTime == null ? null : wsTime.trim();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(Integer temperatur) {
        this.temperatur = temperatur;
    }

    public Integer getWaterLine() {
        return waterLine;
    }

    public void setWaterLine(Integer waterLine) {
        this.waterLine = waterLine;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}