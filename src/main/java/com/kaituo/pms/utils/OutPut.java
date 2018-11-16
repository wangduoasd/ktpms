package com.kaituo.pms.utils;



/**
 * @BelongsProject: gs-server
 * @BelongsPackage: com.gs.pojo
 * @Author: kaituo
 * @CreateTime: 2018-07-07 18:08
 * @Description:
 */
public class OutPut {
    private String code;

    private String message ;

    private int count;




    public OutPut(){
        this.code=CommonEnum.ERROR.getCode();
        this.message=CommonEnum.ERROR.getName();
    }

//    private OutPut(String code,String message){
//        this.code=code;
//        this.message=message;
//    }

    private Object data =null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public void setData(Object data) {
        this.data = data;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public OutPut setNotLogin(OutPut outPut) {
        outPut.setCode(CommonEnum.NOLOGGING.getCode());
        outPut.setMessage(CommonEnum.NOLOGGING.getName());
        return outPut;
    }
    public  static OutPut getInstance(CodeAndMessageEnum codeAndMessageEnum){
        OutPut outPut = new OutPut();
        outPut.data=null;
        outPut.code = codeAndMessageEnum.getCode();
        outPut.message = codeAndMessageEnum.getMessage();
        return  outPut;
    }
}
