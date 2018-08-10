package com.kaituo.pms.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: pms
 * @description: 工具类
 * @author: su
 * @create: 2018-07-27 11:00
 **/

public class Util {

    /**
     * 将时间转换为时间戳
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    public static long dateToStamp(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(dateStr);
        long ts = date.getTime();
        return ts;
    }

    /** 
    * 将时间戳转换为时间
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    public static Date stampToDate(String stamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(stamp);
        Date date = new Date(lt);
        return date;
    }

    /**
     * @Description: 将时间转为指定格式的字符串
     * @Param: [date]
     * @return: java.lang.String
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    public static String dateUtil(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /** 
    * @Description: 图片上传
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/8/3 
    */

    public Map<String , Object> imgUpload(MultipartFile file, String methodName){
        /**
         * 文件上传
         *
         * */
            Map<String,Object> outPut =new HashMap<>();
            outPut.put("message","上传失败！");
            outPut.put("code","0");
            outPut.put("data","");


            if(file.isEmpty()){
                outPut.put("message","文件为空！");
                return outPut;
            }

            String name = methodName;
            long date = System.currentTimeMillis();
            String fileName = date+name;
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            String path = "E:/fileUploadTest" ;
            File dest = new File(path + "/" + fileName);
            //判断文件父目录是否存在
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }


            try {

                //保存文件
                file.transferTo(dest);
                outPut.put("message","上传成功！");
                outPut.put("code","0");
                outPut.put("data","E:/fileUploadTest/"+fileName);
//                System.out.println("1");
                return outPut;
            } catch (Exception e) {
                return outPut;
            }
    }
}
