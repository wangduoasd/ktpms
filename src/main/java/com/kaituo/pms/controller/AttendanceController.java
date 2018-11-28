package com.kaituo.pms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaituo.pms.DTO.AttendanceDTO;
import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.bean.ChangeIntegral;
import com.kaituo.pms.bean.FileUploadRecord;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.dao.AttendanceMapper;
import com.kaituo.pms.dao.FileUploadRecordMapper;
import com.kaituo.pms.previewModel.ReturnResponse;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    public OutJSON uploadExcel(@RequestParam("file") MultipartFile file,String username,Integer status) {
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
                //删除数据库Attendance中存在的全部数据
                attendanceMapper.deleteAll();
                //写入数据
                flag = attendacneService.uploadExcel(fileName, file);
            }
            if(flag){
                //上传文件，写入数据库。
                attendacneService.uploadFile(file,username,status);
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
    public String  selectAttendance() throws JsonProcessingException{
        String token =ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
        }
        try {
            List<Attendance> attendances = attendacneService.selectAll();
            List<AttendanceDTO> attendanceDTOS=Util.AttendanceConv(attendances);
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
        } catch (Exception e) {
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "FAILURE", null));
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
            //传入参数，字符串2018-10-12转换成时间，为 2018-10-12 08:00:00,需要转换日期，减去八小时
            for (int i = 0; i < attendanceList.size(); i++) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                if (attendanceList.get(i).getStarttimeot() != null) {
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(attendanceList.get(i).getStarttimeot());
                    ca.add(Calendar.HOUR_OF_DAY, -8);
                    attendanceList.get(i).setStarttimeot(f.parse(f.format(ca.getTime())));
                    ca.setTime(attendanceList.get(i).getEndtimeot());
                    ca.add(Calendar.HOUR_OF_DAY, -8);
                    attendanceList.get(i).setEndtimeot(f.parse(f.format(ca.getTime())));
                }
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
    public String calculationOfIntegral() throws JsonProcessingException {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
            }
            List<Attendance> attendances = attendacneService.selectByExample();
            attendacneService.calculationOfIntegral(attendances);
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "FAILURE", null));
        }
    }

    /**
     * 更新计算过后数据的员工积分，即合并积分
     * @return
     * @throws ParseException
     */
    @PostMapping("updateEndNum")
    @ResponseBody
    public String updateEndNum() throws JsonProcessingException {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_TASK,token1.getUserId())){
                new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "请重新登录", null));
            }
            List<Attendance> attendances=attendanceMapper.selectAll();
            for (Attendance a:attendances
                 ) {
                if(a.getIsovertime()==1||a.getIsovertime().equals(1)){
                    return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "积分已合并", null));
                }
            }
            attendanceMapper.updateEndNum();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "SUCCESS", null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(-1, "FAILURE", null));
        }
    }
    /**
     　  * @Description: 上传奖惩Excel表格，未写入数据库
     　　* @param  status "pre" 预览 "up"上传
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/11/22 0022 11:21
     　　*/
    @PostMapping("uploadExcelToIntergral/{status}")
    @ResponseBody

    public OutJSON uploadExcelToIntergral(@RequestParam("file") MultipartFile file,@PathVariable("status") String status) {

        String fileName = file.getOriginalFilename();
        String token = ContextHolderUtils.getRequest().getHeader("token");
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1) {
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        // 权限控制
        if (roleService.checkRole(Constant.ROLE_DEPT, token1.getUserId())) {
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            if (status.equals(Constant.PRE_UP_EXCEL)) {
                List<ChangeIntegral> objects = (List<ChangeIntegral>) attendacneService.upLoadExcelToIntergral(fileName, file, status,token1.getUserId());
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, objects);
            } else if (status.equals(Constant.UP_EXCEL)) {
                Object o = attendacneService.upLoadExcelToIntergral(fileName, file, status,token1.getUserId());
                if (null == o) {
                    return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
                } else {
                    return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
                }
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    @RequestMapping("rewardExcel")
    public void orderExport(HttpServletRequest request, HttpServletResponse response) {

        // 导出文件的标题
        String title = "奖惩导入模板" + Util.dateUtil(new Date()) + ".xls";
        // 设置表格标题行
        String[] headers = new String[]{"工号", "姓名", "变动值", "原因"};
        // 使用流将数据导出
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = new Object[]{"10000","张三","10","迟到"};
        dataList.add(objs);
        OutputStream out = null;
        try {
            // 防止中文乱码
            String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "ISO8859-1") + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            out = response.getOutputStream();

            ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);
            ex.export(out);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

        }
    }
//    /**
//     * 查询所有上传文件（积分考勤表）
//     * @return
//     */
//    @RequestMapping(value = "selectAllRecord")
//    @ResponseBody
//    public OutJSON selectAllRecord() {
//        try {
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
//            List<FileUploadRecord> list=fileUploadRecordMapper.selectAllRecord();
//            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
//        }
//    }

}


