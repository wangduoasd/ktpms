package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.bean.ExchangeExample;
import com.kaituo.pms.bean.Prize;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.dao.ExchangeMapper;
import com.kaituo.pms.dao.PrizeMapper;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.service.PrizeService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PrizeControllrt {
    @Autowired
    PrizeService prizeService;

    /**
     * 根据商品名查询已经上架商品
     * 点击按钮进行搜索查询
     * @Description:
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/14
     */
    @GetMapping( value = "prize/prizeName")
    public OutJSON findByName(String prizeName){
        try {
            List<Prize> prizeList = prizeService.selectByName(prizeName);
            if(prizeList!=null&&prizeList.size()>0){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,prizeList);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }


    /**
     * 积分兑换商品
    * @Description:
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date:  2018/8/15
    */
   /* @PostMapping(value="/exchangePrize")
    public OutJSON changePrize(@RequestParam("prizeID")int prizeID,@RequestParam("userID") int userID, @RequestParam("number")int number) {
        int exSumNum = 0;
        Prize prize = prizeMapper.selectByPrimaryKey(prizeID);
        User user = userService.findPersonalDetail(userID);
        List<Exchange> exchangeRecord = exchangeService.findExchangeRecord(userID);
        ExchangeExample exchangeExample = new ExchangeExample();
        ExchangeExample.Criteria criteria = exchangeExample.createCriteria();
        criteria.andPrizeIdEqualTo(prizeID).andUserIdEqualTo(userID);
        Prize prizee = new Prize();
        prizee.setPrizeAmount(6 - number);
        prizee.setPrizeId(prizeID);
        List<Exchange> exchanges = exchangeMapper.selectByExample(exchangeExample);
        int i = prizeMapper.updateByPrimaryKeySelective(prizee);


        int reaminNum = 0;
        //该用户对该商品已经兑换次数
        for (Exchange exc : exchanges) {
            exSumNum = exSumNum + exc.getExchangeCount();
            //该用户剩余限购数量
            reaminNum = exc.getExchangeCount() - exSumNum;
            System.out.println(exSumNum);
        }
        int totalPrice = number * prize.getPrizePrice();
        //  通过员工ID和商品ID从兑换记录表获取所有的兑换记录
        if (null == user || null == prize) {
            //用户名或商品为空
            return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
        } else if (number > reaminNum) {
            //购买失败，超过上限+
            return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_CAP);
        } else if (totalPrice > user.getUserIntegral()) {
            //购买失败，积分不足
            return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_INTEGRAL_LACKOF);
        } else {

            List<Map<String, Object>> prizeMap = new ArrayList<>();
            List<Prize> prizesList = prizeMapper.selectAllStatus();
            if (null != prizesList && prizesList.size() > 0) {
                for (Prize prizea : prizesList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("prizeName", prizea.getPrizeName());
                    map.put("PrizeImage", prizea.getPrizeImage());
                    map.put("PrizePrice", prizea.getPrizePrice());
                    map.put("PrizeAmount", prizee.getPrizeAmount());
                    map.put("prizeQuota", reaminNum);
                    map.put("prizeCondition", prize.getPrizeCondition());
                    prizeMap.add(map);
                }
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, prizeMap);

            }
        }
        return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }*/
   /**
    *
   * @Description:
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:  2018/8/17
   */
   @GetMapping(value="Prizess/userId={userId}")
   public OutJSON findAllPrizePrize(@PathVariable("userId") int userId,@RequestParam(value = "pageNumber",defaultValue = "1") int pageNumber,@RequestParam(value = "pageSize", defaultValue = "4") int pageSize){
       try {
           PageHelper.startPage(pageNumber, pageSize);
           List<Prize> prizess = prizeService.findAllPrizePrize(userId);
           PageInfo objectPageInfo = new PageInfo<>(prizess,4);
           System.out.print(prizess+"55555555555555555");
           return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,prizess);
       } catch (Exception e) {
           log.error(e.getMessage());
           return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
       }
   }

}
