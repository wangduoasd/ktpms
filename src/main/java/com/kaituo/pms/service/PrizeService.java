package com.kaituo.pms.service;

import com.kaituo.pms.domain.Prize;
import com.kaituo.pms.domain.User;

import java.util.List;
import java.util.Map;

public interface PrizeService {
    /**
     * 商品列表_分页查询
     * 张金行
     */
    List<Prize> findPrizeByPage();
    /**
     * 商品列表_搜索按钮
     * 张金行
     */
    List<Prize>  searchPrizeByPage(String msg);
    /**
     * 商品发布_添加商品_确认按钮
     * 张金行
     */
      void addPrize(Prize prize);
    /**
     * 商品发布_操作_修改_确认按钮
     * 张金行
     */
    void updatePrizeBuId(Prize prize);
    /**
     * 商品发布_操作_删除按钮
     * 张金行
     */
    void deletePrizeById(Integer prizeId);
    /**
     * 商品发布_操作_上架按钮
     * 张金行
     */
    void releasePrizeById(Prize prize);
    /** 
    * @Description:
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */
    Prize findPrizeByID(Integer prizeId);

    /** 
    * @Description: 兑换商品
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/29 
    */ 
    Map<String , Object> exchangePrize(int prizeID , int userID , int number);

    /** 
    * @Description: 兑换商品。操作数据
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/29 
    */
    Map<String , Object> exchangePrizeDate(int userID , int prizeID , int number , int totalPrice , User user ,
                                           Prize prize);

    /** 
    * @Description: 搜索所有奖品按钮
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/29 
    */ 
    Map<String , Object> searchByPrize(int pageNumber , int pageSize , String search);
}
