package com.auts.lajitong.controller.withdraw;

import com.auts.lajitong.consts.Const;
import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.model.common.PhiHomeBaseResponse;
import com.auts.lajitong.model.dao.AppWithDrawsWeixinPayDaoModel;
import com.auts.lajitong.model.dao.UserModel;
import com.auts.lajitong.model.request.UserAppWithDrawsRequestModel;
import com.auts.lajitong.service.UserService;
import com.auts.lajitong.service.impl.AppWithDrawsAlipayServiceImpl;
import com.auts.lajitong.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 提现到微信帐号
 *
 * @author xiangrong.ke
 */
@RestController
@PropertySource("classpath:application.properties")
public class AppWithDraws extends SBaseController {
    private final Logger logger = LoggerFactory.getLogger(AppWithDraws.class);
    @Autowired
    private AppWithDrawsAlipayServiceImpl withDrawsAlipayService;
    
    @Autowired
    private UserService userService;
    
    private static final Object mLock = new Object();
    
    private static final List<String> mWithdrawingUids = new LinkedList<String>();

    @RequestMapping(value = "/with_draws", method = RequestMethod.POST, produces = {"application/json"})
    public PhiHomeBaseResponse withDraws(@RequestBody UserAppWithDrawsRequestModel requestParas) {
    	PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();
    	
    	return null;
//        logger.info("token[{}] withdraw amount[{}]: ", requestParas.getUserId(), requestParas.getAmount());
//
//        if (StringUtil.isNullOrEmpty(requestParas.getUserId())) {
//            logger.error("Failed to get phicomm account");
//            rspObj.setCode(Const.ErrorCode.ERROR_NO_UID);
//            return successResponse(rspObj);
//        }
//
//        try {
//        	boolean processingUid = false;
//        	synchronized (mLock) {
//        		processingUid = mWithdrawingUids.contains(requestParas.getUserId());
//        		if (!processingUid) {
//        			mWithdrawingUids.add(requestParas.getUserId());
//				}
//			}
//        	
//            //获得锁则进行提现
//            if (!processingUid) {
//                //do Action
//                //先查询该uid下有无正在处理的订单,有则用原商户单号和原提现金额进行提现，否则产生新单号来提现
//                List<AppWithDrawsWeixinPayDaoModel> processingOrders = withDrawsAlipayService.queryProcessingWxWithdrawOrderByUid(
//                		requestParas.getUserId());
//                //有两条正在提现记录，属于异常情况，直接返回错误，提示用户联系客服检查确认
//                if (processingOrders != null && processingOrders.size() > 1) {
//                    logger.error("uid[{}] processing withdraw record over 1,pls check", requestParas.getUserId());
//                    rspObj.setCode(Const.ErrorCode.STAUS_MORE_THAN_1_PROCESSING_WITHDRAW_RECORD);
//                    return successResponse(rspObj);
//                }else if(processingOrders != null && processingOrders.size() == 1) {//用原商户单号和原提现金额进行提现
//                    AppWithDrawsWeixinPayDaoModel oriProcessingOrder = processingOrders.get(0);
//                    String orderId = oriProcessingOrder.getPartnerTradeNo();
//                    String openId = oriProcessingOrder.getWeixinAccount();
//                    String amountCent = oriProcessingOrder.getCost();
//                    String phiPhone = oriProcessingOrder.getPhoneNum();
//                    String phiNickname = oriProcessingOrder.getNickname();
//
//                    BigDecimal bdAmountCent = new BigDecimal(amountCent);
//                    BigDecimal bd100 = new BigDecimal(100);
//                    // 微信要求是以分为单位传送的，所以乘以100
//                    BigDecimal bdAmount = bdAmountCent.divide(bd100, 2, BigDecimal.ROUND_HALF_UP);
//                    String amountS = bdAmount.toString();
//                    return transfer(Const.DistributedLockParams.WITHDRAW_TYPE_ORI, 
//                    		requestParas.getUserId(), 
//                    		orderId, 
//                    		openId, 
//                    		amountS, 
//                    		phiPhone, 
//                    		phiNickname);
//                }
//
//                //产生新单号来提现
//                // 提现到微信(企业付款给个人用户)
//                String orderId = orderIdGeneratorServiceImpl.getOrderId();
//                if (StringUtils.isEmpty(orderId)) {
//                    logger.error("generate orderID [{}]  error", orderId);
//                    return errorResponse(Const.ErrorCode.STAUS_ORDER_FAILED);
//                }
//                //查询openid
//                UserModel userModel = userService.queryUserByUserid(requestParas.getUserId());
//                if (userModel == null || StringUtil.isNullOrEmpty(userModel.getWxsOpenId())) {
//                    logger.error("uid[{}] not bind wx openid", requestParas.getUserId());
//                    return errorResponse(Const.ErrorCode.STAUS_UID_NOT_BIND_OPEN_ID);
//                }
//                String openId = userModel.getWxsOpenId();
//
//                String amountS = requestParas.getAmount();
//                String phiPhone = userInfoModel.getiPhone();
//                String phiNickname = userModel.getPhiNickName();
//                return transfer(Const.DistributedLockParams.WITHDRAW_TYPE_NEW, 
//                		requestParas.getUserId(), 
//                		orderId, 
//                		openId, 
//                		amountS, 
//                		phiPhone, 
//                		phiNickname);
//            } else {//否则，同个uid并发请求直接返回正在处理提现
//                rspObj.setCode(Const.ErrorCode.STAUS_PROCESSING_WITHDRAW);
//                return successResponse(rspObj);
//            }
//        } finally {
//            mWithdrawingUids.remove(requestParas.getUserId());
//        }
    }

