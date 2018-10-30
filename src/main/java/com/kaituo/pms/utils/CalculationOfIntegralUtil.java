package com.kaituo.pms.utils;

import com.kaituo.pms.bean.Attendance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalculationOfIntegralUtil {

    public static SimpleDateFormat hoursSdf = new SimpleDateFormat("mm:ss");

    public static int caluation(Attendance attendance) throws ParseException {
        int att = 0;
        String attendancedata = attendance.getAttendancedata();
        Map<String, String> map = convertedToMap(attendancedata);
        //判断今天是否打卡了
        System.out.println("map==" + map);

        Map<String,String> map1 = new HashMap<>();
        map1.put("2018-09-28","08:4517:32");
        if (map1 == null) {
            return 0;
        }
        int attendanceIntergal;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            attendanceIntergal = 0;
            //打卡的日期
            Date date = convertedToDate(entry.getKey());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            //如果打卡记录为空
            if (entry.getValue() == "" || entry.getValue() == null) {
                //这个人没打卡，看着一天是否是工作日
                String replace = entry.getKey().replace("-", "");

                int i = HolidayUtil.TimeDemo(replace);
                if (i == 0) {  //工作日
                    att -= 9;
                    attendanceIntergal -= 9;
                    System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                    continue ;//工作日没打卡直接扣分
                } else { //非工作日
                    //判断是否需要加班
                    if (attendance.getIsovertime() == 1) {
                        //需要加班
                        //判断加班日期是否在范围内
                        if (date.after(attendance.getStarttimeot()) && date.before(attendance.getEndtimeot()) && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                            //在加班范围内
                            att -= 14;
                            attendanceIntergal -= 14;
                            System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                            continue; //加班日没打卡，扣14分
                        }
                    }
                    att+=0;
                    attendanceIntergal += 0;
                    System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                    continue ;//不是工作日，也不用加班，就没有积分
                }
            }
            //打卡记录不为空
            //打卡记录的格式是否有问题
            if (entry.getValue().length() == 5) {
                //打卡有问题，要么漏打卡，要么通宵了
                if (attendance.getIswholenight() == 1) {
                    //说明通宵了
                    //查看当前日期是否为通宵日期，不为则过
                    List<String> neightList = Arrays.asList(attendance.getWholenightdate().split(","));
                    if (neightList.contains(entry.getKey())) {
                        //今天他通宵了
                        //通宵算法
                        att += wholeNightSystem(entry);
                        attendanceIntergal += wholeDayTimeSystem(entry);
                        System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                        continue;
                    }
                    //判断当前日期的上一天
                    cal.setTime(date);
                    cal.add(Calendar.DAY_OF_MONTH, -1);

                    if (neightList.contains(cal.getTime())) {
                        att += wholeDayTimeSystem(entry);//通宵2
                        attendanceIntergal += wholeDayTimeSystem(entry);
                        System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                        continue;
                    }

                }
                //没有通宵。漏打卡
                if(attendance.getIsovertime() != 1){    //漏打卡，不加班
                    //att += unnormalMiss(entry);
                    //attendanceIntergal += normalMiss(entry);
                    att -= 9;
                    attendanceIntergal -= 9;
                    System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                    continue;
                }
                if (date.after(attendance.getStarttimeot()) && date.before(attendance.getEndtimeot()) && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    //在加班范围内
                    //att += normalMiss(entry);
                    //attendanceIntergal += unnormalMiss(entry);
                    att -= 14;
                    attendanceIntergal -= 14;
                    System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                    continue;
                }

            } else {
                //正常打卡，格式没毛病
                //加班制度
                if(attendance.getIsovertime() == 1){
                    if ( date.after(attendance.getStarttimeot()) && date.before(attendance.getEndtimeot())) {
                        //在加班范围内
                        att += fourteenHoursSystem(entry);
                        attendanceIntergal += fourteenHoursSystem(entry);
                        System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                        continue;
                    }
                }

                //正常制度
                att += nineHoursSystem(entry);
                attendanceIntergal += nineHoursSystem(entry);
                System.err.println(entry.getKey()+"日积分为："+attendanceIntergal);
                continue;
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

        System.out.println("切割的格式==="+replace);
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
    private static int nineHoursSystem(Map.Entry<String, String> entry) throws ParseException {
        String onWork = entry.getValue().substring(0, 5);//上班打卡时间
        String offWork = entry.getValue().substring(entry.getValue().length() - 5);//下班打卡时间

        int late = late(onWork, "09:00");
        int early = early( "18:00",offWork);
        int i = workHours(onWork, offWork);
        return (i - late - early);
    }

    //14小时制度
    public static int fourteenHoursSystem(Map.Entry<String, String> entry) throws ParseException {
        String onWork = entry.getValue().substring(0, 5);//上班打卡时间
        String offWork = entry.getValue().substring(entry.getValue().length() - 5);//下班打卡时间

        int late = late(onWork, "08:30");
        int early = early("22:30" , offWork);
        int i = workHours(onWork, offWork);
        return (i - late - early);
    }

    //通宵制度
    public static int wholeNightSystem(Map.Entry<String, String> entry) throws ParseException {
        String onWork = entry.getValue().substring(0, 5);//上班打卡时间
        int late = late(onWork, "09:00");
        int i = workHours(onWork, "23:59");
        return (i - late);
    }

    //通宵制度(转天)
    public static int wholeDayTimeSystem(Map.Entry<String, String> entry) throws ParseException {
        String onWork = entry.getValue().substring(0, 5);//下班打卡时间

        int i = workHours("00:00", onWork);
        return (i);
    }

    //工作小时计算
    public static int workHours(String small, String big) throws ParseException {

        long smallTime = 0;
        long bigTime = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoursSdf.parse(small));
        smallTime = cal.getTimeInMillis();
        cal.setTime(hoursSdf.parse(big));
        bigTime = cal.getTimeInMillis();
        int i = (int) (bigTime - smallTime) / (1000 * 60);
        return i;
    }

    //为了保留小数且上升用的，专用于早退的计算
    public static double workHoursDouble(String small, String big) throws ParseException {

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
        if (hoursSdf.parse(small).after(hoursSdf.parse(big))) {
            int i = workHours(small, big);

            System.out.println("迟到了" + (i) + "小时");
            return i;
        }
        return 0;
    }

    //早退
    public static int early(String small, String big) throws ParseException {
        if (hoursSdf.parse(small).after(hoursSdf.parse(big))) {
            double i = workHoursDouble(big, small);
            int a = (int) i;
            if (i > a) {
                a += 1;
            }
            System.out.println("早退了" + (i) + "小时");
            return a;
        }
        return 0;
    }

    //漏打卡
    public static int normalMiss(Map.Entry<String, String> entry) throws ParseException {
        String value = entry.getValue();
        if (hoursSdf.parse(value).before(hoursSdf.parse("12:00"))) {//上午
            int late = late(value, "09:00");
            int i = workHours(value, "12:00");
            return (i - late - 4);
        } else {

            int early = early("18:00", value);

            int i = workHours("13:00", value);
            return (i - early - 5);
        }
    }

    //漏打卡加班状态
    public static int unnormalMiss(Map.Entry<String, String> entry) throws ParseException {
        String value = entry.getValue();
        if (hoursSdf.parse(value).before(hoursSdf.parse("12:00"))) {//上午
            int late = late(value, "08:30");
            int i = workHours(value, "12:00");
            return (i - late);
        } else {

            int early = early("22:30", value);

            int i = workHours("13:00", value);
            return (i - early);
        }
    }
}
