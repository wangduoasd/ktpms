package com.kaituo.pms.controller;


import com.kaituo.pms.bean.Attendance;
import com.kaituo.pms.service.AttendacneService;
import com.kaituo.pms.utils.CalculationOfIntegralUtil;
import com.kaituo.pms.utils.CommonEnum;
import com.kaituo.pms.utils.OutPut;
import lombok.extern.slf4j.Slf4j;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendacneService attendacneService;



    /**
     * 上传Excel表格，并读取表格内容保存到数据库
     *
     * @param file
     */
    @PostMapping(value = "/uploadExcel")
    @ResponseBody
    public OutPut uploadExcel(@RequestParam("file") MultipartFile file) {

        OutPut outPut = new OutPut();
        String fileName = file.getOriginalFilename();
        try {
            attendacneService.uploadExcel(fileName, file);
            outPut.setCode(CommonEnum.SUCCESS.getCode());
            outPut.setMessage("上传成功");
            return outPut;
        } catch (Exception e) {
            e.printStackTrace();
            outPut.setCode(CommonEnum.ERROR.getCode());
            outPut.setMessage("上传失败");
            return outPut;
        }
    }


    /**
     * 将数据库attendance表中的数据查询出来
     */
    @GetMapping(value = "selectAttendance")
    @ResponseBody
    public OutPut selectAttendance() {
        OutPut outPut = new OutPut();
        try {
            List<Attendance> attendances = attendacneService.selectAll();
            outPut.setCode(CommonEnum.SUCCESS.getCode());
            outPut.setMessage("成功");
            outPut.setData(attendances);
            return outPut;
        } catch (Exception e) {
            outPut.setCode(CommonEnum.ERROR.getCode());
            outPut.setMessage("失败");
            return outPut;
        }
    }

    /**
     * 更新修改后的数据
     */
    @PostMapping(value = "/updateAttendances")
    @ResponseBody
    public OutPut updateAttendances(@RequestBody List<Attendance> attendanceList) {
        OutPut outPut = new OutPut();
        try {
            for (int i = 0; i < attendanceList.size(); i++) {
                attendacneService.updateByExample(attendanceList.get(i));
            }
            outPut.setCode(CommonEnum.SUCCESS.getCode());
            outPut.setMessage("成功");
            return outPut;
        } catch (Exception e) {
            e.printStackTrace();
            outPut.setCode(CommonEnum.ERROR.getCode());
            outPut.setMessage("失败");
            return outPut;
        }
    }

    /**
     * 生成积分
     */
    @PostMapping(value = "/calculationOfIntegral")
    @ResponseBody
    public OutPut calculationOfIntegral() throws ParseException {
        OutPut outPut = new OutPut();
        //便利数据库所有数据
        String attendancedata;
        JSONArray json;
        List<Attendance> attendances = attendacneService.selectByExample();
        Attendance attendance1 = attendacneService.selectById (1);
        int caluation = CalculationOfIntegralUtil.caluation (attendance1);
        System.err.println ("aaaaaaaaaaaaa="+caluation);
return null;/*
        for (int i = 0; i < attendances.size(); i++) { //便利所有人
            Attendance attendance = attendances.get(i·);
            ///////
            try {
                CalculationOfIntegralUtil.caluation(attendance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String yearMonth = attendance.getDatatime().substring(0, 6);
            System.out.println(yearMonth);
        }
        return outPut;*/
    }


    @PostMapping("test")
    @ResponseBody
    public OutPut test(){
        OutPut outPut = new OutPut();


        return outPut;
    }

    /**
     * 导入Excel表格，加减积分
     */
    @PostMapping("uploadExcelToIntergral")
    @ResponseBody
    public OutPut uploadExcelToIntergral(@RequestParam("file") MultipartFile file){
        OutPut outPut = new OutPut();
        String fileName = file.getOriginalFilename();
        try {
            List<Object> objects = attendacneService.uploadExcelToIntergral(fileName, file);
            outPut.setCode(CommonEnum.SUCCESS.getCode());
            outPut.setMessage("上传成功");
            outPut.setData(objects);
            return outPut;
        } catch (Exception e) {
            e.printStackTrace();
            outPut.setCode(CommonEnum.ERROR.getCode());
            outPut.setMessage("上传失败");
            return outPut;
        }

    }


}
