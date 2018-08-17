package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.*;
import com.kaituo.pms.dao.ExchangeMapper;
import com.kaituo.pms.dao.PrizeMapper;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.service.PrizeService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据奖品名称搜索
* @Description:
* @Param:
* @return:
* @Author: 侯鹏
* @Date:  2018/8/14
*/
@Service
public class PrizeServiceImpl implements PrizeService {
    @Autowired
    PrizeMapper prizeMapper;
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    UserService userService;
    @Autowired
    ExchangeMapper exchangeMapper;
    @Override
    @Transactional
    public List<Prize> selectByName(String prizeName) {
        List<Prize> pNamelist = prizeMapper.selectByName(prizeName);
        return pNamelist;
    }


    @Override
    public List<Prize> findAllPrizePrize(int userId) {
        List<Prize> prizes = prizeMapper.findAllPrize(userId);
        return prizes;
    }
   /*  @Override
    public void  exchangePrize(int prizeID, int userID, int number, int eid) {
       int exSumNum = 0;
        Prize prize = prizeMapper.selectByPrimaryKey(prizeID);
        User user = userService.findPersonalDetail(userID);
        List<Exchange> exchangeRecord = exchangeService.findExchangeRecord(eid);
        ExchangeExample exchangeExample = new ExchangeExample();
        ExchangeExample.Criteria criteria = exchangeExample.createCriteria();
        criteria.andPrizeIdEqualTo(prizeID).andUserIdEqualTo(userID);
        Prize prizee = new Prize();
        prizee.setPrizeAmount(prizee.getPrizeAmount() - number);
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

}
