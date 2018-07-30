package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.PrizeMapper;
import com.kaituo.pms.domain.Prize;
import com.kaituo.pms.domain.PrizeExample;
import com.kaituo.pms.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrizeServiceImpl implements PrizeService {
    @Autowired
    PrizeMapper prizeMapper;
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
}
