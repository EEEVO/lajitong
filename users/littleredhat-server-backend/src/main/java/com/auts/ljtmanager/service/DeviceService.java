package com.auts.ljtmanager.service;

import com.auts.ljtmanager.model.common.PageInfo;

public interface DeviceService {

	PageInfo queryDevices(String deviceId, String startDate, String endDate, int pageNumber, int pageSize);
}
