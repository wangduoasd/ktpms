package com.kaituo.pms.utils;

import com.kaituo.pms.bean.Attendance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalculationOfIntegralUtil {


    /**
     * @param attendance
     * @return
     * @throws ParseException
     */
    public static int caluation(Attendance attendance) throws ParseException {
        int att = 0;
        String attendancedata = attendance.getAttendancedata();
        Map<String, String> map = convertedToMap(attendancedata);
        //判断今天是否打卡了
        Map<String,String> map1 = new HashMap<>();
        //map1.put("2018-09-28","08:4517:32");
        if (map1 == null) {
            return 0;
        }
        //TODO 这里面可以判断一下错误的时间格式
        int attendanceIntergal;
        Calendar cal = Calendar.getInstance();
        Date date=new Date();
        String replace;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            attendanceIntergal = 0;
            //打卡的日期
             date = convertedToDate(entry.getKey());
            cal.setTime(date);
            //如果打卡记录为空
            if (entry.getValue() == "" || entry.getValue() == null) {
                //这个人没打卡，看今天是否加班
                    //需要加班
                    //判断加班日期是否在范围内
                    if (attendance.getStarttimeot()!=null&&attendance.getEndtimeot()!=null&&
                            date.after(attendance.getStarttimeot()) && date.before(attendance.getEndtimeot())
                        /*&& cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY*/) {
                        //在加班范围内
                        att -= 14;
                        attendanceIntergal -= 14;
                      //  System.err.println("加班"+entry.getKey()+"日积分为："+attendanceIntergal);
                        continue; //加班日没打卡，扣14分
                    }else{
                    //这个人没打卡，看着一天是否是工作日
                        replace = entry.getKey().replace("-", "");
                    int i = HolidayUtil.TimeDemo(replace);
                    if (i == 0) {  //工作日
                        att -= 9;
                        attendanceIntergal -= 9;
                        //System.err.println("工作日"+entry.getKey()+"日积分为："+attendanceIntergal);
                        continue ;//工作日没打卡直接扣分
                    } else { //非工作日
                        att+=0;
                        attendanceIntergal += 0;
                        //System.err.println("非工作日，不加班"+entry.getKey()+"日积分为："+attendanceIntergal);
                        continue ;//不是工作日，也不用加班，就没有积分
                    }
                }
            }else {
                //打卡记录不为空
                //打卡记录的格式是否有问题
                if (entry.getValue().length() == 5) {
                    if (attendance.getIsovertime() != 1) {    //漏打卡，不加班
                        //att += unnormalMiss(entry);
                        //attendanceIntergal += normalMiss(entry);
                        att -= 9;
                        attendanceIntergal -= 9;
                       // System.err.println("漏打卡，不加班" + entry.getKey() + "日积分为：" + attendanceIntergal);
                        continue;
                    }
                    if (date.after(attendance.getStarttimeot()) && date.before(attendance.getEndtimeot()) && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                        //在加班范围内
                        //att += normalMiss(entry);
                        //attendanceIntergal += unnormalMiss(entry);
                        att -= 14;
                        attendanceIntergal -= 14;
                        //System.err.println("漏打卡，加班" + entry.getKey() + "日积分为：" + attendanceIntergal);
                        continue;
                    }

                } else {
                    //正常打卡，格式没毛病
                    //加班制度
                    if (attendance.getIsovertime() == 1) {
                        if (date.after(attendance.getStarttimeot()) && date.before(attendance.getEndtimeot())) {
                            //在加班范围内
                            att += fourteenHoursSystem(entry);
                            attendanceIntergal += fourteenHoursSystem(entry);
                            //System.err.println("正常加班" + entry.getKey() + "日积分为：" + attendanceIntergal);
                            continue;
                        }
                    }
                    //正常制度
                    att += nineHoursSystem(entry);
                    attendanceIntergal += nineHoursSystem(entry);
                   // System.err.println("非加班正常" + entry.getKey() + "日积分为：" + attendanceIntergal);
                    continue;
                }
            }
        }
        System.out.println(attendance.getName()+"的积分是="+att);
        return att;
    }
    /**
     * 字符串转为Map
     *
     * @param attendancedata
     * @return
     */
    private static Map<String, String> convertedToMap(String attendancedata) {
        String replace = attendancedata.replace("{", "").replace("}", "");
        List split = Arrays.asList(replace.split(","));
        Map<String, String> map = new HashMap<>();
        String o;
        for (int a = 0; a < split.size(); a++) {
            o = (String) split.get(a);
            String[] split1 = o.split("=");
            String str1 = split1[0].trim();
            if (split1.length != 1) {
                String str2 = split1[1].trim();
                map.put(str1, str2);
            } else {
                map.put(str1, "");
            }
        }
        return map;
    }
    //字符串转为日期格式
    public static Date convertedToDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(str);
        return parse;
    }
    //9小时制度
    private static double nineHoursSystem(Map.Entry<String, String> entry) throws ParseException {
        String onWork = entry.getValue().substring(0, 5);//上班打卡时间
        String offWork = entry.getValue().substring(entry.getValue().length() - 5);//下班打卡时间
        int late = late(onWork, "09:00");
        int early = early( "18:00",offWork);
        if(late==0){
            onWork="09:00";
        }
        int i = workHours(onWork, offWork,"09:00");
        //System.out.println(entry.getKey()+"-"+i+"-"+early+"-"+late);
        return (i - late - early);
    }
    //14小时制度
    public static double fourteenHoursSystem(Map.Entry<String, String> entry) throws ParseException {
        String onWork = entry.getValue().substring(0, 5);//上班打卡时间
        String offWork = entry.getValue().substring(entry.getValue().length() - 5);//下班打卡时间
        int late = late(onWork, "08:30");
        if(late==0){
            onWork="08:30";
        }
        int early = early("22:30" , offWork);
        int i = workHours(onWork, offWork,"08:30");
        //System.out.println(entry.getKey()+"-"+i+"-"+early+"-"+late);
        return (i - late - early);
    }
    //工作小时计算
    public static int workHours(String small, String big,String standard) throws ParseException {
        SimpleDateFormat hoursSdf = new SimpleDateFormat("mm:ss");
        String compare=small.substring(0,1)+":00";
        int i=0;
        if (hoursSdf.parse(small).after(hoursSdf.parse(standard))) {
            if (hoursSdf.parse(small).after(hoursSdf.parse(compare))) {
                i=-1;
            }
        }
        long smallTime = 0;
        long bigTime = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoursSdf.parse(small));
        smallTime = cal.getTimeInMillis();
        cal.setTime(hoursSdf.parse(big));
        bigTime = cal.getTimeInMillis();
         i+= (int) (bigTime - smallTime) / (1000 * 60);
        return i;
    }
    //为了保留小数且上升用的，专用于早退的计算
    public static double workHoursDouble(String small, String big) throws ParseException {
        SimpleDateFormat hoursSdf = new SimpleDateFormat("mm:ss");
        double smallTime = 0;
        double bigTime = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoursSdf.parse(small));
        smallTime = cal.getTimeInMillis();
        cal.setTime(hoursSdf.parse(big));
        bigTime = cal.getTimeInMillis();
        double i = (bigTime - smallTime) / (1000 * 60);
        return i;
    }
    //迟到
    public static int late(String small, String big) throws ParseException {
        SimpleDateFormat hoursSdf = new SimpleDateFormat("mm:ss");
        if (hoursSdf.parse(small).after(hoursSdf.parse(big))) {
            double i = workHoursDouble(small, big);
            int a=(int)i;
            if(i!=a){
                a=a+1;
            }
            //System.out.println(small+"***"+a+i);
            return a;
        }
        return 0;
    }
    //早退
    public static int early(String small, String big) throws ParseException {
        SimpleDateFormat hoursSdf = new SimpleDateFormat("mm:ss");
        if (hoursSdf.parse(small).after(hoursSdf.parse(big))) {
            double i = workHoursDouble(big, small);
            int a = (int) i;
            if(i!=a){
                a=a+1;
            }
           // System.out.println(big+a+i);
            return a;
        }
        return 0;
    }
}
