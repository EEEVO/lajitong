package com.auts.lajitong.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auts.lajitong.consts.Const;
import com.auts.lajitong.model.response.BannerResponseModel;
import com.auts.lajitong.model.response.BannerResponseModel.Banner;
import com.auts.lajitong.model.response.BannerResponseModel.Result;
import com.auts.lajitong.util.MyResponseutils;

/**
 * 首页Banner图的控制.
 * @author huangrongwei
 *
 */
@RestController
@CrossOrigin
public class BannerController extends SBaseController {

    @RequestMapping(value = "/v1/get_banners", method = RequestMethod.GET, produces = { "application/json" })
    public BannerResponseModel getBanner(HttpServletRequest request) {
        BannerResponseModel rsp = new BannerResponseModel();
        rsp.setStatus(Const.STATUS_OK);
        rsp.setMessage(MyResponseutils.parseMsg(Const.STATUS_OK));

        List<Banner> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Banner banner = new Banner();
            banner.setName("pic-" + i);
            banner.setUrl("https://www.xiaoredcap.com/img/banner/banner_" + i + ".png");
            banner.setLink_url("");
            list.add(banner);
        }
        Result result = new Result();
        result.setList(list);
        rsp.setResult(result);

        return rsp;
    }
}
