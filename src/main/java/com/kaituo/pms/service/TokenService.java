package com.kaituo.pms.service;

import com.kaituo.pms.bean.Token;

import java.sql.Timestamp;

public interface TokenService {
    int addToken(Token token);
    int upToken(Token token);
    int delectToken(String token);
    Token selectUserIdByToken(String token);
    boolean haveToken(Integer userId);
    int delectToken(Integer userId);
}
