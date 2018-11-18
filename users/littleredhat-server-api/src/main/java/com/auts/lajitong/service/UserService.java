package com.auts.lajitong.service;

import com.auts.lajitong.model.dao.UserModel;

public interface UserService {
    int addUser(UserModel model);
    int updateUserNickName(String id, String nickName);
    int updateUserSex(String id, int sex);
    int updateUser(String id, String nickName, int sex);
}
