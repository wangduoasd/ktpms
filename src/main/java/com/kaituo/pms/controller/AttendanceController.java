package com.kaituo.pms.controller;


import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.dao.AttendanceMapper;
import com.kaituo.pms.service.AttendacneService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.utils.*;
import lombok.extern.slf4j.Slf4j;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Arrays;
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
    @PostMapping(value = "/calculationOfIntegral/{token:.+}")
    @ResponseBody
    public OutJSON calculationOfIntegral( @PathVariable("token") String token) throws ParseException {
        System.out.println("*********"+token);
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            List<Attendance> attendances = attendacneService.selectByExample();
            attendacneService.calculationOfIntegral(attendances);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    @PostMapping("test")
    @ResponseBody
    public OutPut test(){
        OutPut outPut = new OutPut();
        attendacneService.test();
        return outPut;
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
     * 更新计算过后数据到员工积分
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
}
