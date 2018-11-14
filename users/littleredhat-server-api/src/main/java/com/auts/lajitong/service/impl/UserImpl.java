package com.auts.lajitong.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.AccountMapper;
import com.auts.lajitong.service.UserService;

@Service
public class UserImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserImpl.class);

    @Autowired
    AccountMapper mapper;

    @Override
    public int updateUser(String id, String nickName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateUser(String id, int sex) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateUser(String id, String nickName, int sex) {
        // TODO Auto-generated method stub
        return 0;
    }

}
