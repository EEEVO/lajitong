package com.auts.lajitong.service;


import com.auts.lajitong.model.dao.CaptchaModel;

public interface CaptchaService {


	int addCaptcha(CaptchaModel captcha);

	int delCaptcha(String phoneNo);

	int updateCaptcha(CaptchaModel captcha);

	CaptchaModel queryCaptchaByPhoneNo(String phoneNo);
}