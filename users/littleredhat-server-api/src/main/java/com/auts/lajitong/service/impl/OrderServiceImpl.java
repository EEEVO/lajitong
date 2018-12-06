package com.auts.lajitong.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auts.lajitong.dao.OrderMapper;
import com.auts.lajitong.model.dao.OrderModel;
import com.auts.lajitong.model.enums.GarbageTypeEnum;
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
		dto.setOrderId(generateOrderNo());
		dto.setDeviceId(deliveryCard.getMbId());
		dto.setBinNo(deliveryCard.getBinNo() + "");
		dto.setOrderType(deliveryCard.getBinNo() + "");
		dto.setDeliveryTime(deliveryCard.getDeliveryDate());
		dto.setWeight(deliveryCard.getWeight() + "");
		dto.setPrice(getPrice(deliveryCard.getBinNo() + ""));
		String amount = new BigDecimal(deliveryCard.getWeight()).multiply(new BigDecimal("1")).setScale(0).toString();
		dto.setAmount(amount);
		Date nowDate = new Date();
		dto.setCreateTime(nowDate.getTime());
		dto.setUpdateTime(nowDate.getTime());
		
		return dto;
	}
	
	private String getPrice(String orderType) {
		String price = "0";
		if(GarbageTypeEnum.Book.getValue().equals(orderType)) {
			price = "0.5";
		} else if(GarbageTypeEnum.Metal.getValue().equals(orderType)) {
			price = "0.8";
		} else if(GarbageTypeEnum.Plastics.getValue().equals(orderType)) {
			price = "0.9";
		} else if(GarbageTypeEnum.Glass.getValue().equals(orderType)) {
			price = "1.5";
		}
		return price;
	}
	
	private String generateOrderNo() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
    	String orderNo = String.format("%s%s", df.format(new Date()), new Random().nextInt(100));//订单编号
    	
    	return orderNo; 	
    }
}
