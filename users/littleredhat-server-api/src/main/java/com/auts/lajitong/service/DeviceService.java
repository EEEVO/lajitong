package com.auts.lajitong.service;

import com.dls.sdk.vo.MbParseResult;
import com.dls.sdk.vo.Shake;

public interface DeviceService {
	
	int saveShake(MbParseResult<Shake> mbParseResult) throws Exception;
}
