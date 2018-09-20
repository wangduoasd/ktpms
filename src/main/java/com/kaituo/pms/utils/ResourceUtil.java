package com.kaituo.pms.utils;


import com.kaituo.pms.bean.Token;

import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.serviceImpl.TokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;

import java.util.Enumeration;


public class ResourceUtil {

	/** logger */
	private static final Logger log = LoggerFactory.getLogger(ResourceUtil.class);

	public static final Token getTokenUser() {
        String token =ContextHolderUtils.getRequest().getHeader("token");

        Enumeration<String> HeaderNames1 =ContextHolderUtils.getRequest().getHeaders("token");

        String token2 = "";

		while(HeaderNames1.hasMoreElements()){
			String Key=HeaderNames1.nextElement();
			if (!StringUtils.isEmpty(Key)){
                token2=Key;
			}
		}

		//拿token获取对应的用户信息对象
        TokenService tokenServiceimpl = new TokenServiceImpl();
        Token tokenEntity = tokenServiceimpl.selectUserIdByToken(token);

		return tokenEntity;
	}



}