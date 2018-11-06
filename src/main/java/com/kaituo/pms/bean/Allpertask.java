package com.kaituo.pms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author wangduo
 * @date 2018/11/1 - 16:24
 */
@Data
public class Allpertask implements Serializable {
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
}
