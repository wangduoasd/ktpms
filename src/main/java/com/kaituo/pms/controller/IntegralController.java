package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Integral;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class IntegralController {
    @Autowired
    IntegralService integralService;
    /**
     * 积分明细
     * @Description: 积分明细
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date:2018/8/18
     */
    @GetMapping(value="integral/{userId}/{pageNumber}")
    public OutJSON findIntegral(@PathVariable("userId") int id, @PathVariable("pageNumber")  int pn, @RequestParam(value = "pageSize" , defaultValue = "8") int pageSize){
        try {
            Map<String , Object> map = new HashMap<>();
            PageHelper.startPage(pn , pageSize);
            List<Map<String, Object>> integrals = integralService.listIntegeral(id);
          PageInfo pageInfo = new PageInfo(integrals, 5);
            if(integrals!=null&&integrals.size()>0){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,pageInfo);

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }
}
