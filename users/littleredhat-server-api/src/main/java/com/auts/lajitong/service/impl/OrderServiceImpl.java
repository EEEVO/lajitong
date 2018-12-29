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
import com.auts.lajitong.dao.UserMapper;
import com.auts.lajitong.dao.WithdrawMapper;
import com.auts.lajitong.model.dao.OrderModel;
import com.auts.lajitong.model.dao.UserModel;
import com.auts.lajitong.model.dao.WithdrawModel;
import com.auts.lajitong.model.enums.GarbageTypeEnum;
import com.auts.lajitong.service.OrderService;
import com.dls.sdk.vo.DeliveryCard;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;
	@Autowired
	WithdrawMapper withdrawMapper;
	@Autowired
    UserMapper userMapper;
	@Transactional
	@Override
	public String saveOrder(List<DeliveryCard> deliveryCardList) throws Exception {
		//1、订单表
		DeliveryCard deliveryCard = deliveryCardList.get(0);
		OrderModel record = convertOrderDTO(deliveryCard);
		int result = orderMapper.insert(record);
		if(result > 0) {
			// 2、金额明细表
			WithdrawModel model = generateWithdraw(record);
			withdrawMapper.insertWithdraw(model);
			// 3、用户表，更新累计金额 投递次数加1
			UserModel userModel = userMapper.queryUserByUserid(record.getUserId());
			BigDecimal  newTotalProfit = new BigDecimal(userModel.getTotalProfit()).add(new BigDecimal(record.getAmount()));
			BigDecimal  newCurrentProfit = new BigDecimal(userModel.getCurrentProfit()).add(new BigDecimal(record.getAmount()));
			userMapper.updateTotalProfit(userModel.getId(), newTotalProfit.toString(), newCurrentProfit.toString());
			return record.getOrderId();
		} else {
			return null;
		}
	}
	
	private WithdrawModel generateWithdraw(OrderModel record) {
		WithdrawModel model = new  WithdrawModel();
		model.setUserId(record.getUserId());
		model.setAmount(record.getAmount());
		model.setWithdrawType(2); //投递收益
		model.setOrderNo(record.getOrderId());
		model.setStatus(0); // 初始状态
		model.setReason("投递收益");
		Date nowDate = new Date();
		model.setCreateTime(nowDate.getTime());
		return model;
	}
	
	private OrderModel convertOrderDTO(DeliveryCard deliveryCard) {
		OrderModel dto = new OrderModel();
		dto.setOrderId(generateOrderNo());
		dto.setUserId(deliveryCard.getCardNo().trim());
		dto.setDeviceId(deliveryCard.getMbId());
		dto.setBinNo(deliveryCard.getBinNo() + "");
		dto.setOrderType(deliveryCard.getBinNo() + "");
		dto.setDeliveryTime(deliveryCard.getDeliveryDate());
		dto.setWeight(deliveryCard.getWeight() + "");
		String price = getPrice(deliveryCard.getBinNo() + "");
		dto.setPrice(price);
		//amout = wt * 10g(单位) * price (KG)
		String amount = new BigDecimal(deliveryCard.getWeight()).multiply(new BigDecimal(price)).divide(new BigDecimal(1000)).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
		dto.setAmount(amount);
		Date nowDate = new Date();
		dto.setCreateTime(nowDate.getTime());
		dto.setUpdateTime(nowDate.getTime());
		
		return dto;
	}
	
	private String getPrice(String orderType) {
		String price = "0";
		if(GarbageTypeEnum.Book.getValue().equals(orderType)) {
			price = "0.9";
		} else if(GarbageTypeEnum.Metal.getValue().equals(orderType)) {
			price = "0.8";
		} else if(GarbageTypeEnum.Plastics.getValue().equals(orderType)) {
			price = "0.9";
		} else if(GarbageTypeEnum.Fabric.getValue().equals(orderType)) {
			price = "0.4";
		}
		return price;
	}
	
	private static String generateOrderNo() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//设置日期格式
    	int i = (int)(Math.random()*900 + 100);
    	String threeRandomStr = Integer.toString(i);
    	String orderNo = String.format("%s%s", df.format(new Date()), threeRandomStr);//订单编号
    	
    	return orderNo; 	
    }
	
	public static void main(String[] args) {
		for(int i = 0; i< 100; i++)
			System.out.println(generateOrderNo());
	}
}
