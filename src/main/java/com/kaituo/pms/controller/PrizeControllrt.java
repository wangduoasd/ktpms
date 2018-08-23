package com.kaituo.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Prize;
import com.kaituo.pms.bean.User;
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
public class PrizeControllrt {
    @Autowired
    PrizeService prizeService;
    @Autowired
    UserService userService;

    /**
     * @Description:兑换中心_商品列表 点击按钮进行搜索查询 根据商品名查询已经上架商品
     * @Param:
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/14
     */
    @GetMapping( value ={"prizes/s/{prizeName}/{pageNumber}",
            "prizes/s/{prizeName}/{pageNumber}/{pageSize}"
    })
    public OutJSON findByName(@PathVariable("prizeName") String prizeName,
                              @PathVariable(value = "pageNumber") int pageNumber,
                              @PathVariable(value = "pageSize") int pageSize){
        try {
            PageHelper.startPage(pageNumber, pageSize);
            List<Prize> prizeList = prizeService.selectByName(prizeName);
            PageInfo pageInfo = new PageInfo<>(prizeList,5);
            if(prizeList!=null&&prizeList.size()>0){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,pageInfo);
            }
        } catch (Exception e) {
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
   @GetMapping(value={"prizes/{userId}/{pageNumber}/{pageSize}",
           "prizes/{userId}/{pageNumber}"
   })
   public OutJSON findAllPrizePrize(@PathVariable("userId") int userId,
                                    @PathVariable(value = "pageNumber") int pageNumber,
                                    @PathVariable(value = "pageSize") int pageSize){
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
   @PutMapping(value="prize/{userId}/{pageNumber}/{prizeId}")
   public OutJSON exchangePrize(@PathVariable("userId")  int userId,@PathVariable("pageNumber") int number,@PathVariable("prizeId") int prizeId){
      try {
       Prize prize = prizeService.selectByPrimaryKey(prizeId);
       int count = (prize.getPrizeAmount()-number);
       prize.setPrizeAmount(count);
       prizeService.updateByPrimaryKey(userId,number,prizeId);
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
   }}catch (Exception e){
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
   @GetMapping("prizes/{pageNumber}/{pageSize}")
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
   * @Description:综服中心_商品发布  删除商品
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
    *
   * @Description:综服中心_商品发布 搜索商品
   * @Param:
   * @return:
   * @Author: 侯鹏
   * @Date: 2018/8/21
   */
   @GetMapping("/prize/s/{prizeName}")
   public OutJSON selectServiceByName(@PathVariable("prizeName") String prizeName){
       List<Prize> prizeList = prizeService.selectServiceByName(prizeName);
       if(null!=prizeList&&prizeList.size()>0){
           return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, prizeList);
       }
       return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
   }

    /**
     *
     * @Description:综服中心_商品发布_添加商品
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

    @PutMapping("prize")
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
    * @Description: 综服中心_商品发布_商品上架
    * @Param:
    * @return:
    * @Author: 侯鹏
    * @Date: 2018/8/22
    */
    @PostMapping("/prize/statusOne/{prizeId}")
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
    @PostMapping("/prize/statusTwo/{prizeId}")
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