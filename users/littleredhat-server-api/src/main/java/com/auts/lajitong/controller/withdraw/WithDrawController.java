package com.auts.lajitong.controller.withdraw;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.model.common.PhiHomeBaseResponse;
import com.auts.lajitong.model.dao.WithdrawModel;
import com.auts.lajitong.service.WithdrawService;

/**
 * 提现的接口,比如微信提现.
 * @author huangrongwei
 *
 */
@RestController
@CrossOrigin
public class WithDrawController extends SBaseController {

    @Autowired
    WithdrawService withdrawService;

    /**
     * 提现记录查询.
     * @param request
     * @return
     */
    @RequestMapping(value = "v1/with_draw_history", method = RequestMethod.GET, produces = { "application/json" })
    public PhiHomeBaseResponse getBanner(HttpServletRequest request) {
        PhiHomeBaseResponse rsp = new PhiHomeBaseResponse();
        List<WithdrawModel> models = withdrawService.getByUserId("");

        return successResponse(rsp);
    }
}
