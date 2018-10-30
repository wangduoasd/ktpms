package com.kaituo.pms.utils;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HolidayUtil {
    /**
     * @param urlAll  :请求接口 http://tool.bitefu.net/jiari/vip.php
     * @param httpArg :日期参数yyyyMMdd
     * @return 返回结果
     */
    public static JSONObject dateRequest(String httpArg) {
        BufferedReader reader = null;
        String result = null;
        JSONObject jsonObjectResult = null;
        StringBuffer sbf = new StringBuffer();
        String httpUrl = "http://api.goseek.cn/Tools/holiday?date=" + httpArg;
        //工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            jsonObjectResult = JSONObject.fromObject(result);//转为JSONObject对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObjectResult;
    }



    public static int TimeDemo(String timeDate){
        JSONObject object = dateRequest(timeDate);
        return (int) object.get("data");

    }

}

