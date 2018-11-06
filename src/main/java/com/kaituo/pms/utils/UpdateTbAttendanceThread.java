package com.kaituo.pms.utils;

import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.dao.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/5 18:45
 */
public class UpdateTbAttendanceThread implements Runnable {
    private Attendance attendance;
    private AttendanceMapper attendanceMapper;

    public UpdateTbAttendanceThread(Attendance attendance,AttendanceMapper attendanceMapper) {
        this.attendance=attendance;
        this.attendanceMapper=attendanceMapper;
    }

    @Override
    public void run() {
        int attr= 0;
        try {

            attr = CalculationOfIntegralUtil.caluation(attendance);
            attendanceMapper.updateDeductintegral(attendance.getId(),attr);
            System.out.println(attr);
            System.out.println(attendance.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
