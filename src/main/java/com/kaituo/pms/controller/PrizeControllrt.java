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
import com.kaituo.pms.utils.Constant;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.Util;
import com.kaituo.pms.utils.Constant;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import com.kaituo.pms.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PrizeControllrt {
    @Autowired
    PrizeService prizeService;
    @Autowired
    UserService userService;

    /**
     * 根据商品名查询已经上架商品
     * 点击按钮进行搜索查询
     * @Description:
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/14
     */
    @GetMapping( value = "prize/{prizeName}/{pn}/{pageSize}")
    public OutJSON findByName(@PathVariable("prizeName") String prizeName,@RequestParam(value = "pageNumber",defaultValue = "1") int pageNumber,@RequestParam(value = "pageSize", defaultValue = "4") int pageSize){
        try {
            prizeName = "%" + prizeName +"%";
            PageHelper.startPage(pageNumber, pageSize);
            List<Prize> prizeList = prizeService.selectByName(prizeName);
            PageInfo objectPageInfo = new PageInfo<>(prizeList,4);
            if(prizeList!=null&&prizeList.size()>0){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,objectPageInfo);
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
    *分页显示已经上架商品
   * @Description:
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:  2018/8/17
   */
   @GetMapping(value="prizes/{userId}/pageNumber/pageSize")
   public OutJSON findAllPrizePrize(@PathVariable("userId") int userId,@RequestParam(value = "pageNumber",defaultValue = "1") int pageNumber,@RequestParam(value = "pageSize", defaultValue = "4") int pageSize){
       try {
           PageHelper.startPage(pageNumber, pageSize);
           List<Prize> prizess = prizeService.findAllPrizePrize(userId);
           PageInfo objectPageInfo = new PageInfo<>(prizess,4);
           System.out.print(prizess+"666666");
           return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,objectPageInfo);
       } catch (Exception e) {
           log.error(e.getMessage());
           return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
       }
   }

   /* @GetMapping(value="prizes/{userId}")
    public OutJSON findAllPrizePrize1(@PathVariable("userId") int userId){
        try {

            List<Prize> prizess = prizeService.findAllPrizePrize(userId);

            System.out.print(prizess+"666666");
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,prizess);
        } catch (Exception e) {
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }*/
   /**
    * 商品兑换
   * @Description:
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:  2018/8/20
   */
   @PutMapping(value="prize/{userId}/{number}/{prizeId}")
   public OutJSON exchangePrize(@PathVariable("userId")  int userId,@PathVariable("number") int number,@PathVariable("prizeId") int prizeId){
       Prize prize = prizeService.selectByPrimaryKey(prizeId);
       //prize.setPrizeId(prizeId);
       System.out.print(prize);
       int count = (prize.getPrizeAmount()-number);

       prize.setPrizeAmount(count);
       prizeService.updateByPrimaryKey(userId,number,prizeId);
       System.out.print(count+"qqqqqqqqqqq");
       User user = userService.findPersonalDetail(userId);
       int i = prizeService.exhangePrize(userId, number, prizeId);
       int totalPrice = number * prize.getPrizePrice();
       if (null == user || null == prize) {
           //用户名或商品为空
           return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
       } else if (number >prize.getPrizeQuota()) {
           //购买失败，超过上限
           return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_CAP);
       } else if (totalPrice > user.getUserIntegral()) {
           //购买失败，积分不足
           return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_INTEGRAL_LACKOF);
       } else {
           //购买成功
         return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_SUCCESS,i);
   }
   }
   /**
    * 综服中心的商品列表
   * @Description:分页显示
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:2018/8/16
   */
   @GetMapping("prize/{pageNumber}/{pageSize}")
   public OutJSON listAllPrize(@PathVariable("pageNumber") int pageNumber,@PathVariable("pageSize") int pageSize ){
       try {
           PageHelper.startPage(pageNumber,pageSize);
           List<Prize> prizes = prizeService.listAllPrize();
           PageInfo<Object> objectPageInfo = new PageInfo(prizes,5);
           if(prizes!=null&&prizes.size()>0){
               return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,objectPageInfo);
           }

       } catch (Exception e) {
           e.printStackTrace();
           log.error(e.getMessage());
       }
       return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
   }
   /**
    * 综服中心删除商品
   * @Description:
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:2018/8/21
   */
   @DeleteMapping("/prize/{prizeId}")
    public OutJSON deleteById(@PathVariable("prizeId") int prizeId) {
       int i = prizeService.deleteById(prizeId);
       try {
           if (i > 0) {
               return OutJSON.getInstance(CodeAndMessageEnum.DELELETE_SUCCESS, i);
           }

       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR);
   }
   /**
    * 综服中心搜索商品
   * @Description:
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date: 2018/8/21
   */
   @GetMapping("/prize/{prizeName}")
   public OutJSON selectServiceByName(@PathVariable("prizeName") String prizeName){
       List<Prize> prizeList = prizeService.selectServiceByName(prizeName);
       if(null!=prizeList&&prizeList.size()>0){
           return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, prizeList);
       }
       return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
   }

    /**
     * 综服中心搜索商品
     * @Description:
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/21
     */
    @PostMapping("prize/adjunction")
    public OutJSON addPrize(@RequestParam("file") MultipartFile file , Prize prize) {
        try {
            Map<String, Object> map = Util.imgUpload(file, Util.getImgRelativePath());
            int key = (int) map.get("code");
            switch (key) {
                case Constant.IMG_UPLOSD_ERROR:
                    return OutJSON.getInstance(CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_UPLOAD_FAILED);
                case Constant.IMG_UPLOSD_SUCCESS:
                    String url = (String) map.get("url");
                    prize.setPrizeImage(url);
                    if (0 >= prizeService.addPrize(prize)) {
                        return OutJSON.getInstance(CodeAndMessageEnum.ADJUNCTION_ERROR);
                    }
                    return OutJSON.getInstance(CodeAndMessageEnum.ADJUNCTION_SUCCESS);
                case Constant.IMG_UPLOSD_EMPTY:
                    return OutJSON.getInstance(CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_IS_EMPTY);
                default:
                    return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
            }
        } catch (Exception e) {
            log.error(e.getMessage() , e);
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.ADJUNCTION_ERROR);
        }

    }

    /**
     * 修改商品
     * @Param:
     * @param file
     * @param prize
     * @return: com.kaituo.pms.utils.OutJSON
     * @Author: 苏泽华
     * @Date: 2018/8/24
     */
    @PutMapping("prize/modification")
    public OutJSON modifyPrize(MultipartFile file, Prize prize) {
        Map<String, Object> map = Util.imgUpload(file, Util.getImgRelativePath());
        // 上传的状态码
        int key = (int) map.get("code");
        try {
            switch (key) {

                // 如果是零
                case Constant.IMG_UPLOSD_ERROR:
                    // 返回图片上传失败
                    return OutJSON.getInstance(CodeAndMessageEnum.PUBLISHING_TASK_IMAGE_HAS_BEEN_SUCCESSFULLY_UPLOADED);
                // 如果是1则为上传成功
                case Constant.IMG_UPLOSD_SUCCESS:
                    // 获取相对路径
                    String url = (String) map.get("url");
                    prize.setPrizeImage(url);
                    if (0 >= prizeService.modifyPrize(prize)){
                        return OutJSON.getInstance(CodeAndMessageEnum.MODIFICATION_EMPTY);
                    }
                    return OutJSON.getInstance(CodeAndMessageEnum.MODIFICATION_SUCCESS);
                // 如果是2则为图片为空
                case Constant.IMG_UPLOSD_EMPTY:
                    prize.setPrizeImage(null);
                    prizeService.modifyPrize(prize);
                    return OutJSON.getInstance(CodeAndMessageEnum.MODIFICATION_SUCCESS);
                default:
                    return OutJSON.getInstance(CodeAndMessageEnum.MODIFICATION_ERROR);
            }
        } catch (Exception e) {
            log.error("modifyPrize ==>" + e.getMessage() , e);
            e.printStackTrace();
            return OutJSON.getInstance(CodeAndMessageEnum.MODIFICATION_ERROR);
        }
    }

    /**
    * @Description: 商品校检
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date:  2018/8/22
    */
    @GetMapping("/prizeEmpty/{prizeName}")
    public OutJSON  prizeIsEmpty(@PathVariable("prizeName")  String prizeName ){
        try {
            boolean b = prizeService.prizeIsEmpty(prizeName);
            if(b){
                return OutJSON.getInstance(CodeAndMessageEnum.PRIZENAME_CANADD,b);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return OutJSON.getInstance(CodeAndMessageEnum.PRIZENAME_EXIST);
    }
    /**
    * @Description: 商品上架
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date: 2018/8/22
    */
    @PostMapping("/goodsShelves/{prizeId}")
    public OutJSON goodsShelves(@PathVariable("prizeId") int prizeId){
        try {
            int goodsshelves = prizeService.goodsshelves(prizeId);
            if(goodsshelves>0){
                return OutJSON.getInstance(CodeAndMessageEnum.GOODS_SHELVES,goodsshelves);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        return OutJSON.getInstance(CodeAndMessageEnum.GOODS_ERROR);
    }
    /**
    * @Description: 商品下架
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date: 2018/8/22
    */
    @PostMapping("/goodsSoldout/{prizeId}")
    public OutJSON goodsSoldout(@PathVariable("prizeId") int prizeId){
        try {
            int goodsshelves = prizeService.goodsshelves(prizeId);
            if(goodsshelves>0){
                return OutJSON.getInstance(CodeAndMessageEnum.GOODS_SOLDOUT_SUCCESS,goodsshelves);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return OutJSON.getInstance(CodeAndMessageEnum.GOODS_SOLDOUT_ERROR);
    }

}