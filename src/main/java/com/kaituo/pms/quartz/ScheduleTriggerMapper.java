package com.kaituo.pms.quartz;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/5 9:18
 */
@Repository
@Mapper
public interface ScheduleTriggerMapper {
    List<CScheduleTrigger> queryAll();
}
