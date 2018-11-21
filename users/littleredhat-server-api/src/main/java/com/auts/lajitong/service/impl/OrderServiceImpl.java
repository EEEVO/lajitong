package com.auts.lajitong.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auts.lajitong.dao.OrderMapper;
import com.auts.lajitong.model.dao.OrderModel;
import com.auts.lajitong.service.OrderService;
import com.dls.sdk.vo.DeliveryCard;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;
	
	@Transactional
	@Override
	public String saveOrder(List<DeliveryCard> deliveryCardList) {
		DeliveryCard deliveryCard = deliveryCardList.get(0);
		OrderModel record = convertOrderDTO(deliveryCard);
		int result = orderMapper.insert(record);
		if(result > 0) {
			return record.getOrderId();
		} else {
			return null;
		}
	}

	private OrderModel convertOrderDTO(DeliveryCard deliveryCard) {

		OrderModel dto = new OrderModel();
		dto.setOrderId("");
		dto.setDeviceId(deliveryCard.getMbId());
		dto.setBinNo(deliveryCard.getBinNo() + "");
		dto.setOrderType("");
		dto.setDeliveryTime(deliveryCard.getDeliveryDate());
		dto.setWeight(deliveryCard.getWeight() + "");
		dto.setPrice("1");
		String amount = new BigDecimal(deliveryCard.getWeight()).multiply(new BigDecimal("1")).setScale(0).toString();
		dto.setAmount(amount);
		Date nowDate = new Date();
		dto.setCreateTime(nowDate.getTime());
		dto.setUpdateTime(nowDate.getTime());
		
		return dto;
	}
}
