package com.auts.ljtmanager.service;

import com.auts.ljtmanager.model.common.PageInfo;

public interface FeedbackService {

	PageInfo queryFeedbacks(String status, String startDate, String endDate, int pageNumber, int pageSize);

	/**
     * 回复反馈
     */
	void settleFeedback(String id, String reply);

}
