package com.kaituo.pms.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/16 17:54
 */
@Data
public class AttendanceDTO {
    private Integer id;

    private String jobnumber; //用户工号

    private String name;//员工姓名

    private String deptname;//部门名称

    private Date starttimeot;//加班开始时间

    private Date endtimeot;//加班结束时间

    private Integer deductintegral;//获得的积分

    private String datatime;//表格的日期

}
