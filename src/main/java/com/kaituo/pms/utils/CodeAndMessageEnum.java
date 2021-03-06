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
    ALL_ERROR("-1","失败"),

    //积分分页-没有得到员工信息
    FIND_RANKING_BY_PAGE_NULL("-2" , "没有得到员工信息"),

    // 积分分页检索-没有得到员工信息
    FIND_RANKING_BY_PAGE_AND_CONDITION_NULL("-3" , "没有得到员工信息"),

    // 任务分页-没有找到任务信息
    GET_STATES_TASK_BY_PAGE_NULL("1" , "未找到任务"),
    // 待领取任务领取任务按钮
    RECIEVE_THE_TASK_USER_OR_TASK_NULL("-5" , "没有找到对应员工或任务信息"),
    // 待领取任务领取任务按钮—任务已被领取
    RECIEVE_THE_TASK_STATUS_NOT_ONE("-6" , "手慢了，本宝宝被领走了QWQ"),
    // 待领取任务领取任务按钮—成功
    RECIEVE_THE_TASK_STATUS_SUCCESS("1" , "领取成功"),
    // 待领取任务领取任务按钮—过期
    RECIEVE_THE_TASK_STATUS_TIME_OUT("-7" , "过期了，不要领了"),
    // 待领取任务领取任务按钮—积分不够
    RECIEVE_THE_TASK_INSUFFICIENT_POINTS("-8" , "积分不足，努力工作吧"),
    // 待领取任务领取任务按钮—状态为已领取
    RECIEVE_THE_TASK_STATUS_IS_RECEIVED("-9" , "打什么主意呢？赶快完成任务吧！"),
    // 未完成任务-提交任务
    SUBMIT_TASK_ERROR("-10" , "任务提交失败"),

    // 已完成页面消息
    COMPLETED_TASK("1" , "任务完成，奖励到账"),
    // 已发布页面消息
    PUBLISHED_TASK_COMPLETED("1" , "已完成"),

    // 图片上传失败
    PUBLISHING_TASK_IMAGE_UPLOAD_FAILED("-11" , "图片上传失败"),
    // 没有获得图片
    PUBLISHING_TASK_IMAGE_IS_EMPTY("-12" , "图片为空"),
    // 图片上传失败
    PUBLISHING_TASK_IMAGE_HAS_BEEN_SUCCESSFULLY_UPLOADED("-13" , "图片上传失败"),
    // 未找到任务
    TASK_NOT_FOUND("-14" , "未找到任务"),
    // 任务发布成功
    THE_TASK_WAS_SUCCESSFULLY_POSTDE("1" , "任务已发布"),
    // 任务发布失败
    TASK_POSTING_FAILED("-15" , "任务发布失败"),
    // 任务提前取消
    TASK_CANCELED_IN_ADVANCE("-16" , "任务提前取消"),
    // 任务审核通过
    AUDIT_PASSED_SUCCESS("1","审核通过"),
    // 任务审核通过失败
    AUDIT_PASSED_ERROR("-17","审核通过失败"),
    // 任务驳回成功
    AUDIT_REJECTION_SUCCESS("1","已驳回"),
    // 任务驳回失败
    AUDIT_REJECTION_ERROR("-18","驳回失败"),

    //购买失败，积分不足
    FIND_PRIZE_INTEGRAL_LACKOF("-19","积分不足"),
    //购买失败，超过上限
    FIND_PRIZE_CAP("-20","超过上限"),
    //购买成功
    FIND_PRIZE_SUCCESS("1","兑换成功"),
    //用户名或商品为空
    FIND_USER_PRIZE_NULL("-21","用户名或商品为空"),
    //删除成功
    DELELETE_SUCCESS("1","删除成功"),
    //删除失败
    DELETE_ERROR("-22","删除失败"),

    // 添加商品失败
    ADJUNCTION_ERROR("-23" , "添加商品失败"),
    //添加商品成功
    ADJUNCTION_SUCCESS("1" , "添加商品成功"),
    //没有查询到商品
    NO_GOODS_WERE_FOUND("-24" , "没有查询到商品"),
    // 修改商品成功
    MODIFICATION_SUCCESS("1" , "修改商品成功"),
    //修改商品失败
    MODIFICATION_ERROR("-26" , "修改商品失败"),
    //商品校检该商品已经存在
    PRIZENAME_EXIST("-27","商品名存在"),
    //商品校检可以添加
    PRIZENAME_CANADD("1","商品可以添加"),
    //上架成功
    GOODS_SHELVES("1","商品上架成功"),
    // 上架失败
    GOODS_ERROR("-28","商品上架失败"),

    GOODS_SOLDOUT_SUCCESS("1","商品下架成功"),

    GOODS_SOLDOUT_ERROR("-29","商品下架失败"),
    // 没有修改任何商品
    MODIFICATION_EMPTY("-30" , "修改商品失败"),

