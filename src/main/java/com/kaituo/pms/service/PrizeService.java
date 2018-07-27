package com.kaituo.pms.service;

import com.kaituo.pms.domain.Prize;

import java.util.List;

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
}
