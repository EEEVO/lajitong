package com.auts.lajitong.service;

import java.util.List;

import com.auts.lajitong.model.dao.FeedbackModel;

public interface FeedbackService {
	
	int save(FeedbackModel record)  throws Exception;
	
	int update(FeedbackModel record) throws Exception;
	
	List<FeedbackModel> queryFeedbackByUserId(String userId) throws Exception;
}
