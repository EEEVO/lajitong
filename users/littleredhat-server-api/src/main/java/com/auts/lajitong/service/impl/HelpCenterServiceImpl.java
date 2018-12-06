package com.auts.lajitong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.HelpCenterMapper;
import com.auts.lajitong.model.dao.HelpCenter;
import com.auts.lajitong.service.HelpCenterService;

@Service
public class HelpCenterServiceImpl implements HelpCenterService {

	@Autowired
	HelpCenterMapper helpCenterMapper;
	
	@Override
	public List<HelpCenter> queryAllHelpCenter() {
		return helpCenterMapper.queryAllHelpCenter();
	}

}
