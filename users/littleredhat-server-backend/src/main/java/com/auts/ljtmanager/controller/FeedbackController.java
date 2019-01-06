package com.auts.ljtmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auts.ljtmanager.model.common.PageInfo;
import com.auts.ljtmanager.model.common.PhiHomeBaseResponse;
import com.auts.ljtmanager.service.FeedbackService;

/**
 * 意见和反馈管理后台 API入口
 *
 * @author lb47917
 * @date 2018年12月12日 上午11:24:26
 */
@RestController
@CrossOrigin
public class FeedbackController extends SBaseController {
    private static final Logger LOGGER = LogManager.getLogger(FeedbackController.class);

    public static final String DEFAULT_CHARSET = "utf-8";
    public static String HTTP_HEAD_AUTHORIZATION = "Authorization";
    public static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";

    @Autowired
    FeedbackService feedbackService;

    /**
     * 订单查询
     */
    @RequestMapping(value = "/feedback/feedbackList", produces = { "application/json" })
    public PhiHomeBaseResponse queryfeedbackList(HttpServletRequest request,
    		@RequestParam(value="status", required=false) String status,
    		@RequestParam(value="startDate", required=false) String startDate,
    		@RequestParam(value="endDate", required=false) String endDate,
    		@RequestParam(value="pageNumber", required=true) int pageNumber,
    		@RequestParam(value="pageSize", required=true) int pageSize) {
    	LOGGER.info("queryfeedbackList: " + status);
    	PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();
    	PageInfo pageInfo = feedbackService.queryFeedbacks(status, startDate, endDate, pageNumber, pageSize);
    	rspObj.setResult(pageInfo);
        return successResponse(rspObj);
    }

}