package com.auts.lajitong.controller.withdraw;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.auts.lajitong.model.dao.UserModel;
import com.auts.lajitong.model.dao.WithdrawModel;
import com.auts.lajitong.model.response.litteredhat.LitteredBalanceListResponseModel;
import com.auts.lajitong.model.response.litteredhat.LitteredBalanceListResponseModel.BalanceData;
import com.auts.lajitong.service.UserService;
import com.auts.lajitong.service.WithdrawService;
import com.auts.lajitong.util.MyListUtils;
import com.auts.lajitong.util.StringUtil;

/**
 * 提现的接口,比如微信提现.
 * @author huangrongwei
 *
 */
@RestController
@CrossOrigin
public class WithDrawController extends SBaseController {

    private static final Logger LOGGER = LogManager.getLogger(WithDrawController.class);

    @Autowired
    WithdrawService withdrawService;
    
    @Autowired
    UserService userService;

    /**
     * 余额明细查询，微信支付宝提现，订单收入.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/v1/balance/lists", method = RequestMethod.GET, produces = { "application/json" })
    public PhiHomeBaseResponse getBalanceList(HttpServletRequest request,
            @RequestParam(value = "userId", required = true) String userId) {

        LOGGER.info("getBalanceList userId: [{}]", userId);

        List<BalanceData> lists = new ArrayList<>();
        List<WithdrawModel> models = withdrawService.getByUserId(userId);
        if (!MyListUtils.isEmpty(models)) {
            for (WithdrawModel withdrawModel : models) {
                BalanceData rspBalanceModel = new BalanceData();
                rspBalanceModel.setAmount(getAmnountByWithdrawType(withdrawModel.getWithdrawType(), withdrawModel.getAmount()));
                rspBalanceModel.setType(withdrawModel.getWithdrawType());
                rspBalanceModel.setMsg(getWithdrawTypeMSG(withdrawModel.getWithdrawType()));
                rspBalanceModel.setOperate_time(transTime(withdrawModel.getCreateTime()));
                lists.add(rspBalanceModel);
            }
        }
        LitteredBalanceListResponseModel bannerRspModels = new LitteredBalanceListResponseModel();
        bannerRspModels.setData(lists);

        PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();
        rspObj.setResult(bannerRspModels);
        return successResponse(rspObj);
    }
    
    
    /**
     * 银行卡提现接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/v1/with_draw_bankcard", method = RequestMethod.POST, produces = { "application/json" })
    public PhiHomeBaseResponse withdrawBankcard(HttpServletRequest request,
    		@RequestParam(value="bankname", required = true) String bankname,
    		@RequestParam(value="bankno", required = true) String bankno,
    		@RequestParam(value="username", required = true) String username,
    		@RequestParam(value="amount", required = true) String amount) {
        String userId = request.getHeader(Const.AUTHORIZATION);
        LOGGER.info("bank withdraw userId [{}] bankname[{}] bankno[{}], username[{}] amount[{}]", userId, bankname, bankno,
        		username, amount);
        if (StringUtil.isNullOrEmpty(bankname)) {
			return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_NO_BANKNAME);
		}
        if (StringUtil.isNullOrEmpty(bankno)) {
			return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_NO_BANKNO);
		}
        if (StringUtil.isNullOrEmpty(username)) {
			return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_NO_USERNAME);
		}
        if (StringUtil.isNullOrEmpty(amount)) {
			return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_NO_AMOUNT);
		}
        if (!StringUtil.isDigitsOnly(amount)) {
        	return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_WRONG_AMOUNT);
		}
        //1. 检查是否有绑定银行卡
//        return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_NO_BANK);
        
        //2. 查询提现金额是否超过可提现的金额
        UserModel userModel = userService.queryUserByUserid(userId);
        if (userModel == null || StringUtil.isNullOrEmpty(userModel.getCurrentProfit())) {
			return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_NO_BALANCE);
		}
        BigDecimal savedBalance = new BigDecimal(userModel.getCurrentProfit());
        BigDecimal passedBalance = new BigDecimal(amount);
        if (savedBalance.compareTo(passedBalance) == -1) {
        	return errorResponse(Const.ErrorCode.STATUS_WITHDRAW_FAILED_NO_BALANCE);
		}
        
        //3. 记录提现请求
        WithdrawModel withdrawModel = new WithdrawModel();
        withdrawModel.setAmount(amount);
        withdrawModel.setCreateTime(System.currentTimeMillis());
        withdrawModel.setOrderNo("");
        withdrawModel.setReason(bankname + "---" + bankno + "---" + username);
        withdrawModel.setStatus(0);
        withdrawModel.setUserId(userId);
        withdrawModel.setWithdrawType(3);
        
        withdrawService.saveWithdraw(withdrawModel);
        
        
        PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();
        rspObj.setResult(null);
        return successResponse(rspObj);
    }

    private String getWithdrawTypeMSG(int withdrawType) {
    	String msg = "";
    	if(withdrawType == 0) {
    		msg = "微信体现";
    	} else if(withdrawType == 1) {
    		msg = "支付宝体现";
    	} else if(withdrawType == 2) {
    		msg = "投递收益";
    	}
    	return msg;
    }

    private String getAmnountByWithdrawType(int withdrawType, String amount) {
    	String amountMsg = amount;
    	if(withdrawType == 0) {
    		amountMsg = "-" + amount;
    	} else if(withdrawType == 1) {
    		amountMsg = "-" + amount;
    	} else if(withdrawType == 2) {
    		amountMsg = "+" + amount;
    	}
    	return amountMsg;
    }

    private String transTime(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }
}