    //微信支付核心方法
    private PhiHomeBaseResponse transfer(int opType, String uid, String orderId, String openId, String amountS, String phiPhone, String phiNickname) {
//        logger.info("transfer,opType[{}],uid[{}],orderId[{}],openId[{}],amountS[{}],phiPhone[{}],phiNickname[{}]", opType, uid, orderId, openId, amountS, phiPhone, phiNickname);
//
//        BaseResponseModel rspObj = new BaseResponseModel();
//
//        // 查询可以提现的总金额--- sw_user_income_balance
//        BigDecimal bdIncomeBalance = BigDecimal.ZERO;
//        //1.01或1.0形式
//        String incomeBalanceS = null;
//        try {
//            incomeBalanceS = incomeBalanceService.selectUidIncome(uid);
//            if (StringUtil.isNullOrEmpty(incomeBalanceS)) {
//                incomeBalanceS = "0";
//            }
//            bdIncomeBalance = new BigDecimal(incomeBalanceS);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            rspObj.setRetCode(Const.ErrorCode.ERROR_DATABASE_FATAL);
//            return successResponse(rspObj);
//        }
//
//        logger.debug("bdIncomeBalance: {}", bdIncomeBalance);
//
//        if (bdIncomeBalance.compareTo(BigDecimal.ONE) == -1) {
//            logger.error("Draw with no balance");
//            rspObj.setRetCode(Const.ErrorCode.STAUS_WITH_DRAW_NO_BALANCE);
//            return successResponse(rspObj);
//        }
//
//        rspObj.setRetCode(Const.STATUS_OK);
//        rspObj.setRetMsg("");
//
//        BigDecimal bd100 = new BigDecimal(100);
//        // 微信要求是以分为单位传送的，所以乘以100，转化为分比较
//        BigDecimal bdIncomeBalanceCent = bdIncomeBalance.multiply(bd100);
//        BigDecimal bdAmount = new BigDecimal(amountS);
//        //分为单位
//        BigDecimal bdAmountCent = bdAmount.multiply(bd100);
//        // 余额不够就返回
//        if (bdIncomeBalanceCent.compareTo(bdAmountCent) == -1) {
//            logger.error("Draw with no balance,bdIncomeBalanceCent[{}]bdAmountCent[{}]", bdIncomeBalanceCent, bdAmountCent);
//            rspObj.setRetCode(ResponseStatus.STAUS_WITH_DRAW_NO_BALANCE);
//            return successResponse(rspObj);
//        }
//
//        //如果是新单号操作类型，则先插入数据库记录
//        if (Const.DistributedLockParams.WITHDRAW_TYPE_NEW == opType) {
//            //先下单，插入提现到微信记录表
//            AppWithDrawsWeixinPayDaoModel daoModel = new AppWithDrawsWeixinPayDaoModel();
//            daoModel.setUid(uid);
//            daoModel.setCost(bdAmountCent.toString());
//            daoModel.setWeixinAccount(openId);
//            long currentTime = System.currentTimeMillis() / 1000;
//            daoModel.setCreateTime(currentTime);
//            daoModel.setUpdateTime(currentTime);
//            daoModel.setStatus(0);
//            String phone = phiPhone;
//            if (phone == null) {
//                phone = "";
//            }
//            daoModel.setPhoneNum(phone);
//            String nickName = phiNickname;
//            if (nickName == null) {
//                nickName = "";
//            }
//            daoModel.setNickname(nickName);
//            daoModel.setPartnerTradeNo(orderId);// 商户订单号
//
//            withDrawsAlipayService.addWxWithdrawOrder(daoModel);
//        }
//
//        //提现后余额，更新零钱余额用，
//        BigDecimal bdRestAfterWithDraw = bdIncomeBalance.subtract(bdAmount);
//
//        //ip
//        String hostIp = GetLocalHostIp.getHostIp();
//        TransferReqData transferReqData = new TransferReqData(orderId, openId, bdAmountCent.intValue(), hostIp);
//        String transferServiceResponseString = "";
//        //调用微信接口
//        try {
//            transferServiceResponseString = transferService.request(transferReqData);
//            //调用微信接口错误，可能是超时或者未知错误，更新数据库对应orderId记录的wx_err_code字段为"TIMEOUT"
//            if (StringUtil.isNullOrEmpty(transferServiceResponseString)) {
//                logger.error("transfer err,uid[{}],orderid[{}],errcode[{}]", uid, orderId, ResponseStatus.STAUS_WX_TRANSFER_TIMEOUT_STR);
//                withDrawsAlipayService.updateWxWithdrawOrderErrCode(uid, orderId, ResponseStatus.STAUS_WX_TRANSFER_TIMEOUT_STR);
//                rspObj.setRetMsg(ResponseStatus.STAUS_WX_TRANSFER_TIMEOUT_STR);
//                return successResponse(rspObj);
//            }
//            // 将从API返回的XML数据映射到Java对象
//            TransferResData transferResData = (TransferResData) Util
//                    .getObjectFromXML(transferServiceResponseString, TransferResData.class);
//
//            //如果转换错误，可能返回数据格式错误，也更新数据库对应orderId记录的wx_err_code字段为"FORMAT_ERR"
//            if (transferResData == null || transferResData.getReturn_code() == null) {
//                logger.error("transfer err,uid[{}],orderid[{}],errcode[{}]", uid, orderId, ResponseStatus.STAUS_WX_TRANSFER_FORMAT_ERR_STR);
//                withDrawsAlipayService.updateWxWithdrawOrderErrCode(uid, orderId, ResponseStatus.STAUS_WX_TRANSFER_FORMAT_ERR_STR);
//                rspObj.setRetCode(ResponseStatus.STAUS_ORDER_FAILED);
//                rspObj.setRetMsg(ResponseStatus.STAUS_WX_TRANSFER_FORMAT_ERR_STR);
//                return successResponse(rspObj);
//            }
//            //如果是业务错误，也更新数据库对应orderId记录的wx_err_code字段
//            if (transferResData.getReturn_code().equals("FAIL") || transferResData.getResult_code().equals("FAIL")) {
//                //获取具体业务错误
//                String wxErrCode = transferResData.getErr_code();
//                logger.error("transfer err,uid[{}],orderid[{}],errcode[{}]", uid, orderId, wxErrCode);
//                withDrawsAlipayService.updateWxWithdrawOrderErrCode(uid, orderId, wxErrCode);
//                rspObj.setRetCode(ResponseStatus.STAUS_ORDER_FAILED);
//                rspObj.setRetMsg(wxErrCode);
//                return successResponse(rspObj);
//            }
//
//            // 提现成功再更新数据库
//            try {
//                updateDatabase(uid, incomeBalanceS, amountS, bdRestAfterWithDraw, openId, transferResData.getPayment_time(), transferResData.getPayment_no(), transferResData.getPartner_trade_no());
//            } catch (Exception e) {
//                logger.error("after transfer,update db err,uid[{}],orderid[{}],errcode[{}]", uid, orderId, ResponseStatus.STAUS_WX_TRANSFER_DB_ERR_STR);
//                logger.error(e.getMessage());
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//
//                rspObj.setRetCode(ResponseStatus.STAUS_DATABASE_OPERATE_ERROR);
//                rspObj.setRetMsg(ResponseStatus.STAUS_WX_TRANSFER_DB_ERR_STR);
//                return successResponse(rspObj);
//            }
//
//        } catch (Exception e) {
//            logger.error("transfer err,uid[{}],orderid[{}],errcode[{}]", uid, orderId, ResponseStatus.STAUS_WX_TRANSFER_TIMEOUT_STR);
//            logger.error(e.getMessage());
//            rspObj.setRetCode(ResponseStatus.STAUS_ERROR);
//            rspObj.setRetMsg(ResponseStatus.STAUS_WX_TRANSFER_TIMEOUT_STR);
//            return successResponse(rspObj);
//        }
//        logger.info("uid[{}],amountCentStr[{}] withdraw weixin done", uid, amountS);
//
//        return successResponse(rspObj);
    	return null;
    }

