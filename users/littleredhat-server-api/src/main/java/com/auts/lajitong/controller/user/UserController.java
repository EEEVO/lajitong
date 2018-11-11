package com.auts.lajitong.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auts.lajitong.controller.SBaseController;
import com.auts.lajitong.model.common.PhiHomeBaseResponse;

/**
 * 用户个人信息接口.
 *
 * rongwei.huang
 *
 */
@RestController
@CrossOrigin
public class UserController extends SBaseController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    public static final String DEFAULT_CHARSET = "utf-8";
    public static String HTTP_HEAD_AUTHORIZATION = "Authorization";
    public static String HTTP_HEAD_CONTENT_TYPE = "Content-Type";

    /**
     * 修改个人信息.
     * @param request
     * @return
     */
    @RequestMapping(value = "/users/v1/modify", method = RequestMethod.POST, produces = { "application/json" })
    public PhiHomeBaseResponse queryAllInfoCount(HttpServletRequest request,
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "nickName", required = true) String nickName,
            @RequestParam(value = "sex", required = true) int sex) {
        PhiHomeBaseResponse rspObj = new PhiHomeBaseResponse();

        return successResponse(rspObj);
    }
}
