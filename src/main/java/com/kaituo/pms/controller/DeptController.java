package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.utils.MapUtil;
import com.kaituo.pms.utils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 *@Description:
 *@Param:
 *@return:
 *@Author: 张金行
 *@Date: 2018/7/26
*/
@RestController
@RequestMapping(value = "dept")
@Slf4j
public class DeptController {
    @Autowired
    DeptService deptService;
    /**
     *@Description: 测试的
     *@Param:
     *@return:
     *@Author: 张金行
     *@Date: 2018/7/26
    */
    /*REST URI风格的请求
     *  /emp/{id} GET 查询请求
     *  /emp    POST 保存请求
     *   /emp/{id} PUT 修改
     *   /emp/{id} DELETE 删除*/
    /**
     * @Description: 风控中心-部门设置-分页查询
     * @Param:
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/26
     */
    @PostMapping("findAll")
    public Msg findAll(@RequestParam(value="pn",defaultValue="1") Integer pn){
        PageHelper.startPage(pn,5);
        List<Dept> depts = deptService.findAll();
        PageInfo pageInfo = new PageInfo(depts,5);
        return Msg.success().add("pageInfo",pageInfo);

    }

    @RequestMapping(value = "findAllDeptName",method = RequestMethod.POST)
    public Map<String , Object> findAllDeptName(){
        List<Dept> list = deptService.findAll();
        if (null==list || list.size()<=0){
            return MapUtil.setMap2("0","数据库空空如也",list);
        }
        return MapUtil.setMap2("1","成功",list);
    }
    /**
     * @Description: 风控中心-部门设置-新建部门-完成按钮
     * @Param:
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/26
     */
    @RequestMapping(value = "addDept",method = RequestMethod.POST)
    public Msg addDept(Dept dept){
        deptService.addDept(dept);
        return Msg.success();
    }

    @RequestMapping(value = "updateDeptById",method = RequestMethod.POST)
    public Msg updateDeptById(Dept dept){
        deptService.updateDeptById(dept);
        return Msg.success();
    }
    @RequestMapping(value = "deleteDeptById",method = RequestMethod.POST)
    public Msg deleteDeptById(Integer deptId){
        deptService.deleteDeptById(deptId);
        return Msg.success();
    }

}
