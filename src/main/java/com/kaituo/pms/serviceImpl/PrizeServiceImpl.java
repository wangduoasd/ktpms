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
@Transactional
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
    public List<Prize> selectByName(String prizeName) {
        List<Prize> pNamelist = prizeMapper.selectByName(prizeName);
        return pNamelist;

    }


    @Override
    public List<Prize> findAllPrizePrize(int userId) {
        List<Prize> prizes = prizeMapper.findAllPrize(userId);
        return prizes;
    }
    @Override
    public int exhangePrize(int userId, int number, int prizeId) {

        int i = exchangeService.addexchangeRecord(prizeId,userId,number);


        return  i;
    }

    @Override
    public Prize selectByPrimaryKey(int prizeId) {
        return prizeMapper.selectByPrimaryKey(prizeId);
    }

    @Override
    public int updateByPrimaryKey(int userId, int number, int prizeId) {
        Prize prize = prizeMapper.selectByPrimaryKey(prizeId);
        //prize.setPrizeId(prizeId);
        System.out.print(prize);
        int count = (prize.getPrizeAmount()-number);

        prize.setPrizeAmount(count);
        return prizeMapper.updateByPrimaryKey(prize);
    }

    @Override
    public List<Prize> listAllPrize() {
        return prizeMapper.listAllPrize();
    }

    @Override
    public int deleteById(int prizeID) {
      return  prizeMapper.deleteByPrimaryKey(prizeID);
    }

    @Override
    public List<Prize> selectServiceByName(String prizeName) {
        PrizeExample prizeExample = new PrizeExample();
        PrizeExample.Criteria criteria = prizeExample.createCriteria();
        criteria.andPrizeNameLike(prizeName);
        return prizeMapper.selectByExample(prizeExample);
    }
//添加商品
    @Override
    public int addPrize(Prize prize) {
        prize.setPrizeStatus(1);
        return prizeMapper.insertSelective(prize);
    }
//商品名唯一校验
    @Override
    public boolean prizeIsEmpty(String prizeName) {
        PrizeExample prizeExample = new PrizeExample();
        PrizeExample.Criteria criteria = prizeExample.createCriteria();
        criteria.andPrizeNameEqualTo(prizeName);
        List<Prize> prize=prizeMapper.selectByExample(prizeExample);
        if (null==prize||prize.size()==0 ){
            return true;
        }
        return false;
    }
/**
 * 商品上架
* @Description:
* @Param:
* @return:
* @Author: 侯鹏
* @Date: 8/24
*/
    @Override
    public int goodsshelves(int prizeId) {
        Prize prize1 = prizeMapper.selectByPrimaryKey(prizeId);

        prize1.setPrizeStatus(2);
        int i = prizeMapper.updateByPrimaryKeySelective(prize1);
        return i;
    }
    /**
     * 商品下架
     * @Description:
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date: 8/23
     */
    @Override
    public int goodsoldout(int prizeId) {
        Prize prize1 = prizeMapper.selectByPrimaryKey(prizeId);

        prize1.setPrizeStatus(3);
        int i = prizeMapper.updateByPrimaryKeySelective(prize1);
        return i;
    }

    /**
     * 商品修改
     * @Param:
     * @param prize
     * @return: com.kaituo.pms.bean.Prize
     * @Author: 苏泽华
     * @Date: 2018/8/22
     */
    @Override
    public int modifyPrize(Prize prize) {
        return prizeMapper.updateByPrimaryKeySelective(prize);
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
