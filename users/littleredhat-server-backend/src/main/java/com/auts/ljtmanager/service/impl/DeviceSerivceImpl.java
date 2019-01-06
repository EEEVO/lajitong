package com.auts.ljtmanager.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.auts.ljtmanager.controller.order.OrderController;
import com.auts.ljtmanager.dao.DeviceBinWatcherMapper;
import com.auts.ljtmanager.dao.DeviceWatcherMapper;
import com.auts.ljtmanager.model.common.PageInfo;
import com.auts.ljtmanager.model.dao.device.DeviceBinWatcherModel;
import com.auts.ljtmanager.model.dao.device.DeviceWatcherModel;
import com.auts.ljtmanager.model.dao.order.OrderModel;
import com.auts.ljtmanager.model.enums.GarbageTypeEnum;
import com.auts.ljtmanager.model.vo.DeviceVO;
import com.auts.ljtmanager.model.vo.OrderVO;
import com.auts.ljtmanager.service.DeviceService;
import com.github.pagehelper.PageHelper;

@Service
public class DeviceSerivceImpl implements DeviceService {
	private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
	@Autowired
	DeviceWatcherMapper deviceWatcherMapper;
	@Autowired
	DeviceBinWatcherMapper deviceBinWatcherMapper;

	@Override
	public PageInfo queryDevices(String deviceId, String startDate, String endDate, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<DeviceWatcherModel> list = deviceWatcherMapper.queryDevices(deviceId, startDate, endDate);
		int total = deviceWatcherMapper.queryDevicesCnt(deviceId, startDate, endDate);
		List<DeviceVO> resultList = new ArrayList<>();
		if(list != null && list.size() > 0) {
			for(DeviceWatcherModel deviceWatcherModel : list) {
				DeviceVO deviceVO = new DeviceVO();
				try {
					BeanUtils.copyProperties(deviceVO, deviceWatcherModel);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				
				List<DeviceBinWatcherModel> deviceBin = deviceBinWatcherMapper.selectDeviceBinByFId(deviceWatcherModel.getId());
//				convertVO(orderVO);
				resultList.add(deviceVO);
			}
		}
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNumber(pageNumber);
		pageInfo.setPageSize(pageSize);
		pageInfo.setDataList(resultList);
		pageInfo.setTotal(total);
		return pageInfo;
	}

	private void convertVO(OrderVO orderVO) {
		orderVO.setDeliveryTime(orderVO.getDeliveryTime());
		orderVO.setOrderType(GarbageTypeEnum.valueToEnum(orderVO.getOrderType()).getText());
    	String newprice = new BigDecimal(orderVO.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    	orderVO.setPrice(newprice);
	}
}