package com.kaituo.pms.controller;


import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.dao.AttendanceMapper;
import com.kaituo.pms.service.AttendacneService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Controller
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendacneService attendacneService;

    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    TokenService tokenService;
    /**
     * 上传Excel表格，并读取表格内容保存到数据库
     *
     * @param file
     */
    @PostMapping(value = "/uploadExcel")
    @ResponseBody
    public OutJSON uploadExcel(@RequestParam("file") MultipartFile file) {
        String token =ContextHolderUtils.getRequest().getHeader("token");
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }

        String fileName = file.getOriginalFilename();
        try {
            attendacneService.uploadExcel(fileName, file);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }


    /**
     * 将数据库attendance表中的数据查询出来
     */
    @GetMapping(value = "selectAttendance")
    @ResponseBody
    public OutJSON selectAttendance() {
        String token =ContextHolderUtils.getRequest().getHeader("token");
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            List<Attendance> attendances = attendacneService.selectAll();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,attendances);
        } catch (Exception e) {
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 更新修改后的数据
     */
    @PostMapping(value = "/updateAttendances")
    @ResponseBody
    public OutJSON updateAttendances(@RequestBody List<Attendance> attendanceList) {
        String token =ContextHolderUtils.getRequest().getHeader("token");
        Token token1 = tokenService.selectUserIdByToken(token);
        System.out.println(token1);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            for (int i = 0; i < attendanceList.size(); i++) {
                attendacneService.updateByExample(attendanceList.get(i));
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 生成积分
     */
    @PostMapping(value = "/calculationOfIntegral")
    @ResponseBody
    public OutJSON calculationOfIntegral( /*@PathVariable("token") String token*/) throws ParseException {

//        Token token1 = tokenService.selectUserIdByToken(token);
//        System.out.println(token1);
//        if (null == token1){
//            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
//        }
        try {
            List<Attendance> attendances = attendacneService.selectByExample();
            attendacneService.calculationOfIntegral(attendances);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }



    /**
     * 导入Excel表格，加减积分
     */
    @PostMapping("uploadExcelToIntergral")
    @ResponseBody
    public OutJSON uploadExcelToIntergral(@RequestParam("file") MultipartFile file){
        String token =ContextHolderUtils.getRequest().getHeader("token");
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        String fileName = file.getOriginalFilename();
        try {
            List<Object> objects = attendacneService.uploadExcelToIntergral(fileName, file);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,objects);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }

    }

    /**
     * 删除所有attendance
     * @return
     * @throws ParseException
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public OutJSON deleteAll() throws ParseException {
        String token =ContextHolderUtils.getRequest().getHeader("token");
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            attendanceMapper.deleteAll();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 更新计算过后数据的员工积分
     * @return
     * @throws ParseException
     */
    @PostMapping("updateEndNum")
    @ResponseBody
    public OutJSON updateEndNum() throws ParseException {
        String token =ContextHolderUtils.getRequest().getHeader("token");
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            attendanceMapper.updateEndNum();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    //文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test") MultipartFile file) {
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
        fileName = format.format(new Date())+fileName + suffixName;
        // 检测是否存在目录
        String path=Util.getImgBasePath()+"\\image\\";
        Util.makeDirPath(path);
        File dest = new File(path);
        if (dest.exists()){
            return "文件已存在";
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response,String fileName) throws IOException {
        //得到要下载的文件名
         fileName = URLDecoder.decode(fileName,"utf-8");
        String fileSaveRootPath="D:\\fileUpload\\image";
        //通过文件名找出文件的所在目录
        //得到要下载的文件
        File file = new File(fileSaveRootPath + "\\" + fileName);
        //如果文件不存在
        if(!file.exists()){
            System.out.println("您要下载的资源已被删除！！");
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
    }

}


