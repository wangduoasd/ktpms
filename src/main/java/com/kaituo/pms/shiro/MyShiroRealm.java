package com.kaituo.pms.shiro;


import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.domain.User;
import com.kaituo.pms.domain.UserExample;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** 
 *@Description: 自定义密码比较器
 *@Author: 郭士伟
 *@Date: 2018/7/26
*/

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Override
    public String  getName(){
        return "MyShiroRealm";
    }

    /** 
     *@Description: 授权
     *@Author: 郭士伟
     *@Date: 2018/7/26
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *@Description: 认证
     *@Author: 郭士伟
     *@Date: 2018/7/26
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //根据令牌信息获取用户的账号
        String username = (String)token.getPrincipal();
        //根据账号查询出用户信息
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(example);
        if(list.size() <=  0 || list == null){
            return null;
        }
        User user = list.get(0);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user,//用户信息
                user.getUserPassword(),//用户密码
                ByteSource.Util.bytes(user.getUserUsername()),//加密的盐
                getName()//realm name
        );
        return simpleAuthenticationInfo;
    }
}
