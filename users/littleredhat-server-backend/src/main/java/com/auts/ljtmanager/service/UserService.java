package com.auts.ljtmanager.service;

import com.auts.ljtmanager.model.dao.AccountModel;

public interface UserService {

	AccountModel queryLoginUser(String username, String password);

}