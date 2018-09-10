package com.kaituo.pms.utils;

import java.util.HashMap;

/**
 * @author 张金行
 * @version 1.0
 * @Title: TokenMap
 * @ProjectName pms
 * @Description:
 * @date 2018/9/10 001011:20
 */
public class TokenMap {
    private HashMap<String,Integer> tokenMap=new HashMap<>();
    private static class TokenMapHolder{
        private  static final  TokenMap instance=new TokenMap();
    }

    public HashMap<String, Integer> getTokenMap() {
        return tokenMap;
    }
     public static TokenMap getInstance(){
        return  TokenMapHolder.instance;
     }

    public void putTokenMap(String token) {
        this.tokenMap.put(token,JwtToken.getUserId(token));
    }
}
