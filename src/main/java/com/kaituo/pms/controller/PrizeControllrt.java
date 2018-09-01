package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Exchange;
import com.kaituo.pms.bean.Prize;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.dao.IntegralMapper;
import com.kaituo.pms.service.ExchangeService;
import com.kaituo.pms.service.IntegralService;
import com.kaituo.pms.service.PrizeService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.Constant;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class PrizeControllrt {
    @Autowired
    PrizeService prizeService;
    @Autowired
    UserService userService;
    @Autowired
    IntegralMapper integralMapper;
    @Autowired
    IntegralService  integralService;
    @Autowired
    ExchangeService exchangeService;

    /**
     * @Description:兑换中心_商品列表 点击按钮进行搜索查询 根据商品名查询已经上架商品
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/14
     */
    @GetMapping( value ="prizes/s/{prizeName}/{pageNumber}")
    public OutJSON findByName(@PathVariable("prizeName") String prizeName,
                              @PathVariable(value = "pageNumber") int pageNumber,
                              @RequestParam (value = "pageSize",defaultValue = "4") int pageSize){
        try {
            prizeName = "%" + prizeName +"%";
            PageHelper.startPage(pageNumber, pageSize);
            List<Prize> prizeList = prizeService.selectByName(prizeName);
            PageInfo pageInfo = new PageInfo<>(prizeList,5);
            if(prizeList!=null&&prizeList.size()>0){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,pageInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }

   /**
    *
   * @Description: 兑换中心_商品列表 分页显示已经上架商品
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:  2018/8/17
   */
   @GetMapping(value="prizes/{userId}/{pageNumber}")
   public OutJSON findAllPrizePrize(@PathVariable("userId") int userId,
                                    @PathVariable(value = "pageNumber") int pageNumber,
                                    @RequestParam (value = "pageSize",defaultValue = "6") int pageSize){
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
   /**
    *
   * @Description: 兑换中心_商品列表_商品兑换
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:  2018/8/20
   */
   @PutMapping(value="prize/{userId}/{number}/{prizeId}")
   public OutJSON exchangePrize(@PathVariable("userId")  int userId, @PathVariable("number") int number,
                                @PathVariable("prizeId") int prizeId){
       int newCount = 0;
      try {
       Prize prize = prizeService.selectByPrimaryKey(prizeId);
          User user = userService.findPersonalDetail(userId);
          List<Exchange> exchanges = exchangeService.selectByUserIdPrizeId(prizeId, userId);
          if (null != exchanges && exchanges.size() > 0) {
              for(Exchange exchange:  exchanges){
                  newCount= exchange.getExchangeCount() + number;
              }
          }


          int totalPrice = number * prize.getPrizePrice();
       if (null == user || null == prize) {
           //用户名或商品为空
           return OutJSON.getInstance(CodeAndMessageEnum.GET_STATES_TASK_BY_PAGE_NULL);
       } else if (number >prize.getPrizeQuota()||prize.getPrizeAmount()<0||prize.getPrizeQuota()<=0||newCount>prize.getPrizeQuota()) {
           //购买失败，超过上限
           return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_CAP);
       } else if (totalPrice > user.getUserIntegral()) {
           //购买失败，积分不足
           return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_INTEGRAL_LACKOF);
       } else {
           //购买成功
           int count = (prize.getPrizeAmount()-number);
           prize.setPrizeAmount(count);
           user.setUserIntegral(user.getUserIntegral()-totalPrice);
           prizeService.updateByPrimaryKey(userId,number,prizeId);
           int i = prizeService.exhangePrize(userId, number, prizeId);
           String changestr = "兑换"+prize.getPrizeName()+"成功";
           int changint=-number*prize.getPrizePrice();
          int endnum=user.getUserIntegral()-changint;
           int k = integralService.addPrizeIntegral(changint, userId, changestr, endnum);
           userService.upUserIntegral(user);
         return OutJSON.getInstance(CodeAndMessageEnum.FIND_PRIZE_SUCCESS,i);
   }}catch (Exception e){
          e.printStackTrace();
          log.error(e.getMessage());
          return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
      }

   }
   /**
    *
   * @Description:综服中心_商品发布 分页显示
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:2018/8/16
   */
   @GetMapping("authority/two/prizes/{pageNumber}")
   public OutJSON listAllPrize(@PathVariable("pageNumber") int pageNumber, @RequestParam (value = "pageSize",defaultValue = "6") int pageSize ){
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
   * @Description:综服中心_商品发布  删除商品
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date:2018/8/21
   */
   @DeleteMapping("authority/two/prize/{prizeId}")
   public OutJSON deleteById(@PathVariable("prizeId") int prizeId) {
       Prize prize = prizeService.selectByPrimaryKey(prizeId);
       int deleteFalg = 0;
       try {
           if (null != prize.getPrizeImage()) {
               deleteFalg = Util.imgDelect(prize.getPrizeImage());
           }else {
               return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR);
           }
           switch (deleteFalg){
               case Constant.IMG_DELECT_ERROR:
                   return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR);
               case Constant.IMG_DELECT_SUCCESS:
                   int i = prizeService.deleteById(prizeId);
                   if (i > 0) {
                       return OutJSON.getInstance(CodeAndMessageEnum.DELELETE_SUCCESS, i);
                   }else {
                       return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR);
                   }
               case Constant.IMG_UPLOSD_EMPTY:
                   return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR);
               default:
                   return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR);
           }
       } catch (Exception e) {
           log.error( e.getMessage());
           e.printStackTrace();
           return OutJSON.getInstance(CodeAndMessageEnum.DELETE_ERROR);
       }
   }
   /**
    *
   * @Description:综服中心_商品发布 搜索商品
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date: 2018/8/21
   */
   @GetMapping("authority/two/prize/s/{prizeName}/{pageNumber}")
   public OutJSON selectServiceByName(@PathVariable("prizeName") String prizeName,@RequestParam (value = "pageSize",defaultValue = "6") int pageSize,@PathVariable("pageNumber") int pageNumber){
     try {
         prizeName = "%" + prizeName + "%";

         PageHelper.startPage(pageNumber, pageSize);
         List<Prize> prizeList = prizeService.selectServiceByName(prizeName);
         if (null != prizeList && prizeList.size() > 0) {
             PageInfo<Prize> prizePageInfo = new PageInfo<>(prizeList);
             return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, prizePageInfo);
         }
         return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
     }catch (Exception e){
         log.error(e.getMessage());
         return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
     }

   }

    /**
     *
     * @Description:综服中心_商品发布_添加商品
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/21
     */
    @PostMapping("authority/two/prize")
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
    @PutMapping("authority/two/prize")
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
    * @Description: 综服中心_商品发布_添加商品 商品名唯一校验
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date:  2018/8/22
    */
    @GetMapping("authority/two/prizeEmpty/{prizeName}")
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
    * @Description: 综服中心_商品发布_商品上架
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date: 2018/8/22
    */
    @PostMapping("authority/two/prize/statusOne/{prizeId}")
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
    * @Description: 综服中心_商品发布_商品下架
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date: 2018/8/22
    */
    @PostMapping("authority/two/prize/statusTwo/{prizeId}")
    public OutJSON goodsSoldout(@PathVariable("prizeId") int prizeId){
        try {
            int goodsshelves = prizeService.goodsoldout(prizeId);
            if(goodsshelves>0){
                return OutJSON.getInstance(CodeAndMessageEnum.GOODS_SOLDOUT_SUCCESS,goodsshelves);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return OutJSON.getInstance(CodeAndMessageEnum.GOODS_SOLDOUT_ERROR);
    }

    /**
     * @Description: 综服中心_商品发布_通过商品id查询商品
     * @Param:
     * @return:
     * @Author: suzehua
     * @Date: 2018/8/31
     */
    @GetMapping("authority/two/prize/s/{prizeID}")
    public OutJSON gatPrize(@PathVariable("prizeId") int prizeId){
        try {
            Prize prize = prizeService.getPrize(prizeId);
            if (null == prize){
                return OutJSON.getInstance(CodeAndMessageEnum.NO_GOODS_WERE_FOUND);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , prize );
        } catch (Exception e) {
            e.printStackTrace();
            log.error("gatPrize=>" + e.getMessage() , e);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR );
        }
    }
}