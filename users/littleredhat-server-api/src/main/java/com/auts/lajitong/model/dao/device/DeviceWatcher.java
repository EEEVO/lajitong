package com.auts.lajitong.model.dao.device;

public class DeviceWatcher {
    private Long id;

    private String deviceId;

    private String gpsLng;

    private String gpsLat;

    private String signalIntensit;

    private String firmwareVersion;

    private String charingVoltage;

    private String charingCurrent;

    private String dailyCharge;

    private String dailyElectricity;

    private String mainboardTemperature;

    private String batteryTemperature;

    private String batteryVoltage;

    private Long sortSum;

    private String mainboardId;

    private String wsTime;

    private String capacity;

    private String weight;

    private String temperatur;

    private String waterLine;

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

    public String getSignalIntensit() {
        return signalIntensit;
    }

    public void setSignalIntensit(String signalIntensit) {
        this.signalIntensit = signalIntensit == null ? null : signalIntensit.trim();
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion == null ? null : firmwareVersion.trim();
    }

    public String getCharingVoltage() {
        return charingVoltage;
    }

    public void setCharingVoltage(String charingVoltage) {
        this.charingVoltage = charingVoltage == null ? null : charingVoltage.trim();
    }

    public String getCharingCurrent() {
        return charingCurrent;
    }

    public void setCharingCurrent(String charingCurrent) {
        this.charingCurrent = charingCurrent == null ? null : charingCurrent.trim();
    }

    public String getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(String dailyCharge) {
        this.dailyCharge = dailyCharge == null ? null : dailyCharge.trim();
    }

    public String getDailyElectricity() {
        return dailyElectricity;
    }

    public void setDailyElectricity(String dailyElectricity) {
        this.dailyElectricity = dailyElectricity == null ? null : dailyElectricity.trim();
    }

    public String getMainboardTemperature() {
        return mainboardTemperature;
    }

    public void setMainboardTemperature(String mainboardTemperature) {
        this.mainboardTemperature = mainboardTemperature == null ? null : mainboardTemperature.trim();
    }

    public String getBatteryTemperature() {
        return batteryTemperature;
    }

    public void setBatteryTemperature(String batteryTemperature) {
        this.batteryTemperature = batteryTemperature == null ? null : batteryTemperature.trim();
    }

    public String getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(String batteryVoltage) {
        this.batteryVoltage = batteryVoltage == null ? null : batteryVoltage.trim();
    }

    public Long getSortSum() {
        return sortSum;
    }

    public void setSortSum(Long sortSum) {
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity == null ? null : capacity.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(String temperatur) {
        this.temperatur = temperatur == null ? null : temperatur.trim();
    }

    public String getWaterLine() {
        return waterLine;
    }

    public void setWaterLine(String waterLine) {
        this.waterLine = waterLine == null ? null : waterLine.trim();
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