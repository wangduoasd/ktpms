package com.kaituo.pms.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.kaituo.pms.dao.PrizeMapper;
import com.kaituo.pms.domain.*;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.service.PrizeService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.MapUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PrizeServiceImpl implements PrizeService {
    @Autowired
    PrizeMapper prizeMapper;

    @Autowired
    UserService userService;

    @Autowired
    ExchangeService exchangeService;

    @Autowired
    IntegralService integralService;
    /**
     * 商品列表_分页查询
     * 张金行
     */
    @Override
    public List<Prize> findPrizeByPage() {
        PrizeExample prizeExample=new PrizeExample();
        PrizeExample.Criteria criteria = prizeExample.createCriteria();
        return prizeMapper.selectByExample(prizeExample);
    }
    /**
     * 商品列表_搜索按钮  模糊查询 返回商品list
     * 张金行
     */
    @Override
    public List<Prize>  searchPrizeByPage(String keyword) {
        return prizeMapper.selectBykeyWord(keyword);
    }
    /**
     * 商品发布_添加商品_确认按钮
     * 张金行
     */
    @Override
    public void addPrize(Prize prize) {
        prizeMapper.insertSelective(prize);
    }
    /**
     * 商品发布_操作_修改_确认按钮
     * 张金行
     */
    @Override
    public void updatePrizeBuId(Prize prize) {

        prizeMapper.updateByPrimaryKey(prize);
    }
    /**
     * 商品发布_操作_删除按钮
     * 张金行
     */
    @Override
    public void deletePrizeById(Integer prizeId) {
        prizeMapper.deleteByPrimaryKey(prizeId);
    }
    /**
     * 商品发布_操作_上架按钮
     * 张金行
     */
    @Override
    public void releasePrizeById(Prize prize) {
        prizeMapper.updateByPrimaryKey(prize);

    }

    /** 
    * @Description: 通过id查找商品
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    @Override
    public Prize findPrizeByID(Integer prizeId) {
        return prizeMapper.selectByPrimaryKey(prizeId);
    }

    @Override
    public Map<String, Object> exchangePrize(int prizeID , int userID , int number) {
        Map<String , Object> map;
        Prize prize = prizeMapper.selectByPrimaryKey(prizeID);
        User user = userService.findUserByID(userID);
        //为了避免空指针
        if(null==user||null==prize){
            return MapUtil.setMap2("0" , "获取员工或商品出错" , null);
        }
        //购买数量和库存作比较
        if (number>prize.getPrizeAmount()){
            return MapUtil.setMap2("1" , "库存不足" , null);
        }
        //购买数量和限购作比较
        if(number>prize.getPrizeQuota()){
            return MapUtil.setMap2("2" , "超过限购数量" , null);
        }
        //数量和单价算出的合计价格
        int totalPrice = number*prize.getPrizePrice();
        //合计价格和余额作比较
        if (totalPrice>user.getUserIntegral()){
            return MapUtil.setMap2("3" , "余额不足" , null);
        }
        //余额和购买条件作对比
        if (user.getUserIntegral()<prize.getPrizeCondition()){
            return MapUtil.setMap2("4" , "未达到购买条件" , null);
        }
        //和以往的兑换记录作对比
        List<Exchange> exchangeList = exchangeService.findExchangeByUserIDAndPrizeID(userID,prizeID);
        if (null==exchangeList||exchangeList.size()==0){
            //无数据或长度等于0说明没有兑换过这个商品
            return exchangePrizeDate(userID , prizeID , number , totalPrice , user , prize);

        }else {
            int purchased = number;
            for (Exchange exchange : exchangeList){
                purchased += exchange.getExchangeCount();
            }
            //原先购买价本次购买和限购作对比
            if (purchased>prize.getPrizeQuota()){
                return MapUtil.setMap2("2" , "超过限购数量" , null);
            }
            return exchangePrizeDate(userID , prizeID , number , totalPrice , user , prize);

        }
    }

    @Override
    public Map<String , Object> exchangePrizeDate(int userID , int prizeID , int number , int totalPrice , User user ,
                                                  Prize prize){
        //新增兑换记录
        Exchange exchange = new Exchange();
        exchange.setUserId(userID);
        exchange.setPrizeId(prizeID);
        exchange.setExchangeCount(number);
        exchange.setExchangePrice(totalPrice);
        exchange.setExchangeTime(new Date());
        exchange.setExchangeStatus(1);
        try {
            exchangeService.insert(exchange);
        }catch (Exception e){
            e.printStackTrace();
            return MapUtil.setMap2("5" , "添加兑换记录失败" , null);
        }
        //扣除积分
        user.setUserIntegral(user.getUserIntegral()-totalPrice);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(userID);
        try {
            userService.upDate(user , userExample);
        }catch (Exception e){
            e.printStackTrace();
            return MapUtil.setMap2("6" , "扣除积分失败" , null);
        }
        //添加新的积分明细
        Integral integral = new Integral();
        integral.setIntegralTotal(user.getUserIntegral()-totalPrice);
        integral.setUserId(userID);
        integral.setIntegralChangestr("兑换了"+prize.getPrizeName());
        integral.setIntegralTime(new Date());
        integral.setIntegralChangeint(-user.getUserIntegral()-totalPrice);
        integral.setIntegralStatus(1);
        integral.setIntegralOperator("");
        try {
            integralService.insert(integral);
        }catch (Exception e){
            e.printStackTrace();
            return MapUtil.setMap2("7" , "添加积分明细失败" , null);
        }
        return MapUtil.setMap2("8" , "兑换成功" , null);
    }

    @Override
    public Map<String, Object> searchByPrize(int pageNumber , int pageSize , String search) {
        PageHelper.startPage(pageNumber , pageSize);
        List<Prize> prizeList = prizeMapper.selectBykeyWord(search);
        if (null==prizeList||prizeList.size()<=0){
            return MapUtil.setMap2("1" , "未查找到相应信息" , null);
        }
        return MapUtil.setMap2("0" , "成功" , prizeList);
    }

}
