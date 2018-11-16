package com.kaituo.pms.controller;


import com.kaituo.pms.DTO.AttendanceDTO;
import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.bean.FileUploadRecord;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.dao.AttendanceMapper;
import com.kaituo.pms.dao.FileUploadRecordMapper;
import com.kaituo.pms.service.AttendacneService;
import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendacneService attendacneService;
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    TokenService tokenService;
    @Autowired
    FileUploadRecordMapper fileUploadRecordMapper;
    @Autowired
    RoleService roleService;
    /**
     * 上传Excel表格，并读取表格内容保存到数据库
     *
     * @param file
     */
    @PostMapping(value = "/uploadExcel")
    @ResponseBody
    public OutJSON uploadExcel(@RequestParam("file") MultipartFile file,String username) {

        System.out.println(username);
        String fileName = file.getOriginalFilename();
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            Boolean flag=false;
            //读取表格内容保存到数据库
            if (username==""||username==null) {
                flag=false;
            }else {
                attendanceMapper.deleteAll();
                flag = attendacneService.uploadExcel(fileName, file);
            }
            if(flag){
                attendacneService.uploadFile(file,username);
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
            }else {
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
            }
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
        try {
//            String token =ContextHolderUtils.getRequest().getHeader("token");
//            // 检查token并获得userID
//            Token token1 = tokenService.selectUserIdByToken(token);
//            if (null == token1){
//                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
//            }
//            // 权限控制
//
//            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
//                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
//            }
            List<Attendance> attendances = attendacneService.selectAll();
            List<AttendanceDTO> attendanceDTOS=Util.AttendanceConv(attendances);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,attendanceDTOS);
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

        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
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
    public OutJSON calculationOfIntegral() throws ParseException {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
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
     * 更新计算过后数据的员工积分
     * @return
     * @throws ParseException
     */
    @PostMapping("updateEndNum")
    @ResponseBody
    public OutJSON updateEndNum() throws ParseException {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            attendanceMapper.updateEndNum();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 查询所有上传文件（积分考勤表）
     * @return
     */
    @RequestMapping(value = "selectAllRecord")
    @ResponseBody
    public OutJSON selectAllRecord() {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            List<FileUploadRecord> list=fileUploadRecordMapper.selectAllRecord();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 下载服务器文件（积分考勤表）
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public OutJSON download(HttpServletRequest request, HttpServletResponse response, String fileName)  {
        try {
            String message=uploadFile.download(request,response,fileName);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,message);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 删除服务器文件（积分考勤表）及数据库记录
     * @param fileName
     * @return
     * @throws IOException
     */
    @PostMapping("/deleteFile")
    public OutJSON deleteFile(String fileName)  {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                return  OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            boolean flag=attendacneService.downFile(fileName);
            if(flag){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,flag);
            }else {
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR,flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}


