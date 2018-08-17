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


}
