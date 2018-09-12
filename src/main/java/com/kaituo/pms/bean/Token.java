package com.kaituo.pms.bean;

import com.kaituo.pms.utils.Util;

import java.sql.Timestamp;

/**
 * @author 张金行
 * @version 1.0
 * @Title: Token
 * @ProjectName pms
 * @Description:
 * @date 2018/9/12 001220:34
 */
public class Token {
    private int userId;
    private String token;
    private Timestamp failureTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Timestamp failureTime) {
        this.failureTime = failureTime;
    }
    public static Token getNewToken(int userId,String token){
        Token token1 = new Token();
        token1.setToken(token);
        token1.setUserId(userId);
        token1.setFailureTime(Util.getTimestamp());
        return token1;
    }
    public static Token getNewToken(String token){
        Token token1 = new Token();
        token1.setToken(token);
        token1.setFailureTime(Util.getTimestamp());
        return token1;
    }

    public Token(int userId, String token, Timestamp failureTime) {
        this.userId = userId;
        this.token = token;
        this.failureTime = failureTime;
    }

    public Token() {
    }
}