NOREASON("-48" , "未知错误"),





    //ZJH
    //100 用户错误  200 系统错误
    ALL_OPERATION_ERROR("-31","操作失败，请稍后重试"),
    //兑换记录
    EXCHANGE_STATUS_ERROR("-32","确认领取失败，请刷新页面重试"),
    USER_ADD_ERROR("-33","添加失败，工号已被使用，请更换工号"),
    USER_UP_ERROR("-34","修改失败，工号已被使用，请更换工号"),
    //职位
    POSITION_FIND_ERROR("-35","该部门尚未设置职位"),
    //部门
    DEPT_ADD_ERROR("-36","部门名已被使用，请更换部门名"),
    DEPT_UP_ERROR("-42","删除职位中有人"),
    DEPT_DEL_ERROR("-37","部门尚有员工，请将员工删除后再操作"),
    //信息
    USER_PASSWORD_CHECK("-38","原密码错误"),
    USER_LOGIN_ERROR("-39","用户名或者密码错误"),
    //权限
    ROLE_EMPTY("-40","权限列表为空，请先添加权限"),
    // token过期
    TOKEN_EXPIRED("-41" , "请重新登录"),
    PRIZE_NUMBER_ERROR("-43","请重新输入兑换数量"),
    PRIZE_USERSTATUS_ERROR("-44","请先解除积分冻结或离职后再兑换"),
    User_PASSWORD_ERROR("-45","请输入新密码"),
    USER_PASSWORD_EMPTY_CHECK("-46","未输入密码"),
    USER_PASSWORD_LONG_CHECK("-47","密码长度过短"),
    DEPT_ADD_OPTION_ERROR("-48","职位不能为空，请先添加部门"),
    // 待领取任务领取任务按钮—未开始
    RECIEVE_THE_TASK_STATUS_TIME_NOT_START("-49" , "任务未开始，稍等等吧。"),
    DEPT_ADD_OPTION_LENGTH_ERROR("-50","职位长度不能超过5位"),
    USER_ADD_USERID_ERROR("-51","工号不能为零或负数"),
    USER_AUTH_ADD_ERROR("-52","如要取消该用户所有权限，请直接删除"),
    USER_STATUS_ERROR("-53","用户积分已被冻结"),
    KEY_WORD_EMPTY("-54","请输入搜索关键词"),
    SELECT_USERID_EMPTY("-55","员工id查询为空"),
    ADD_PERTASK_ERROR("-56","添加全员的任务信息失败"),
    ADD_PERTASKUSER_ERROR("-57","添加人员与全员任务不匹配"),
    ADD_JOBSTART_ERROR("-58","添加定时开始任务失败"),
    ADD_JOBEND_ERROR("-59","添加定时结束任务失败"),
    END_TIME_ERROR("-60","结束时间小于当前时间"),
    TRIGGER_EMPTY("-61","触发器为空"),
    UPDATE_JOBSTART_ERROR("-62","修改定开始任务失败"),
    UPDATE_JOBEND_ERROR("-63","修改定时结束任务失败"),
    UPDATE_PERTASK_ERROR("-64","修改全员的任务信息失败"),
    DELETE_JOBSTART_ERROR("-65","删除定时开始任务失败"),
    DELETE_JOBEND_ERROR("-66","删除定时结束任务失败"),
    UPDATE_PERTASKSTATUS_ERROR("-64","修改全员领取状态失败"),
    DELETE_PERTASK_ERROR("-65","删除全员任务失败"),
    FIND_PERTASK_ERROR("-66","查找全员任务失败"),
    GETALLPERTASK_COUNT_FAIL("-67","查找正在执行人数失败"),
    FIND_PERTASKUSER_ERROR("-68","查找本人的全员任务失败"),
    GETALLPERTASK_FAIL("-69","领取任务失败"),
    GET_USERINFORMATION_ERROR("-70","获取人员信息失败"),
    UPDATE_INTEGRAL_ERROR("-71","修改积分明细表失败"),
    UPDATE_INTEGRALUSER_ERROR("-72","修改总积分失败"),
    GIVEUPALLPERTASK_FAIL("-73","取消(完成)任务失败"),
    //取消任务
    CANNEL_TASK_ERROR("-74" , "任务取消失败"),
    CANNEL_TASK_SUCCESS("-75" , "任务取消成功"),
    JIGEN_ERROR("-76" , "积分不足")
    ;

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
