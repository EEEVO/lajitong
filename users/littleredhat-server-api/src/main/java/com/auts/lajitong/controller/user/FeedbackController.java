package com.auts.lajitong.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auts.lajitong.consts.Const;
import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.model.common.PhiHomeBaseResponse;
import com.auts.lajitong.model.dao.FeedbackModel;
import com.auts.lajitong.model.response.litteredhat.FeedbackListResponseModel;
import com.auts.lajitong.model.response.litteredhat.FeedbackListResponseModel.Feedback;
import com.auts.lajitong.service.FeedbackService;
import com.auts.lajitong.util.DateUtils;
import com.auts.lajitong.util.MyListUtils;
import com.auts.lajitong.util.StringUtil;

/**
 * API 建议与反馈模块
 * 
 * @author libing
 *
 */
@RestController
@CrossOrigin
public class FeedbackController extends SBaseController {
	private static final Logger LOGGER = LogManager.getLogger(FeedbackController.class);

	public static final String DEFAULT_CHARSET = "utf-8";
	public static String HTTP_HEAD_AUTHORIZATION = "Authorization";
	public static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";

	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * 提交反馈接口
	 * @param request
	 * @param userId
	 * @param type
	 * @param phone
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "/users/v1/saveFeedback", method = RequestMethod.POST, produces = { "application/json" })
    public PhiHomeBaseResponse loginAccount(HttpServletRequest request,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "description", required = true) String description) {
        LOGGER.info("saveFeedback userId [{}]", userId);
        PhiHomeBaseResponse rsp = new PhiHomeBaseResponse();
        if (StringUtil.isNullOrEmpty(userId) 
        		|| StringUtil.isNullOrEmpty(type)
        		|| StringUtil.isNullOrEmpty(description)
        		|| StringUtil.isNullOrEmpty(phone)) {
            LOGGER.info("saveFeedback with no parms");
            return errorResponse(Const.ErrorCode.REQUEST_NO_PARAS);
        }

        FeedbackModel model = new FeedbackModel();
        model.setUserId(userId);
        model.setDescription(description);
        model.setPhone(phone);
        model.setType(Integer.parseInt(type));
        model.setStatus(0); //待处理
        try {
			int result = feedbackService.save(model);
			if(result < 1) {
				return errorResponse(102, "添加失败");
			}
		} catch (Exception e) {
			return errorResponse(101, "添加异常");
		}
        return successResponse(rsp);
    }

	/**
	 * 插叙用户的反馈列表
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/users/v1/queryFeedbackByUserId")
	public PhiHomeBaseResponse queryFeedbackByUserId(HttpServletRequest request,
			@RequestParam(value = "userId", required = true) String userId) {

		LOGGER.info("queryFeedbackByUserId userId: [{}]", userId);

		List<Feedback> lists = new ArrayList<Feedback>();
		List<FeedbackModel> result;
		try {
			result = feedbackService.queryFeedbackByUserId(userId);
			if (!MyListUtils.isEmpty(result)) {
				for (FeedbackModel feedbackModel : result) {
					Feedback feedback = new Feedback();
					feedback.setUserId(feedbackModel.getUserId());
					String type = "";
					if(feedbackModel.getType() == 0) {
						type = "建议";
					} else if(feedbackModel.getType() == 1) {
						type = "吐槽";
					} else if(feedbackModel.getType() == 9) {
						type = "其他";
					}
					feedback.setType(type);
					feedback.setDescription(feedbackModel.getDescription());
					feedback.setReply(feedbackModel.getReply());
					feedback.setPhone(feedbackModel.getPhone());
					feedback.setCreateTime(DateUtils.transTime(feedbackModel.getCreateTime()));
					String status = "";
					if(feedbackModel.getType() == 0) {
						status = "待处理";
					} else if(feedbackModel.getType() == 1) {
						status = "已处理";
					}
					feedback.setStatus(status);
					lists.add(feedback);
				}
			}
		} catch (Exception e) {
			LOGGER.warn("queryFeedbackByUserId userId: [{}]", userId);
		}
		
		FeedbackListResponseModel feedbackRspModels = new FeedbackListResponseModel();
		feedbackRspModels.setData(lists);

		PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();
		rspObj.setResult(feedbackRspModels);
		return successResponse(rspObj);
	}
}
