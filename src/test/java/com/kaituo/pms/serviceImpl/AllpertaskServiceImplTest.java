package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.Allpertask;
import com.kaituo.pms.service.AllpertaskService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/14 15:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllpertaskServiceImplTest {

    @Autowired
    AllpertaskService allpertaskService;

    @Test
    public void distribute_Allpertask() {

        Allpertask allpertask = new Allpertask();
        allpertask.setAllpertask_name("第一个任务");
        allpertask.setAllpertask_difficulty(1);
        allpertask.setAllpertask_price(11);
        allpertask.setAllpertask_award(11);
        allpertask.setAllpertask_time(1);

        allpertask.setAllpertask_starttime(new Date(118,10,14,16,28,00));
        allpertask.setAllpertask_endtime(new Date(119,10,14,16,59,00));
        allpertask.setAllpertask_image("123");

        allpertaskService.distribute_Allpertask(allpertask);
    }

    @Test
    public void find_Allpertask_ofadmin() {
        System.out.println("-------------" +
                ""+allpertaskService.find_Allpertask_ofadmin().toString());
    }

    @Test
    public void find_allpertaskfinish() {
    }

    @Test
    public void pass_allpertask() {
    }

    @Test
    public void fail_allpertask() {
    }

    @Test
    public void find_Allpertask_ofuser() {
    }

    @Test
    public void allpertaskList() {
    }

    @Test
    public void get_Allpertask() throws Exception {
        System.out.println(allpertaskService.get_Allpertask(6,1));
    }

    @Test
    public void giveup_allpertask() {
    }

    @Test
    public void finish_allpertask() {
    }
}