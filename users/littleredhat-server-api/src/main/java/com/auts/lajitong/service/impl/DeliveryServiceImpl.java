package com.auts.lajitong.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.DeliveryMapper;
import com.auts.lajitong.model.dao.LitteredhatDeliveryListModel;
import com.auts.lajitong.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private static final Logger LOGGER = LogManager.getLogger(DeliveryServiceImpl.class);

    @Autowired
    DeliveryMapper mapper;

    @Override
    public List<LitteredhatDeliveryListModel> getListByUser(String userId) throws Exception{
        return mapper.getListByUser(userId);
    }

}
