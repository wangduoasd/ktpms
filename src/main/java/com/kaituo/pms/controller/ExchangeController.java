package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.service.ExchangeService;
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
 * @Title: ExchangeController
 * @ProjectName pms
 * @Description:
 * @date 2018/8/8 000810:56
 */
@Controller
@Slf4j
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;
    /**
     　  * @Description: 兑换中心_兑换记录_分页查询
     　　* @param [pageNumber, pageSize] userId
     　　* @return com.kaituo.pms.util.Msg
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/8 0008 11:23
     　　*/
    @ResponseBody
    @GetMapping (value = "exchangeRecords/{userId}/pn={pn}")
    public OutJSON findExchangeList(@PathVariable(value = "pn") int pageNumber, @RequestParam(value = "pageNumber", defaultValue = "5") int pageSize, @PathVariable("userId") int userId) {
       try {
           PageHelper.startPage(pageNumber, pageSize);
           //根据userId查询视图中该用户所有兑换列表
           List<Exchange> list = exchangeService.findExchangeRecord(userId);
           PageInfo pageInfo = new PageInfo(list, 5);
           return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,pageInfo);
       }catch (Exception e){
           log.error(""+e.getMessage());
           return  OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
       }
    }
    /**
     　  * @Description:
     　　* @param exchangeId
     　　* @return com.kaituo.pms.util.Msg
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/8 0008 14:43
     　　*/
    @ResponseBody
    @PutMapping (value = "exchangeRecords/{exchangeId}/")
    public OutJSON updateExchange(@PathVariable("exchangeId") int exchangeId) {
        try {
            exchangeService.updateExchange(exchangeId);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        }catch (Exception e){
            log.error(""+e.getMessage());
            return  OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description:
     　　* @param [pageSize] pn,keyWord
     　　* @return com.kaituo.pms.util.Msg
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/8 0008 16:21
     　　*/
    @ResponseBody
    @GetMapping (value = "exchangeRecords/s/{keyWord}/pn={pn}")
    public OutJSON findExchange(@PathVariable(value = "pn") int pageNumber,@RequestParam(value = "pageNumber", defaultValue = "5") int pageSize,@PathVariable("keyWord") String keyWord) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            System.out.println(keyWord);
            List<Exchange> list = exchangeService.selectBykeyWord(keyWord);
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,pageInfo);
            } catch (Exception e) {
            log.error("" + e.getMessage());
            return  OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
}
