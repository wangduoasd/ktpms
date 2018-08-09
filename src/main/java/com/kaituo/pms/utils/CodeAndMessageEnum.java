package com.kaituo.pms.utils;
/** 
* @Description: 枚举返回值定义类
* @Author: su
* @Date: 2018/8/8
*/ 
public enum CodeAndMessageEnum {
    //message无需显示且没有其他可能发生的情况且未定义所返回message时可以使用
    ALL_SUCCESS("1","成功"),
    //message无需显示且没有其他可能发生的情况且未定义所返回message时可以使用
    ALL_ERROR("0","失败"),

    //发生未知异常导致失败
    USER_FIND_RANKING_BY_PAGE_ERROR("0" , "失败"),
    //成功获取积分排行榜员工列表
    USER_FIND_RANKING_BY_PAGE_SUCCESS("1" , "成功"),
    //没有得到员工信息
    USER_FIND_RANKING_BY_PAGE_NULL("2" , "没有得到员工信息");

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CodeAndMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
