package com.kaituo.pms.utils;

/**
 * @program: ktpms
 * @description: 各种常量
 * @author: 由苏泽华创建
 * @create: 2018-08-14 15:41
 **/

public class Constant {
    /**
     * 任务中心—任务列表-待领取
     * */
    public static final String MISSION_CENTER_TASK_LIST_PENDING= "Pending";
    /**
     * 任务中心—任务列表-未完成
     */
    public static final String MISSION_CENTER_TASK_LIST_UNDONE= "undone";
    /**
     * 任务中心-任务列表-已完成
     */
    public static final String MISSION_CENTER_TASK_LIST_COMPLETED = "completed";

    /**
     * 任务的状态,1为任务发布成功
     */
    public static final int THE_TASK_WAS_SUCCESSFULLY_POSTED = 1;

    /**
     * 任务的状态,2为任务已经被领取
     */
    public static final int THE_TASK_HAS_BEEN_RECEIVED = 2;

    /**
     * 任务的状态,3为提前取消
     */
    public static final int TASK_CANCELDE_IN_ADVANCE = 3;

    /**
     * 任务的状态,4为提交审核
     */
    public static final int TASK_SUBMISSION_REVIEW = 4;

    /**
     * 任务的状态,5为任务完成失败
     */
    public static final int TASK_COMPLETION_FAILED = 5;

    /**
     * 任务的状态,6为任务没人领取
     */
    public static final int NO_ONE_ACCEPTS_THE_TASK = 6;

    /**
     *任务的状态,7为任务完成了
     */
    public static final int MISSION_COMPLETED = 7;

    /**
     * 图片上传失败
     */
    public static final int IMG_UPLOSD_ERROR = 0;

    /**
     * 图片删除失败
     */
    public static final int IMG_DELECT_ERROR = 0;

    /**
     * 图片上传成功
     */
    public static final int IMG_UPLOSD_SUCCESS = 1;

    /**
     * 图片删除成功
     */
    public static final int IMG_DELECT_SUCCESS = 1;

    /**
     *图片上传为空
     */
    public static final int IMG_UPLOSD_EMPTY = 2;

    /**
     *图片删除为空
     */
    public static final int IMG_DELECT_EMPTY = 2;

    /**
     * 风控中心  任务管理  已发布任务
     */
    public static final String RISK_CONTROL_CENTER_TASK_MANAGEMENT_RELEASED_TASK = "released";

    /**
     * 风控中心  任务管理  待审核任务
     */
    public static final String RISK_CONTROL_CENTER_TASK_MANAGEMENT_PENDING_TASK = "pendingReview";

    /**
     * 风控中心  任务管理  失效任务
     */
    public static final String RISK_CONTROL_CENTER_TASK_MANAGEMENT_INVALID_TASK = "invalid";

    /**
     * 审核状态 通过
     */
    public static final int AUDIT_PASSED = 0;

    /**
     * 审核状态 驳回
     */
    public static final int AUDIT_REJECTION = 2;

    // 用户权限
    public static final int ROLE_TASK = 1;
    public static final int ROLE_DEPT = 2;
    public static final int ROLE_USER_SET = 3;
    public static final int ROLE_PRIZE_RELEASE = 4;
    public static final int ROLE_SURE_EXCHANGE = 5;
    public static final int ROLE_ALL = 6;



}
