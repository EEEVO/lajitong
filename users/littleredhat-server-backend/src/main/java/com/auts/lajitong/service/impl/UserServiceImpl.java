package com.auts.lajitong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.AccountMapper;
import com.auts.lajitong.model.dao.AccountModel;
import com.auts.lajitong.service.UserService;
import com.auts.lajitong.util.EntryUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AccountMapper userMapper;
	
	@Override
	public AccountModel queryLoginUser(String username, String password) {
		return userMapper.login(username, EntryUtils.getMd5(password));
	}

}