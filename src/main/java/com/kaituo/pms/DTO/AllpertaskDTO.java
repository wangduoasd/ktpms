package com.kaituo.pms.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wangduo
 * @date 2018/11/9 - 17:35
 */
@Data
public class AllpertaskDTO {
    /**
     * 全员任务表数据
     */
    private Integer allpertask_id;
    private String allpertask_name;
    private Integer allpertask_difficulty;
    private Integer allpertask_price;
    private Integer allpertask_award;
    private Integer allpertask_time;
    private Date allpertask_starttime;
    private Date allpertask_endtime;
    private Integer allpertask_status;
    private Date allpertask_currenttime;
    /**
     * 全员任务中间表数据
     */
    private Integer user_status;
    private Date user_gettime;
    /**
     * 领取人数（）
     */
    private Integer count;
}
