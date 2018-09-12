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
    private static HashMap<String,Integer> tokenMap;
    private static class TokenMapHolder{
        private static final HashMap<String,Integer> tokenMap=new HashMap<>();
        private  static final  TokenMap instance=new TokenMap();
    }

    public static TokenMap getInstance(){
        return  TokenMapHolder.instance;
     }

    public static int check(String token){
        System.out.println(token);
        return tokenMap.get(token);
    }
    public static String remove(String token,int userId){
        tokenMap.remove(token);
        String newToken = null;
        try {
            newToken = JwtToken.createToken(userId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tokenMap.put(newToken,userId);
        return newToken;
    }
    public static void delete(String token){
        tokenMap.remove(token);

    }
    public static String create(Integer userId){
        String token=null;
        try {
             token=JwtToken.createToken(userId);
            setTokenMap();
            tokenMap.put(token,userId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  token;
    }

    public static void setTokenMap() {
        TokenMap.tokenMap = TokenMapHolder.tokenMap;
    }

    public static HashMap<String, Integer> getTokenMap() {
        return tokenMap;
    }
}
