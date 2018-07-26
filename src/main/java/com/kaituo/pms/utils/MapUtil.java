package com.kaituo.pms.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtil {

    public static Map<String,Object> setMap(String code, String message, List<Map<String,Object>> data){
        Map<String , Object> map = new HashMap<>();
        map.put("code",code);
        map.put("message",message);
        map.put("data",data);
        return map;

    }
    public static HashMap<String,Object> setMap2(String code, String message, Object data){
        HashMap<String , Object> map = new HashMap<>();
        map.put("code",code);
        map.put("message",message);
        map.put("data",data);
        return map;

    }
}
