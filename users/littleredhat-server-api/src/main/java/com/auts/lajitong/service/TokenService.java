package com.auts.lajitong.service;

import com.auts.lajitong.model.dao.TokenModel;

public interface TokenService {

    TokenModel getByUid(String uid);

    TokenModel getByToken(String token);

    int insertToken(TokenModel model);

    int updateToken(TokenModel model);
}
