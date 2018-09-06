package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.JwtToken;
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
 * @Description: 兑换商品的状态,默认为1.为1是用户已经兑换,2是风控委已经发送奖品.3是用户已经确认领取完奖品了
 * @date 2018/8/8 000810:56
 */
@RestController
@CrossOrigin
@Slf4j
public class ExchangeController {
    @Autowired
    ExchangeService exchangeService;

    /**
     * 　  * @Description: 兑换中心_兑换记录_分页查询
     * 　　* @param [pageNumber, pageSize] userId
     * 　　* @return com.kaituo.pms.util.Msg
     * 　　* @throws
     * 　　* @author 张金行
     * 　　* @date 2018/8/8 0008 11:23
     *
     */
    @ResponseBody
    @GetMapping(value = "exchangeRecords/{token:.+}/{pageNumber}")
    public OutJSON getExchangeRecords(@PathVariable("token") String token,
                                       @PathVariable(value = "pageNumber") int pageNumber,
                                       @RequestParam(value = "pageSize", defaultValue = "4") int pageSize
                                  ) {
        try {
            int userId = JwtToken.getUserId(token);
            PageHelper.startPage(pageNumber, pageSize);
            //根据userId查询视图中该用户所有状态 状态1（显示为：未发送）  状态2（显示为：确定领取），状态3（显示为：已经领取）  的兑换列表
            List<Exchange> list = exchangeService.findExchangeRecord(userId);;
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 　  * @Description: 兑换中心_兑换记录_确定领取
     * 　　* @param exchangeId
     * 　　* @return com.kaituo.pms.util.Msg
     * 　　* @throws
     * 　　* @author 张金行
     * 　　* @date 2018/8/8 0008 14:43
     *
     */
    @ResponseBody
    @PutMapping(value = "exchangeRecords/{exchangeId}")
    public OutJSON updateExchange(@PathVariable("exchangeId") int exchangeId) {
        try {
            //根据userId将视图中该用户状态从  状态2（显示为：确定领取）改变到 状态3（显示为：已经领取）
            int i = exchangeService.updateExchange(exchangeId, 2,3);
            if(i==1)
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.EXCHANGE_STATUS_ERROR);
            }
            return  OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 　  * @Description:  兑换中心_兑换记录_兑换记录搜索（根据商品名）
     * 　　* @param [pageSize] pn,keyWord,userId
     * 　　* @return com.kaituo.pms.util.Msg
     * 　　* @throws
     * 　　* @author 张金行
     * 　　* @date 2018/8/8 0008 16:21
     *
     */
    @ResponseBody
    @GetMapping(value = "exchangeRecords/{token:.+}/s/{keyWord}/{pn}")
    public OutJSON findExchange( @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,  @PathVariable("token") String token,@PathVariable("keyWord") String keyWord,@PathVariable(value = "pn") int pageNumber) {
        try {
            int userId = JwtToken.getUserId(token);
            PageHelper.startPage(pageNumber, pageSize);
            //根据商品名keyWord搜索视图中该用户所有状态 状态1（显示为：未发送）  状态2（显示为：确定领取），状态3（显示为：已经领取）  的兑换列表
            List<Exchange> list = exchangeService.selectBykeyWord(keyWord, userId);
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    //综服中心
    /**
     　  * @Description:  综服中心_商品兑换列表_分页查询
     　　* @param [pageSize]  pn
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/10 0010 10:59
     　　*/
    @ResponseBody
    @GetMapping(value = "authority/three/exchangeLists/{pn}")
    public OutJSON getExchangeLists(@PathVariable(value = "pn") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "4") int pageSize) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            //查询视图中所有用户   状态1（显示为：确定兑换），状态2（显示为：已兑换）  的兑换列表
            List<Exchange> list = exchangeService.getExchangeLists();
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error("" + e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description:   综服中心_商品兑换列表_确定兑换
     　　* @param exchangeId
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/10 0010 11:00
     　　*/
    @ResponseBody
    @PutMapping(value = "authority/three/exchangeLists/{exchangeId}")
    public OutJSON updateExchangeList(@PathVariable("exchangeId") int exchangeId) {
        try {
            int i = exchangeService.updateExchange(exchangeId, 1,2);
            if(i==1)
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.EXCHANGE_STATUS_ERROR);
            }
            return  OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description:  综服中心_商品兑换列表_搜索（根据用户名和商品名）
     　　* @param keyWord, pageNumber
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/10 0010 11:00
     　　*/
    @ResponseBody
    @GetMapping(value = "authority/three/exchangelists/s/{keyWord}/{pn}")
    public OutJSON findExchangelists( @RequestParam(value = "pageSize", defaultValue = "4") int pageSize, @PathVariable("keyWord") String keyWord,@PathVariable(value = "pn") int pageNumber) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            //根据商品名keyWord搜索视图中该用户所有状态 状态1（显示为：未发送）  状态2（显示为：确定领取），状态3（显示为：已经领取）  的兑换列表
            List<Exchange> list = exchangeService.selectBykeyWord(keyWord);
            if(list==null)
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

}
