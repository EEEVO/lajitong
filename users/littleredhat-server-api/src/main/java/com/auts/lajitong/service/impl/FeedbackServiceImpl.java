package com.auts.lajitong.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.FeedbackMapper;
import com.auts.lajitong.model.dao.FeedbackModel;
import com.auts.lajitong.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackMapper mapper;
	
	@Override
	public int save(FeedbackModel record) throws Exception {
		Date nowDate = new Date();
		record.setCreateTime(nowDate.getTime());
		record.setUpdateTime(nowDate.getTime());
		return mapper.insert(record);
	}

	@Override
	public int update(FeedbackModel record) throws Exception {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<FeedbackModel> queryFeedbackByUserId(String userId) throws Exception {
		return mapper.queryFeedbackByUserId(userId);
	}

}
