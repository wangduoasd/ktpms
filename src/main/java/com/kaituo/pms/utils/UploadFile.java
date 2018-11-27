package com.kaituo.pms.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.kaituo.pms.error.MyException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 上传，下载，删除积分导入文件
 * @author @chnxy_xrabbit
 * @date 2018/11/13 15:52
 */
@Slf4j
public class UploadFile {

    String fileDir= Util.getImgBasePath()+"\\";
    String demoDir = "file";
    String demoPath = demoDir + File.separator;

    /**
     * 文件上传
     * @param fileName
     * @param file
     * @return
     * @throws JsonProcessingException
     */
    public  boolean upload(String  fileName,MultipartFile file) throws JsonProcessingException {

        // 判断该文件类型是否有上传过，如果上传过则提示不允许再次上传
//        if (existsTypeFile(fileName)) {
//            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "每一种类型只可以上传一个文件，请先删除原有文件再次上传", null));
//        }
        File outFile = new File(fileDir + demoPath);
        if (!outFile.exists()) {
            outFile.mkdirs();
        }
        try(InputStream in = file.getInputStream();
            OutputStream ot = new FileOutputStream(fileDir + demoPath + fileName)){
            byte[] buffer = new byte[1024];
            int len;
            while ((-1 != (len = in.read(buffer)))) {
                ot.write(buffer, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @param fileName
     * @return
     */
    public Boolean download(HttpServletRequest request, HttpServletResponse response, String fileName){
        //得到要下载的文件名
        try {
//            fileName = URLDecoder.decode(fileName,"utf-8");
//            String fileSaveRootPath=Util.getImgBasePath()+"\\file";
            //通过文件名找出文件的所在目录
            //得到要下载的文件
            File file = new File(fileDir + demoPath);
            //如果文件不存在
            if(!file.exists()){
                return false;
            }
            //处理文件名
            String realname = fileName.substring(fileName.indexOf("_")+1);
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(fileDir + demoPath + fileName);

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
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        return true;
    }

    /**
     * 文件删除
     * @param fileName
     * @return
     */
    public  boolean delete(String fileName) {
        fileName=fileDir + demoPath + fileName;
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
    /**
     * 文件夹删除
     * @param fileName
     * @return
     */
    public  boolean deleteFile(String fileName) {
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

    /**
     * 获取文件夹中文件列表
     * @return
     * @throws JsonProcessingException
     */
    public String getFiles() throws JsonProcessingException {
        List<Map<String, String>> list = Lists.newArrayList();
        File file = new File(fileDir + demoPath);
        if (file.exists()) {
            Arrays.stream(file.listFiles()).forEach(file1 -> list.add(ImmutableMap.of("fileName", file1.getName())));
        }
        return new ObjectMapper().writeValueAsString(list);
    }
}
