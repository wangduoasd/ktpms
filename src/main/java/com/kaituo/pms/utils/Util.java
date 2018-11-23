package com.kaituo.pms.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: pms
 * @description: 工具类
 * @author: su
 * @create: 2018-07-27 11:00
 **/
@Slf4j
public class Util {

    /**
     * System.getProperty()获取系统的执行属性，file.separator 文件分隔符，os.name系统名
     */
    public static String seperator = System.getProperty("file.separator");
    /**
     * 时间戳
     */
    private static final long date = System.currentTimeMillis();
    /**
     * 随机数
     */
    private static final Random r = new Random();

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
    public static Date stampToDate(String stamp) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = simpleDateFormat.parse (stamp);

//        long lt = new Long(stamp);
//        Date date = new Date(lt);
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
     * 删除文件
     * @Param:
     * @param fileName
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: 苏泽华
     * @Date: 2018/8/22
     */
    public static int imgDelect(String fileName){
       int out = Constant.IMG_DELECT_ERROR;
        File file = new File(getImgBasePath() + fileName);

        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if(file.exists() && file.isFile()){
            if(file.delete()){
                out = Constant.IMG_DELECT_SUCCESS;
            }
            return out;
        }else {
            out = Constant.IMG_DELECT_SUCCESS;
            return out;
        }
    }

    /**
     * 图片上传
     * @Param:
     * @param file 文件
     * @param targetAddr 相对路径 =》 Util.getImgRelativePath()
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: 苏泽华
     * @Date: 2018/8/22
     */
    public static Map<String , Object> imgUpload(MultipartFile file , String targetAddr){
        /**
         * 文件上传
         *
         * */
         Map<String,Object> outPut =new HashMap<>(2);
         outPut.put("code",Constant.IMG_UPLOSD_ERROR);
         outPut.put("url","");


         if(null==file || file.isEmpty()){
             outPut.put("code",Constant.IMG_UPLOSD_EMPTY);
             return outPut;
         }


         String fileName = getRandomFileName()+getFileExtension(file);
         int size = (int) file.getSize();

         log.info(fileName + size);

        String relativeAddr = targetAddr + fileName;
        // 创建目录
        makeDirPath(targetAddr);

        File dest = new File(getImgBasePath() + relativeAddr);

        try {

            //保存文件
            file.transferTo(dest);
            outPut.put("code",Constant.IMG_UPLOSD_SUCCESS);
            outPut.put("url",targetAddr+fileName);
            return outPut;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("com.kaituo.pms.utils.Util.imgUpload ==>"+e.getMessage() , e);
            return outPut;
        }
    }

    /**
     * 生成随机文件名 ， 当前时间戳+5位随机数
     * @Param:
     * @param
     * @return: java.lang.String
     * @Author: 苏泽华
     * @Date: 2018/8/17
     */
    private static String getRandomFileName(){
        // 获取随机5位数
        int rannum = r.nextInt(89999) + 10000;
        return date+""+rannum;
    }

    /**
     * 获取文件输入流的扩展名
     * @Param:
     * @param file
     * @return: java.lang.String
     * @Author: 苏泽华
     * @Date: 2018/8/17
     */
    private static String getFileExtension(MultipartFile file){
        String fileExtension = file.getOriginalFilename();
        return fileExtension.substring(fileExtension.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及到的目录
     * @Param:
     * @param targetAddr 路径
     * @return: void
     * @Author: 苏泽华
     * @Date: 2018/8/17
     */
    public static void  makeDirPath(String targetAddr){
        String realFileParentPath = getImgBasePath()+targetAddr;
        File dirPath = new File(realFileParentPath);
        // 判断路径是否存在
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 文件存放位置
     * @Param:
     * @param
     * @return: java.lang.String
     * @Author: 苏泽华
     * @Date: 2018/8/17
     */
    public static String getImgBasePath(){
        // 获取操作系统
        String os = System.getProperty("os.name");
        // 声明路径
        String basePath = "";

        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/fileUpload";
        } else {
            basePath = "/var/www/jifen.kaituo.local";
        }
        basePath = basePath.replace("/" , seperator);
        return basePath;
    }
    /**
     * 相对路径
     * @Param:
     * @param
     * @return: java.lang.String
     * @Author: 苏泽华
     * @Date: 2018/8/17
     */
    public static String getImgRelativePath(){
        // 获取操作系统
        String os = System.getProperty("os.name");
        // 声明路径
        String relativePath = "";

        if(os.toLowerCase().startsWith("win")){
            relativePath = "/image/";
        } else {
            relativePath = "/img/";
        }
        relativePath = relativePath.replace("/" , seperator);
        return relativePath;
    }
    public static Timestamp getTimestamp() {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return  Timestamp.valueOf(time);
    }
}
