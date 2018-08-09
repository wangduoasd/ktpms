package com.kaituo.pms.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局捕获异常
 */
@ControllerAdvice(basePackages = "com.kaituo.pms.controller")
public class ExcrptionHanler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Msg errorResult() {

        return  Msg.fail(500,"系统错误");

    }
}
