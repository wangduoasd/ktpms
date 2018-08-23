package com.kaituo.pms.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Dept;
import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.List;

/**
 * @author 张金行
 * @version 1.0
 * @Title: DeptController
 * @ProjectName pms
 * @Description:
 * @date 2018/8/21 002116:20
 */
@Slf4j
@Controller
@CrossOrigin
public class DeptController {
    @Autowired
    DeptService deptService;
    @ResponseBody
    @GetMapping(value = "depts/{pn}")
    public OutJSON findAllDept(@PathVariable(value = "pn") int pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "8") int pageSize
    ) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            List<Dept> list = deptService.findAllDept();
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    @ResponseBody
    @PostMapping (value = "dept")
    public OutJSON addDept(Dept dept,@RequestParam("positionArray") String[] positionArray) {
        try {
             deptService.addDept(dept,positionArray);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    @DeleteMapping("dept/{deptId}")
    @ResponseBody
    public OutJSON delDept(@PathVariable("deptId")int deptId) {
        try {
            deptService.delDept(deptId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        }catch (Exception e){

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    @PutMapping("dept/{deptId}")
    @ResponseBody
    public OutJSON upDept(Dept dept,@RequestParam("positionArray") String[] positionArray) {
        try {
            deptService.upDept(dept,positionArray);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
