package com.auts.lajitong.model.request;

import java.io.Serializable;

/**
 * 提现到微信帐号 用户请求参数
 *
 * @author rongwei.huang
 */
public class UserAppWithDrawsRequestModel implements Serializable {
    /*
     * 请求提现的参数
     */
    private String amount; //金额（精确到小数点2位 如：99.22）
    private String userId; //用户token

    public UserAppWithDrawsRequestModel() {
        super();
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
