package com.auts.lajitong.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auts.lajitong.dao.UserMapper;
import com.auts.lajitong.model.dao.UserModel;
import com.auts.lajitong.service.UserService;

@Service
public class UserImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserImpl.class);

    @Autowired
    UserMapper mapper;

    @Override
    public int updateUserNickName(String id, String nickName) {
        return mapper.updateUserNickName(id, nickName);
    }

    @Override
    public int updateUserSex(String id, int sex) {
        return mapper.updateUserSex(id, sex);
    }

    @Override
    public int updateUser(String id, String nickName, int sex) {
        return mapper.updateUser(id, nickName, sex);
    }

    @Override
    public int addUser(UserModel model) {
        return mapper.addUser(model);
    }
}
