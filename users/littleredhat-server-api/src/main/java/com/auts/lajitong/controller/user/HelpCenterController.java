package com.auts.lajitong.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.model.common.PhiHomeBaseResponse;
import com.auts.lajitong.model.dao.HelpCenter;
import com.auts.lajitong.service.HelpCenterService;

/**
 * 帮助中心API
 *
 * @author li.bing
 * @date 2018年12月6日 下午4:55:18
 */
@RestController
@CrossOrigin
public class HelpCenterController extends SBaseController {
//    private static final Logger LOGGER = LogManager.getLogger(HelpCenterController.class);

    public static final String DEFAULT_CHARSET = "utf-8";
    public static String HTTP_HEAD_AUTHORIZATION = "Authorization";
    public static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";

    @Autowired
    private HelpCenterService helpCenterService;

    @RequestMapping(value = "/users/v1/queryAllHelpCenter")
    public PhiHomeBaseResponse queryAllInfoCount(HttpServletRequest request, HttpServletResponse response) {
        PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();
        List<HelpCenter> result = helpCenterService.queryAllHelpCenter();
        rspObj.setResult(result);
        return successResponse(rspObj);
    }
}
