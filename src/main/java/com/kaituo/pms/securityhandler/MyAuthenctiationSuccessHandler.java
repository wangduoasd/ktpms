/*
package com.kaituo.pms.securityhandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAuthenctiationSuccessHandler")
@Slf4j
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
       request.getSession().setAttribute("userId",request.getParameter("username"));
       response.setContentType("application/json;charset=UTF-8");
       response.getWriter().write(objectMapper.writeValueAsString(authentication));
    }
}
*/
