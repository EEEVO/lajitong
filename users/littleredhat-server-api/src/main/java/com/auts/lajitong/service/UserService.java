package com.auts.lajitong.service;


public interface UserService {
    int updateUserNickName(String id, String nickName);
    int updateUserSex(String id, int sex);
    int updateUser(String id, String nickName, int sex);
}
