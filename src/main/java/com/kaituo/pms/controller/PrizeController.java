package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.domain.Prize;
import com.kaituo.pms.service.PrizeService;
import com.kaituo.pms.utils.MapUtil;
import com.kaituo.pms.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("prize")
public class PrizeController {
    @Autowired
    PrizeService prizeService;
    /**
     * 商品列表_分页查询
     * 张金行
     *
     */
     @RequestMapping(value = "findPrizeByPage",method = RequestMethod.POST)
   public Msg findPrizeByPage(@RequestParam(value="pn",defaultValue="1") Integer pn){
         PageHelper.startPage(pn,5);
         List<Prize> prizes = prizeService.findPrizeByPage();
         PageInfo pageInfo = new PageInfo(prizes,5);
         return Msg.success().add("pageInfo",pageInfo);
     };
   /**
     * 商品列表_搜索按钮
     * 张金行
     */
   @RequestMapping(value = "searchPrizeByPage",method = RequestMethod.POST)
    public Msg  searchPrizeByPage(@RequestParam(value="pn",defaultValue="1") Integer pn,String msg){
       PageHelper.startPage(pn,5);
       List<Prize>  prizes = prizeService.searchPrizeByPage(msg);
       if(prizes==null){return Msg.fail();}
       else {
           PageInfo pageInfo = new PageInfo(prizes, 5);
           return Msg.success().add("pageInfo", pageInfo);
       }
    };
    /**
     * 商品发布_添加商品_确认按钮
     * 张金行
     */
    @RequestMapping(value = "addPrize",method = RequestMethod.POST)
    public Msg addPrize(Prize prize){
        prizeService.addPrize(prize);
        return Msg.success();
    };
    /**
     * 商品发布_操作_修改_确认按钮
     * 张金行
     */
    @RequestMapping(value = "updatePrizeBuId",method = RequestMethod.POST)
    public Msg  updatePrizeBuId(Prize prize){
prizeService.updatePrizeBuId(prize);
        return Msg.success();
    }
    /**
     * 商品发布_操作_删除按钮
     * 张金行
     */
    @RequestMapping(value = "deletePrizeById",method = RequestMethod.POST)
    public Msg deletePrizeById(Integer prizeId){
        prizeService.deletePrizeById(prizeId);
        return Msg.success();
    }
    /**
     * 商品发布_操作_上架按钮
     * 张金行
     */
    public  Msg releasePrizeById(Prize prize){
        prize.setPrizeStatus(true);
        prizeService.releasePrizeById(prize);
        return Msg.success();
    }

    /** 
    * @Description: 兑换按钮
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/30 
    */ 
    @PostMapping("exchangePrize")
    public Map<String , Object> exchangePrize(String prizeID , String userID , String number){
        Integer intPrizeID = Integer.parseInt(prizeID);
        Integer intUserID = Integer.parseInt(userID);
        Integer intNnumber = Integer.parseInt(number);
        return prizeService.exchangePrize(intPrizeID,intUserID,intNnumber);
    }

    /** 按条件搜索商品
     * @Param:
     * @return:
     * @Author: su
     * @Date: 2018/7/30
     */
    @PostMapping("searchByPrize")
    public Map<String , Object> searchByPrize(String pageNumber , String pageSize , String search){
        Integer intPageNumber = Integer.parseInt(pageNumber);
        Integer intPageSize = Integer.parseInt(pageSize);
        return prizeService.searchByPrize(intPageNumber, intPageSize , search);
    }
}
