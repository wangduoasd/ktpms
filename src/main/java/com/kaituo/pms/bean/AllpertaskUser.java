package com.kaituo.pms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @author wangduo
 * @date 2018/11/1 - 16:49
 */
@Data
public class AllpertaskUser implements Serializable {
    private Integer user_allpertask_id;
    private Integer allpertask_id;
    private Integer user_id;
    private Integer user_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date user_gettime;
}