    @Transactional
    public int updateDatabase(String uid, String oldBalance, String amount, BigDecimal bdRestAfterWithDraw, String weixinAccount, String withdrawTimeFormat, String wxOrderId, String orderId)
            throws Exception {
//        // 插入零钱明细表
//        AppIncomeDetailsDaoModel incomeDetail = new AppIncomeDetailsDaoModel();
//        incomeDetail.setActionName("零钱提现");
//        incomeDetail.setCost("-" + amount);
//        long curTime = System.currentTimeMillis() / 1000;
//        incomeDetail.setCreateTime(curTime);
//        incomeDetail.setRouterMac("");
//        incomeDetail.setStatus((byte) 0);
//        incomeDetail.setUid(uid);
//        incomeDetail.setUpdateTime(curTime);
//        incomeDetailService.insertOne(incomeDetail);
//
//        // 总余额-提现=余额 sw_user_income_balance
//        int result = incomeBalanceService.updateUserTotalBalance(uid, StringUtil.formatNumberWithTwoPoint(bdRestAfterWithDraw),
//                oldBalance);
//        if (result <= 0) {
//            // 更新数据库没有影响一行记录，Rollback
//            logger.error("Update balance table unsuccess, rollback");
//            throw new Exception("No updated rows");
//        }
//
//        // 更新提现到微信记录表，主要回填payno
//        AppWithDrawsWeixinPayDaoModel daoModel = new AppWithDrawsWeixinPayDaoModel();
//        daoModel.setUid(uid);
//        daoModel.setWeixinAccount(weixinAccount);
//        daoModel.setPartnerTradeNo(orderId);
//
//        daoModel.setPaymentNo(wxOrderId);
//        long currentTime = System.currentTimeMillis() / 1000;
//        daoModel.setUpdateTime(currentTime);
//        daoModel.setWithdrawTime(withdrawTimeFormat);
//
//        withDrawsAlipayService.updateWxWithdrawOrder(daoModel);

        return Const.STATUS_OK;
    }

}