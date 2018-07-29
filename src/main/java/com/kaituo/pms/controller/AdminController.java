package com.kaituo.pms.controller;

import com.kaituo.pms.domain.User;
import com.kaituo.pms.utils.MapUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    /** 
     *@Description: 登陆认证
     *@Param: 
     *@return: 
     *@Author: 郭士伟
     *@Date: 2018/7/26
    */
    @PostMapping(value = "login")
    @ResponseBody
    public String login( User user){
        System.out.println("进入到了登陆Controller");
        System.out.println("getUserUsername---"+user.getUserUsername());
        System.out.println("getUserPassword---"+user.getUserPassword());
        //获取用户输入的用户名密码
        String username = user.getUserUsername();
        String password = user.getUserPassword();
        //将用户名和密码包装起来
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(usernamePasswordToken);

        }catch (UnknownAccountException e){
            usernamePasswordToken.clear();
            return "账号错误";
        }catch (IncorrectCredentialsException e){
            usernamePasswordToken.clear();
            return "密码错误";
        }catch (Exception e){
            usernamePasswordToken.clear();
            return "登陆异常";
        }
        return "index";
    }

    /** 
     *@Description: 测试
     *@Author: 郭士伟
     *@Date: 2018/7/26
    */

    @RequestMapping(value = "test")
    public void test(HttpSession session){
        User user = (User)session.getAttribute("token");
        System.out.println(user.getUserName());
    }

}
