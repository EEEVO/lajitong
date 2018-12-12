package com.auts.lajitong.service;

import com.auts.lajitong.model.dao.AccountModel;

public interface UserService {

	AccountModel queryLoginUser(String username, String password);

}