package com.kaituo.pms.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Token {
    private Integer userId;

    private String token;

    private Date failureTime;

    public Token(Integer userId, String token, Date failureTime) {
        this.userId = userId;
        this.token = token;
        this.failureTime = failureTime;
    }

    public Token() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }
    public static Token getNewToken(Integer userId,String token){
        Token token1 = new Token();
        token1.setToken(token);
        token1.setUserId(userId);
        token1.setFailureTime(new Date());
        return token1;

    }
}