package com.auts.lajitong.controller.withdraw;

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
import com.auts.lajitong.controller.user.UserController;
import com.auts.lajitong.model.common.PhiHomeBaseResponse;
import com.auts.lajitong.model.dao.WithdrawModel;
import com.auts.lajitong.model.response.litteredhat.LitteredBalanceListResponseModel;
import com.auts.lajitong.model.response.litteredhat.LitteredBalanceListResponseModel.BalanceData;
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

    /**
     * 余额明细查询，微信支付宝提现，订单收入.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/v1/balance/lists", method = RequestMethod.GET, produces = { "application/json" })
    public PhiHomeBaseResponse getBalanceList(HttpServletRequest request,
            @RequestParam(value = "userId", required = true) String userId) {
//        String userId = request.getHeader(Const.AUTHORIZATION);
//        if (StringUtil.isNullOrEmpty(userId)) {
//            return errorResponse(Const.ErrorCode.REQUEST_NO_PARAS);
//        }
        LOGGER.info("getBalanceList userId: [{}]", userId);

        List<BalanceData> lists = new ArrayList<>();

        List<WithdrawModel> models = withdrawService.getByUserId(userId);
        if (!MyListUtils.isEmpty(models)) {
            for (WithdrawModel withdrawModel : models) {
                BalanceData rspBalanceModel = new BalanceData();
                rspBalanceModel.setAmount(withdrawModel.getAmount());
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
    private String transTime(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }
}
