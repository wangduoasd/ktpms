package com.kaituo.pms;

import com.kaituo.pms.domain.Integral;
import com.kaituo.pms.serviceImpl.InterfaceServiceImpl;

import java.util.Date;

/**
 * @program: pms
 * @description:
 * @author: su
 * @create: 2018-07-27 10:50
 **/

public class a {

    public static void main(String[] args){
        InterfaceServiceImpl interfaceService = new InterfaceServiceImpl();
        interfaceService.addNewUserIntegral(1, 0 , "张三");
    }
}
