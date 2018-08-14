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

    //积分分页-没有得到员工信息
    FIND_RANKING_BY_PAGE_NULL("2" , "没有得到员工信息"),

    // 积分分页检索-没有得到员工信息
    FIND_RANKING_BY_PAGE_AND_CONDITION_NULL("2" , "没有得到员工信息"),

    // 任务分页-没有找到任务信息
    GET_STATES_TASK_BY_PAGE_NULL("2" , "未找到任务"),
    // 待领取任务领取任务按钮
    RECIEVE_THE_TASK_USER_OR_TASK_NULL("2" , "没有找到对应员工或任务信息"),
    // 待领取任务领取任务按钮—任务已被领取
    RECIEVE_THE_TASK_STATUS_NOT_ONE("3" , "手慢了，本宝宝被领走了QWQ"),
    // 待领取任务领取任务按钮—成功
    RECIEVE_THE_TASK_STATUS_SUCCESS("4" , "领取成功"),
    // 待领取任务领取任务按钮—成功
    RECIEVE_THE_TASK_STATUS_TIME_OUT("5" , "过期了，不要领了");

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
