package com.auts.ljtmanager.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.auts.ljtmanager.controller.order.OrderController;
import com.auts.ljtmanager.dao.FeedbackMapper;
import com.auts.ljtmanager.model.common.PageInfo;
import com.auts.ljtmanager.model.dao.FeedbackModel;
import com.auts.ljtmanager.model.vo.FeedbackVO;
import com.auts.ljtmanager.service.FeedbackService;
import com.auts.ljtmanager.util.DateUtils;
import com.github.pagehelper.PageHelper;

@Service
public class FeedbackSerivceImpl implements FeedbackService {
	private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
	@Autowired
	FeedbackMapper feedbackMapper;

	@Override
	public PageInfo queryFeedbacks(String status, String startDate, String endDate, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		PageInfo pageInfo = new PageInfo();
		List<FeedbackModel> list;
		try {
			list = feedbackMapper.queryFeedbackByStatus(status, startDate, endDate);
			int total = feedbackMapper.queryFeedbacksCnt(status, startDate, endDate);
			List<FeedbackVO> resultList = new ArrayList<>();
			if(list != null && list.size() > 0) {
				for(FeedbackModel feedbackModel : list) {
					FeedbackVO feedbackVO = new FeedbackVO();
					try {
						BeanUtils.copyProperties(feedbackModel, feedbackVO);
					} catch (BeansException e) {
						LOGGER.warn("queryFeedbacks" + e.getMessage());
					}
					convertVO(feedbackVO, feedbackModel);
					LOGGER.info("queryFeedbacks"  + JSON.toJSONString(feedbackVO));
					resultList.add(feedbackVO);
				}
			}
			pageInfo.setPageNumber(pageNumber);
			pageInfo.setPageSize(pageSize);
			pageInfo.setDataList(resultList);
			pageInfo.setTotal(total);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return pageInfo;
	}

	private void convertVO(FeedbackVO feedbackVO, FeedbackModel feedbackModel) {
		
		String type = "";
		if(feedbackModel.getType() == 0) {
			type = "建议";
		} else if(feedbackModel.getType() == 1) {
			type = "吐槽";
		} else if(feedbackModel.getType() == 9) {
			type = "其他";
		}
		feedbackVO.setTypeMsg(type);
		String status = "";
		if(feedbackModel.getType() == 0) {
			status = "待处理";
		} else if(feedbackModel.getType() == 1) {
			status = "已处理";
		}
		feedbackVO.setStatusMsg(status);
		feedbackVO.setCreateTimeStr(DateUtils.transTime(feedbackModel.getCreateTime()));
		feedbackVO.setUpdateTimeStr(DateUtils.transTime(feedbackModel.getUpdateTime()));
	}

	@Override
	public void settleFeedback(String id, String reply) {
		// TODO Auto-generated method stub
		
	}
}