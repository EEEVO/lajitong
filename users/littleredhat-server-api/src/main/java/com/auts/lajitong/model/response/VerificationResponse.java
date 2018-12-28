package com.auts.lajitong.model.response;

import com.dls.sdk.vo.Verification;

public class VerificationResponse {
	public int parseStatus;
	public Verification mbDataObject;
	private String reqContent;
	private int errorStatus;
	public int getParseStatus() {
		return parseStatus;
	}
	public void setParseStatus(int parseStatus) {
		this.parseStatus = parseStatus;
	}
	public Verification getMbDataObject() {
		return mbDataObject;
	}
	public void setMbDataObject(Verification mbDataObject) {
		this.mbDataObject = mbDataObject;
	}
	public String getReqContent() {
		return reqContent;
	}
	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}
	public int getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(int errorStatus) {
		this.errorStatus = errorStatus;
	}
}
