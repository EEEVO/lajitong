package com.auts.lajitong.service;

import java.util.List;

import com.auts.lajitong.model.dao.LitteredhatDeliveryListModel;

public interface DeliveryService {

    List<LitteredhatDeliveryListModel> getListByUser(String userId);

}
