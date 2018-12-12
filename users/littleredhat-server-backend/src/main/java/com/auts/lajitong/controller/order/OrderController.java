package com.auts.lajitong.controller.order;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.model.common.PageInfo;
import com.auts.lajitong.model.common.PhiHomeBaseResponse;
import com.auts.lajitong.service.OrderService;

/**
 * 订单管理 API入口
 *
 * @author lb47917
 * @date 2018年12月12日 上午11:24:26
 */
@RestController
@CrossOrigin
public class OrderController extends SBaseController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    public static final String DEFAULT_CHARSET = "utf-8";
    public static String HTTP_HEAD_AUTHORIZATION = "Authorization";
    public static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";

    @Autowired
    OrderService orderService;

    /**
     * 订单查询
     */
    @RequestMapping(value = "/order/orderlist", method = RequestMethod.GET, produces = { "application/json" })
    public PhiHomeBaseResponse queryOrders(HttpServletRequest request,
    		@RequestParam(value="orderType", required=false) String orderType,
    		@RequestParam(value="startDate", required=false) String startDate,
    		@RequestParam(value="endDate", required=false) String endDate,
    		@RequestParam(value="pageNumber", required=true) int pageNumber,
    		@RequestParam(value="pageSize", required=true) int pageSize) {
    	PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();
    	PageInfo pageInfo = orderService.queryOrders(orderType, startDate, endDate, pageNumber, pageSize);
    	rspObj.setResult(pageInfo);
        return successResponse(rspObj);
    }
    
}