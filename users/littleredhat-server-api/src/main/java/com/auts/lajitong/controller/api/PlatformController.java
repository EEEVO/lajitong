package com.auts.lajitong.controller.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.model.dao.UserModel;
import com.auts.lajitong.model.response.RecordResponse;
import com.auts.lajitong.model.response.VerificationResponse;
import com.auts.lajitong.service.DeviceService;
import com.auts.lajitong.service.OrderService;
import com.auts.lajitong.service.UserService;
import com.auts.lajitong.util.DateUtils;
import com.dls.sdk.util.MainBoardUtil;
import com.dls.sdk.vo.Deliver;
import com.dls.sdk.vo.DeliveryCard;
import com.dls.sdk.vo.MbParseResult;
import com.dls.sdk.vo.ResultDispose;
import com.dls.sdk.vo.Shake;
import com.dls.sdk.vo.Verification;
import com.dls.sdk.vo.VerificationDispose;

/**
 * 对接设备厂商硬件标准接口
 * 1、心跳检测
 * 2、卡号验证
 * 3、投递记录
 *
 * @author lb47917
 * @date 2018年11月6日 下午4:18:07
 */
@Controller
@CrossOrigin
public class PlatformController extends SBaseController {
    private static final Logger LOGGER = LogManager.getLogger(PlatformController.class);
    public static final String ORGANIZATION_ID = "10001";
    public static final String DEFAULT_CHARSET = "utf-8";
    public static String HTTP_HEAD_AUTHORIZATION = "Authorization";
    public static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";

    @Autowired
    DeviceService deviceService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    /**
     * 心跳检测
     * @param request
     * @param pageNo
     * @param pageSize
     * @param status
     * @return
     */
    @RequestMapping(value = "/fl/ws")
    public void shake(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("shake test, time:" + DateUtils.parseDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss")); 
        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			Shake shake = new Shake();
	        MbParseResult<Shake> mbParseResult = shake.buildResult(inputStream);
	        LOGGER.info("shake:" + JSON.toJSONString(mbParseResult));
	        deviceService.saveShake(mbParseResult);

	        String org_id = ORGANIZATION_ID; // 机构编号
	        String[] bucket1 = new String[] {"401", "402"};  
	        String[] bucket2 = new String[] {"403", "404"}; 
	        // 内桶个数对应的垃圾类型
	        String[] buckets = null;
	        if("511560163239595305DCFF35".equals(mbParseResult.mbDataObject.getMbId())) {
	        	buckets = bucket1;
	        } else if("511560273239595305D6FF35".equals(mbParseResult.mbDataObject.getMbId())) {
	        	buckets = bucket2;
	        } else {
	        	buckets = bucket1;
	        }
	        LOGGER.info("shake response：" + mbParseResult.mbDataObject.getMbId() + "==" + JSON.toJSONString(buckets));
	        int[] res = ResultDispose.returnResints(org_id, buckets);
	        byte[] resData = MainBoardUtil.toByteArray(res);
	        //响应主板
	        OutputStream out = response.getOutputStream();
	        out.write(resData);
	        out.flush();

		} catch (Exception e) {
			LOGGER.warn("shake Exception:" + e.getMessage());
		}
        
    }

