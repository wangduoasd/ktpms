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
    RECIEVE_THE_TASK_STATUS_TIME_OUT("5" , "过期了，不要领了"),
    // 未完成任务-提交任务
    SUBMIT_TASK_ERROR("1" , "任务提交失败"),
    // 已完成页面消息
    COMPLETED_TASK("1" , "任务完成，奖励到账"),

    // 图片上传失败
    PUBLISHING_TASK_IMAGE_UPLOAD_FAILED("0" , "图片上传失败"),
    // 没有获得图片
    PUBLISHING_TASK_IMAGE_IS_EMPTY("1" , "图片为空"),
    // 图片上传失败
    PUBLISHING_TASK_IMAGE_HAS_BEEN_SUCCESSFULLY_UPLOADED("0" , "图片上传失败"),
    // 已发布页面消息
    PUBLISHED_TASK_COMPLETED("1" , "已完成"),
    // 任务发布成功
    THE_TASK_WAS_SUCCESSFULLY_POSTDE("1" , "任务已发布"),
    // 任务发布失败
    TASK_POSTING_FAILED("0" , "任务发布失败"),
    // 任务提前取消
    TASK_CANCELED_IN_ADVANCE("1" , "任务提前取消"),
    // 任务审核通过
    AUDIT_PASSED_SUCCESS("1","审核通过"),
    // 任务审核通过失败
    AUDIT_PASSED_ERROR("0","审核通过失败"),
    // 任务驳回成功
    AUDIT_REJECTION_SUCCESS("1","已驳回"),
    // 任务驳回失败
    AUDIT_REJECTION_ERROR("1","驳回失败"),

    //购买失败，积分不足
    FIND_PRIZE_INTEGRAL_LACKOF("1","积分不足"),
    //购买失败，超过上限
    FIND_PRIZE_CAP("2","超过上限"),
    //购买成功
    FIND_PRIZE_SUCCESS("3","兑换成功"),
    //用户名或商品为空
    FIND_USER_PRIZE_NULL("4","用户名或密码为空"),
    //删除成功
    DELELETE_SUCCESS("1","删除成功"),
    //删除失败
    DELETE_ERROR("2","删除失败"),

    // 添加商品失败
    ADJUNCTION_ERROR("0" , "添加商品失败"),
    //添加商品成功
    ADJUNCTION_SUCCESS("1" , "添加商品成功"),
    //修改商品成功
    MODIFICATION_SUCCESS("1" , "修改商品成功"),
    //修改商品失败
    MODIFICATION_ERROR("1" , "修改商品失败"),
    //商品校检该商品已经存在
    PRIZENAME_EXIST("1","商品名存在"),
    //商品校检可以添加
    PRIZENAME_CANADD("2","商品可以添加"),
    //上架成功
    GOODS_SHELVES("1","商品上架成功"),
    // 上架失败
    GOODS_ERROR("0","商品上架失败"),

    GOODS_SOLDOUT_SUCCESS("1","商品下架成功"),

    GOODS_SOLDOUT_ERROR("0","商品下架失败");






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
