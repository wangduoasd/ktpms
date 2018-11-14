package com.kaituo.pms.utils;



/**
 * @author zhoulian
 * @date 2018/8/22
 * Created by idea 2018.1
 */
public class ResultUtil {
    public static OutPut success(Object object) {
        OutPut response = new OutPut();
        response.setCode(CodeAndMessageEnum.ALL_SUCCESS.getCode());
        response.setMessage (CodeAndMessageEnum.ALL_SUCCESS.getMessage ());
        response.setData(object);
        return response;
    }
    public static OutPut success(String code,String msg,Object object) {
        OutPut response = new OutPut();
        response.setCode(code);
        response.setMessage (msg);
        response.setData(object);
        return response;
    }
    public static OutPut success() {
        return success(null);
    }

    public static OutPut error(String code, String msg) {
        OutPut response = new OutPut();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }
}
