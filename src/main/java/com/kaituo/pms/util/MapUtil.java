package com.kaituo.pms.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

    public static Map<String,Object> setMap(CodeAndMessageEnum codeAndMessageEnum, Object data){
        Map<String , Object> map = new HashMap<>(3);
        map.put("code", codeAndMessageEnum.getCode());
        map.put("message", codeAndMessageEnum.getMessage());
        map.put("data",data);
        return map;

    }
    public static HashMap<String,Object> setMap2(String code, String message, Object data){
        HashMap<String , Object> map = new HashMap<>(3);
        map.put("code",code);
        map.put("message",message);
        map.put("data",data);
        return map;

    }
}