    /**
     * 卡号验证
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/fl/verification")
    public void verification(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("verification test, time:" + DateUtils.parseDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			Verification verification = new Verification();
			MbParseResult<Verification> mbParseResult=verification.buildResult(inputStream);
			LOGGER.info("verification:" + JSON.toJSONString(mbParseResult));
			LOGGER.info("verification: cardNO:[{}]", mbParseResult.mbDataObject.getCardNumber());

			String cardNumber = mbParseResult.mbDataObject.getCardNumber();  // 卡号
			boolean result = true;  // 验证结果
			UserModel userModel = userService.queryUserByUserid(cardNumber);
			if(userModel == null) {
				result =false;
			}
			byte[] bytes = VerificationDispose.replyDelivery(cardNumber, result);
			        
			OutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();


		} catch (Exception e) {
			LOGGER.warn("verification Exception:" + e.getMessage());
		} 
    }

    /**
     * 投递记录
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/fl/record")
    public void record(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("record test, time:" + DateUtils.parseDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			Deliver deliver = new Deliver(); 
			MbParseResult<Deliver> mbParseResult = deliver.buildResult(inputStream);
			LOGGER.info("record:" + JSON.toJSON(mbParseResult));
			// 取出投递记录
			List<DeliveryCard> deliveryCardList = mbParseResult.mbDataObject.getFenleiDeliveryCardList();
			if(deliveryCardList == null || deliveryCardList.size() < 1) {
				LOGGER.warn("record is null");
			} else {
				String result = orderService.saveOrder(deliveryCardList);
				if(result == null) {
					LOGGER.warn("record failure");
				} else {
					LOGGER.warn("record success，orderNO：  [{}]", result);
				}
			}
			
			String org_id = ORGANIZATION_ID; // 机构编号
	        String[] bucket1 = new String[] {"401", "402"};  
	        String[] bucket2 = new String[] {"403", "404"}; 
	        // 内桶个数对应的垃圾类型
	        String[] buckets = null;
	        if("511560163239595305DCFF35".equals(mbParseResult.mbDataObject.getMbId())) {
	        	buckets = bucket1;
	        } else if("511560273239595305D6FF35".equals(mbParseResult.mbDataObject.getMbId())) {
	        	buckets = bucket2;
	        } else {
	        	buckets = bucket1;
	        }
	        LOGGER.info("record response：" + mbParseResult.mbDataObject.getMbId() + "==" + JSON.toJSONString(buckets));
			int[] resBytes = ResultDispose.returnResints(org_id, buckets);
			        
			byte[] resData = MainBoardUtil.toByteArray(resBytes);
			OutputStream out = response.getOutputStream();
			out.write(resData);
			out.flush();
			
		} catch (Exception e) {
			LOGGER.warn("record Exception:" + e.getMessage());
		}
        
    }
    
    /**
     * 卡号验证
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/fl/testverification")
    public void testverification(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("verification test, time:" + DateUtils.parseDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			String requestData = IOUtils.toString(inputStream);
			VerificationResponse mbParseResult = JSON.parseObject(requestData, VerificationResponse.class);
			LOGGER.info("verification:" + JSON.toJSONString(mbParseResult));
			LOGGER.info("verification: cardNO:[{}]", mbParseResult.mbDataObject.getCardNumber());

			String cardNumber = mbParseResult.mbDataObject.getCardNumber();  // 卡号
			boolean result = true;  // 验证结果
			UserModel userModel = userService.queryUserByUserid(cardNumber);
			if(userModel == null) {
				result =false;
			}
			LOGGER.info("verification result:" + result);
			byte[] bytes = VerificationDispose.replyDelivery(cardNumber, result);
			        
			OutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
			LOGGER.warn("verification Exception:" + e.getMessage());
		} 
    }
    
    /**
     * 投递记录
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/fl/testrecord")
    public void testRecord(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.info("test record test, time:" + DateUtils.parseDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			String requestData = IOUtils.toString(inputStream);
			RecordResponse mbParseResult = JSON.parseObject(requestData, RecordResponse.class);
//			Deliver deliver = new Deliver(); 
//			MbParseResult<Deliver> mbParseResult = deliver.buildResult(inputStream);
			LOGGER.info("record:" + JSON.toJSON(mbParseResult));
			// 取出投递记录
			List<DeliveryCard> deliveryCardList = mbParseResult.mbDataObject.getFenleiDeliveryCardList();
			if(deliveryCardList == null || deliveryCardList.size() < 1) {
				LOGGER.warn("record is null");
			} else {
				String result = orderService.saveOrder(deliveryCardList);
				if(result == null) {
					LOGGER.warn("record failure");
				} else {
					LOGGER.warn("record success，orderNO：  [{}]", result);
				}
			}
			
			String org_id = ORGANIZATION_ID; // 机构编号
			String[] buckets = new String[] {"401", "402", "403", "404"};  // 内桶个数对应的垃圾类型
			int[] resBytes = ResultDispose.returnResints(org_id, buckets);
			        
			byte[] resData = MainBoardUtil.toByteArray(resBytes);
			OutputStream out = response.getOutputStream();
			out.write(resData);
			out.flush();
			
		} catch (Exception e) {
			LOGGER.warn("record Exception:" + e.getMessage());
		}
        
    }
}
