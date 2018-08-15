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
     *任务中心-任务列表-已完成
     */
    public static final String MISSION_CENTER_TASK_LIST_COMPLETED = "completed";

    // 任务的状态,1为任务发布成功,2为任务已经被领取,3为提前取消,4为提交审核，
    // 5为任务完成失败,6为任务没人领取,7为任务完成了!56都为任务失效

    /**
     *任务的状态,1为任务发布成功
     */
    public static final int THE_TASK_WAS_SUCCESSFULLY_POSTED = 1;

    /**
     *任务的状态,2为任务已经被领取
     */
    public static final int THE_TASK_HAS_BEEN_RECEIVED = 2;

    /**
     *任务的状态,3为提前取消
     */
    public static final int TASK_CANCELDE_IN_ADVANCE = 3;

    /**
     *任务的状态,4为提交审核
     */
    public static final int TASK_SUBMISSION_REVIEW = 4;

    /**
     *任务的状态,5为任务完成失败
     */
    public static final int TASK_COMPLETION_FAILED = 5;

    /**
     *任务的状态,6为任务没人领取
     */
    public static final int NO_ONE_ACCEPTS_THE_TASK = 6;

    /**
     *任务的状态,7为任务完成了
     */
    public static final int MISSION_COMPLETED = 7;
}
