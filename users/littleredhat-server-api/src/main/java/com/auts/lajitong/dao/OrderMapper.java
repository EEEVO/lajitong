package com.auts.lajitong.dao;

import com.auts.lajitong.model.dao.OrderModel;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderModel record);

    int insertSelective(OrderModel record);

    OrderModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderModel record);

    int updateByPrimaryKey(OrderModel record);
}