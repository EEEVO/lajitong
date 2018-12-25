package com.auts.lajitong.model.response;

import com.dls.sdk.vo.Deliver;

public class RecordResponse {
	public int parseStatus;
	public Deliver mbDataObject;
	private String reqContent;
	private int errorStatus;
	public int getParseStatus() {
		return parseStatus;
	}
	public void setParseStatus(int parseStatus) {
		this.parseStatus = parseStatus;
	}
	public Deliver getMbDataObject() {
		return mbDataObject;
	}
	public void setMbDataObject(Deliver mbDataObject) {
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
