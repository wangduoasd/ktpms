package com.kaituo.pms.service;


import com.kaituo.pms.bean.Allpertask;

/**
 * @author wangduo
 * @date 2018/11/6 - 12:59
 */
public interface AllpertaskService {
    void distribute_Allpertask(Allpertask allpertask);
    void delete_Allpertask(Integer Allpertask_id);
}
