package com.kaituo.pms.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @program: pms
 * @description:
 * @author: su
 * @create: 2018-08-07 11:22
 **/
@RestController
@RequestMapping("test")
public class test {
    @RequestMapping(value = "test/{id}" , method = RequestMethod.GET)
    public int say(@PathVariable("id") int id){
        System.out.println(id);
        return id;
    }

    @ResponseBody
    @GetMapping("hel")
    public String hel(){
        return "asdf";
    }
}
