package com.kaituo.pms.error;

/**
 * @author 张金行
 * @version 1.0
 * @Title: MyException
 * @ProjectName pms
 * @Description:
 * @date 2018/9/5 000511:34
 */
public class MyException extends RuntimeException {
    //无参构造方法
    public MyException(){

        super();
    }

    //有参的构造方法
    public MyException(String message){
        super(message);

    }

    // 用指定的详细信息和原因构造一个新的异常
    public MyException(String message, Throwable cause){

        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public MyException(Throwable cause) {

        super(cause);
    }

}
