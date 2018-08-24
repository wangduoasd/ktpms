package com.kaituo.pms.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张金行
 * @version 1.0
 * @Title: OutJSON
 * @ProjectName pms
 * @Description:
 * @date 2018/8/9 00099:55
 */
public class OutJSON {

    private  String code;

    private String message;

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }
    private static class OutJSONHolder{
        private  static final  OutJSON instance=new OutJSON();
    }
    private OutJSON(){ };
    public  static OutJSON getInstance(CodeAndMessageEnum codeAndMessageEnum, Object data){
        OutJSON outJSON = OutJSONHolder.instance;
      outJSON.code = codeAndMessageEnum.getCode();
      outJSON.message = codeAndMessageEnum.getMessage();
      outJSON.data = data;
      return  outJSON;
    }

    public  static OutJSON getInstance(CodeAndMessageEnum codeAndMessageEnum){
        OutJSON outJSON = OutJSONHolder.instance;
        outJSON.data=null;
        outJSON.code = codeAndMessageEnum.getCode();
        outJSON.message = codeAndMessageEnum.getMessage();
        return  outJSON;
    }
    public  String getCode() {
        return code;
    }

    public  void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }
}
