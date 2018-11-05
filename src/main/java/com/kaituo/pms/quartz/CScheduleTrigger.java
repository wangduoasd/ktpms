package com.kaituo.pms.quartz;

import lombok.Data;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/2 18:35
 */
@Data
public class CScheduleTrigger {
    private Integer id;
    private String cron;  //时间表达式
    private String status; //使用状态 0：禁用   1：启用
    private String jobName; //任务名称
    private String jobGroup; //任务分组
}
