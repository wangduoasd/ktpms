package com.kaituo.pms.controller;

import com.kaituo.pms.utils.DynamicScheduledTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author @chnxy_xrabbit
 * @date 2018/11/2 15:42
 */
@Controller
@RequestMapping("/taskAll")
@CrossOrigin
public class TaskAllController {
    @RequestMapping("/SchedulingTask")
    public String  SchedulingTask(String cron,int id){
        DynamicScheduledTask dynamicScheduledTask=new DynamicScheduledTask();
        dynamicScheduledTask.setId(id);
        dynamicScheduledTask.setCron(cron);
        return "1";
    }
}
