package com.auts.lajitong.service;

import java.util.List;

import com.dls.sdk.vo.DeliveryCard;

public interface OrderService {
	
	String saveOrder(List<DeliveryCard> deliveryCardList) throws Exception;
}
