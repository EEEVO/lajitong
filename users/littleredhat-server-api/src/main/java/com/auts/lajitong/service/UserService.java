package com.auts.lajitong.service;


public interface UserService {
    int updateUser(String id, String nickName);
    int updateUser(String id, int sex);
    int updateUser(String id, String nickName, int sex);
}
