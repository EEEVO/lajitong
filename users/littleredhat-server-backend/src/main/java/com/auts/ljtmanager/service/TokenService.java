package com.auts.ljtmanager.service;

import com.auts.ljtmanager.model.dao.TokenModel;

public interface TokenService {

    TokenModel getByUid(String uid);

    TokenModel getByToken(String token);

    int insertToken(TokenModel model);

    int updateToken(TokenModel model);
}
