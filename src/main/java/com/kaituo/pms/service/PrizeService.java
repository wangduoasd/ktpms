package com.kaituo.pms.service;

import com.kaituo.pms.bean.Prize;
import com.kaituo.pms.utils.OutJSON;

import java.util.List;
/**
 *
* @Description:
* @Param:
* @return:
* @Author: 侯鹏
* @Date:  2018/8/14
*/
public interface PrizeService {
   List<Prize> selectByName(String prizeName);

   List<Prize> findAllPrizePrize(int  userId);

   Prize selectByPrimaryKey(int prizeId);

   int exhangePrize(int userId,int number,int prizeId);

   int  updateByPrimaryKey(int userId,int number,int prizeId);
   //综服中心商品显示
   List<Prize> listAllPrize();
   //综服中心删除商品
   int deleteById(int prizeID);
   //综服中心查询搜索
   List<Prize> selectServiceByName(String prizeName);
   //综服中心添加商品
   int addPrize(Prize prize);
   //商品唯一校验
   boolean prizeIsEmpty(String prizeName);
   //商品发布上架
   int goodsshelves(int prizeId);

   int goodsoldout(int prizeId);

    /**
     * 商品修改
     * @Param:
     * @param prize
     * @return: com.kaituo.pms.bean.Prize
     * @Author: 苏泽华
     * @Date: 2018/8/22
     */
    int modifyPrize(Prize prize);

   /**
    * 商品修改
    * @Param:
    * @param prize
    * @return: com.kaituo.pms.bean.Prize
    * @Author: 苏泽华
    * @Date: 2018/8/31
    */
   Prize getPrize(int prizeId);
}
