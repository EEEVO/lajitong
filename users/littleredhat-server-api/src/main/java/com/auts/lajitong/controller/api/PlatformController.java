package com.auts.lajitong.controller.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.service.DeviceService;
import com.auts.lajitong.service.OrderService;
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
@RestController
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

        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			Shake shake = new Shake();
	        MbParseResult<Shake> mbParseResult = shake.buildResult(inputStream);
	        LOGGER.info("心跳检测:" + JSON.toJSONString(mbParseResult));
	        System.out.println(mbParseResult);
	        deviceService.saveShake(mbParseResult);

	        String org_id = ORGANIZATION_ID; // 机构编号
	        String[] buckets = new String[] {"401", "402", "403", "404"};  
	        // 内桶个数对应的垃圾类型
	        int[] res = ResultDispose.returnResints(org_id, buckets);
	        byte[] resData = MainBoardUtil.toByteArray(res);
	        //响应主板
	        OutputStream out = response.getOutputStream();
	        out.write(resData);
	        out.flush();

		} catch (Exception e) {
			e.printStackTrace();
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

        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			Verification verification = new Verification();
			MbParseResult<Verification> mbParseResult=verification.buildResult(inputStream);
			LOGGER.info("卡号验证:卡号 [{}]", mbParseResult.mbDataObject.getCardNumber());

			String cardNumber = mbParseResult.mbDataObject.getCardNumber();  // 卡号
			boolean result = true;  // 验证结果
			byte[] bytes = VerificationDispose.replyDelivery(cardNumber, result);
			        
			OutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();


		} catch (Exception e) {
			e.printStackTrace();
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

        InputStream inputStream;
		try {
			inputStream = request.getInputStream();
			Deliver deliver = new Deliver(); 
			MbParseResult<Deliver> mbParseResult = deliver.buildResult(inputStream);
			// 取出投递记录
			List<DeliveryCard> deliveryCardList = mbParseResult.mbDataObject.getFenleiDeliveryCardList();
			LOGGER.info("投递记录: [{}]", JSON.toJSON(deliveryCardList));
			if(deliveryCardList == null || deliveryCardList.size() < 1) {
				LOGGER.warn("投递记录为空");
			} else {
				String result = orderService.saveOrder(deliveryCardList);
				if(result == null) {
					LOGGER.warn("投递记录失败");
				} else {
					LOGGER.warn("投递记录成功，订单编号为：  [{}]", result);
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
			e.printStackTrace();
		}
        
    }
}
