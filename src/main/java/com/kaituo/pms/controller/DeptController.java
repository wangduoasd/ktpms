package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Dept;
import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            //根据userId查询视图中该用户所有状态 状态1（显示为：未发送）  状态2（显示为：确定领取），状态3（显示为：已经领取）  的兑换列表
            List<Dept> list = deptService.findAllDept();;
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
