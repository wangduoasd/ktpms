package com.kaituo.pms.service;



import com.kaituo.pms.domain.Exchange;

import java.util.List;
import java.util.Map;

public interface ExchangeService {

    /**
     *@Description: 根据状态升序查询兑换列表，
     * 并封装到一个List<Map<String,Object>>里面（根据前台页面所需要的数据来进行封装的）
     *@Author: 郭士伟
     *@Date: 2018/7/25
     */
    List<Object> findExchangeByStatusRank();

    /** 
     *@Description: 查询兑换列表的总量
     *@Author: 郭士伟
     *@Date: 2018/7/25
    */
    long findNumberOfExchange();



    int updateExchangeByID(int exchangeId,int status);

    List<Object> findExchangeBySearchStr(String searchStr);
    /** 
    * @Description: 根据UserID查找所有兑换记录总数
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    long findNumberOfExchangeByUserID(Integer userID);

    /** 
    * @Description: 查找所有这个UserID的兑换记录
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    List<Exchange> findExchangeByUserID(Integer userID);

    /** 
    * @Description: 将数据重组为展示数据
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    List<Map> reconstituteUsers(List<Exchange> list);
    
    /** 
    * @Description: 初步处理展示数据
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    Map<String , Object> findExchangeRecordByUser(String pageNumber, String pageSize,Integer userID);

    /** 
    * @Description: 查询当前员工下所有条件相符的兑换记录总数
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */ 
    long findNumberOfExchangeByUserIDAndSearchStr(Integer userID,String searchStr);

    /** 
    * @Description: 查询当前员工下所有条件相符的兑换记录总数
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/27 
    */
    List<Exchange> findExchangeByUserIDAndSearchStr(Integer userID, String searchStr);
    /**
     * @Description: 初步处理同名controller展示数据
     * @Param:
     * @return:
     * @Author: su
     * @Date: 2018/7/27
     */
    Map<String , Object> searchExchangeRecord(String pageNumber, String pageSize,Integer userID , String searchStr);
    /** 
    * @Description: 查找这个id的兑换数据
    * @Param:  
    * @return:  
    * @Author: su
    * @Date: 2018/7/29 
    */ 
    Exchange findExchangeById(String exchangeId);

    Map<String , Object> confirmReceive(String exchangeId , String userId);




}
