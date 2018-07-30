package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.domain.Prize;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inregral")
public class InregralController {
    @Autowired
    IntegralService integralService;
    /**
     * @Description: 综服中心-员工设置-修改积分
     * @Param: 用户ID userId，积分改动原因 integralChangestr,积分数值 integralTotal
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/28
     */
    @PostMapping(value = "upUserIntegral")
    public Msg upUserIntegral(Integral integral) {
        int statu=integralService.upUserIntegral(integral);
        if(statu==1)
            return Msg.success();
        else
            return  Msg.fail();
    }
    /**
     * @Description: 个人中心-积分明细 分页查询
     * @Param: 用户ID userId
     * @return:
     * @Author: 张金行
     * @Date: 2018/7/28
     */
    @RequestMapping(value = "findIntegralDetail",method = RequestMethod.POST)
    public Msg findIntegralDetail(@RequestParam(value="pn",defaultValue="1") Integer pn,Integer userid){
        PageHelper.startPage(pn,15);
        List<Integral> list = integralService.findIntegralDetail(userid);
        PageInfo pageInfo = new PageInfo(list,3);
        return Msg.success().add("pageInfo",pageInfo);
    };
}
