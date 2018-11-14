package com.kaituo.pms.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 上传，下载，删除积分导入文件
 * @author @chnxy_xrabbit
 * @date 2018/11/13 15:52
 */
@Slf4j
public class uploadFile {

    public static String upload(MultipartFile file){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        //文件名称为日期+文件名称+后缀
        fileName=fileName.substring(0,fileName.lastIndexOf("."));
        fileName = format.format(new Date())+fileName+"--"+ UUID.randomUUID() + suffixName;
        // 检测是否存在目录
        String path=Util.getImgBasePath()+"\\image\\"+fileName;
        Util.makeDirPath(path);
        File dest = new File(path);
        if (dest.exists()){
            return "";
        }
        try {
            file.transferTo(dest);
            return fileName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    public static String download(HttpServletRequest request, HttpServletResponse response, String fileName){
        //得到要下载的文件名
        try {
            fileName = URLDecoder.decode(fileName,"utf-8");
            String fileSaveRootPath=Util.getImgBasePath()+"\\image";
            //通过文件名找出文件的所在目录
            //得到要下载的文件
            File file = new File(fileSaveRootPath + "\\" + fileName);
            //如果文件不存在
            if(!file.exists()){
                return "文件不存在";
            }
            //处理文件名
            String realname = fileName.substring(fileName.indexOf("_")+1);
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);
            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while((len=in.read(buffer))>0){
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "下载失败";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "下载失败";
        } catch (IOException e) {
            e.printStackTrace();
            return "下载失败";

        }
        return "下载完成";
    }
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            }
            return false;
        }
    }
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
