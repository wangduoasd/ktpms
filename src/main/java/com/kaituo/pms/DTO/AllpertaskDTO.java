package com.kaituo.pms.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author wangduo
 * @date 2018/11/9 - 17:35
 * 数据拼装层
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date allpertask_starttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date allpertask_endtime;
    private Integer allpertask_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date allpertask_currenttime;
    private String allpertask_image;
    private String allpertask_describe;
    /**
     * 全员任务中间表数据
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date user_gettime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date user_finishtime;
    private Integer user_status;
    private Integer user_id;
    private Integer resttime;
}
