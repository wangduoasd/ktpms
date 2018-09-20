/*
package com.kaituo.pms.config;

import com.kaituo.pms.utils.OutJSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * @author 张金行
 * @version 1.0
 * @Title: MyInterceptor
 * @ProjectName pms
 * @Description:
 * @date 2018/9/3 000315:30*//*




public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getParameter("token")==null){
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
*/
