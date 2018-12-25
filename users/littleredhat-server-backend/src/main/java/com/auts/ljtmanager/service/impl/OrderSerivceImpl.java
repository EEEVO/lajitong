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
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.auts.ljtmanager.controller.order.OrderController;
import com.auts.ljtmanager.dao.OrderMapper;
import com.auts.ljtmanager.model.common.PageInfo;
import com.auts.ljtmanager.model.dao.order.OrderModel;
import com.auts.ljtmanager.model.enums.GarbageTypeEnum;
import com.auts.ljtmanager.model.vo.OrderVO;
import com.auts.ljtmanager.service.OrderService;
import com.github.pagehelper.PageHelper;

@Service
public class OrderSerivceImpl implements OrderService {
	private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
	@Autowired
	OrderMapper orderMapper;

	@Override
	public PageInfo queryOrders(String orderType, String startDate, String endDate, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<OrderModel> list = orderMapper.queryOrders(orderType, startDate, endDate);
		LOGGER.info("OrderList1" + JSON.toJSONString(list));
		int total = orderMapper.queryOrdersCnt(orderType, startDate, endDate);
		LOGGER.info("OrderList2" + JSON.toJSONString(total));
		List<OrderVO> resultList = new ArrayList<>();
		if(list != null && list.size() > 0) {
			for(OrderModel orderModel : list) {
				OrderVO orderVO = new OrderVO();
				try {
					BeanUtils.copyProperties(orderVO, orderModel);
					LOGGER.warn("OrderList3"  + JSON.toJSONString(orderVO));
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
					LOGGER.warn("OrderList4" + e.getMessage());
				}
				convertVO(orderVO);
				LOGGER.info("OrderList5"  + JSON.toJSONString(orderVO));
				resultList.add(orderVO);
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

	@Override
	@Transactional
	public void orderSettle(String uid) {
		orderMapper.orderSettle(uid);
	}

	@Override
	@Transactional
	public void orderFailure(String uid) {
		orderMapper.orderFailure(uid);
	}

	@Override
	@Transactional
	public void orderContract(String uid) {
		orderMapper.orderContract(uid);
	}

	@Override
	@Transactional
	public void orderSettled(String uid) {
		orderMapper.orderSettled(uid);
	}

	@Override
	@Transactional
	public void orderPay(String uid) {
		orderMapper.orderPay(uid);
	}

	@Override
	public OrderModel queryOrder(String uid) {
		return orderMapper.queryOrder(uid);
	}

}