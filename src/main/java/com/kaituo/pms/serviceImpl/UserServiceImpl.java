package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.bean.*;
import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.service.*;
import com.kaituo.pms.utils.JwtToken;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;

/**
 * @program: ktpms
 * @description: UserService的实现
 * @author: 由苏泽华创建
 * @create: 2018-08-08 14:35
 **/
@Service
public class UserServiceImpl implements UserService/*,UserDetailsService*/ {
    @Autowired
    UserMapper userMapper;
/*    @Autowired
    private PasswordEncoder passwordEncoder;*/
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    DeptService deptService;
    @Autowired
    PositionService positionService;
    @Autowired
    IntegralService integralService;
    @Autowired
    TokenService tokenService;
    /**
    * @Description:  从用户视图中获取除超级管理员外全部数据
    * @Param:
    * @return:
    * @Author: 苏泽华
    * @Date: 2018/8/8
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> listUserRankingByPage() {
        List<User> userList = userMapper.selectUsersByView();
        return userList;
    }
/** 
* @Description: 个人中心我的信息
* @Param:  
* @return:  
* @Author: 侯鹏
* @Date:  2018/8/10
*/ 
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User findPersonalDetail(int userid) {
        return userMapper.findPersonalDetail(userid);

    }

    /**
     * 查询所有名字或部门有关键字的员工数量
     * @Description 查询所有名字或部门有关键字的员工数量
     * @Param condition
     * @return long
     * @Author 苏泽华
     * @Date 2018/8/8
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long countUsersByView() {
        return userMapper.countUsersByView();
    }

    /**
     * 从用户视图中获取除超级管理员外条件相符合的全部数据
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> selectUsersByViewAndCondition(String condition) {
        List<User> userList = userMapper.selectUsersByViewAndCondition(condition);
        return userList;
    }

    /**
     * 从用户视图中获取除超级管理员外条件相符合的全部数据的条数
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long countUsersByViewAndCondition(String condition) {
        return userMapper.countUsersByViewAndCondition(condition);
    }

    /**
     * 通过员工id获取对应的一个员工数据
     * @Description: 个人中心我的信息
     * @param userid 员工id
     * @return:
     * @Author: 苏泽华
     * @Date:2018/8/9
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getUserFromTable(int userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> findByKeyWord(String keyWord) {
        return userMapper.findByKeyWord(keyWord);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        for(User u:users){
           if( u.getUserId().equals(user.getUserId())){
               return 2;}
        }

        return userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int upUser(User user,int oldUserId) {
        if(user.getUserStatus()==4){user.setUserIntegral(0);}
        if(user.getUserId()==oldUserId){return userMapper.updateByPrimaryKey(user);}
        User userById = findUserById(user.getUserId());
        if(userById!=null){
            return 2;}
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(oldUserId);
            return userMapper.updateByExample(user, example);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> findUserRole() {
        List<User> userRole = userMapper.findUserRole();
       for (int i=0;i<=userRole.size()-1;i++){
           if(userRoleService.findAllRole(userRole.get(i).getUserId())!=null){
               userRole.remove(i);
               i-=1;
           }
       }
       return  userRole;
    }

    @Override
    public int upUserIntegral(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user.getUserId());
        return userMapper.updateByExampleSelective(user , example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> findRoleUser() {
        List<User> roleUser = userMapper.findRoleUser();

        return  roleUser;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User findUserById(int userId) {

        return  userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int upUserPassword(int userId,String oldPassWord, String newPassWord) {
        if(!userMapper.selectByPrimaryKey(userId).getUserPassword().equals(oldPassWord)){

            return 2;
        }
        User user = new User();
        user.setUserId(userId);
        user.setUserPassword(newPassWord);
        return userMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int upUserIntegral(int operatorId,int userId,String changeStr,int changeInt) {
        User user = userMapper.getUserById(userId);
        int startNum=user.getUserIntegral();
        user.setUserId(userId);
        user.setUserIntegral(changeInt);
        userMapper.updateByPrimaryKeySelective(user);
        return integralService.addIntegral(operatorId,changeStr,userId,changeInt-startNum,changeInt );
    }



    @Override
    public Login login(int userId, String password){
        Login login = new Login();
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null||!user.getUserPassword().equals(password)){
            return null;
        }
        user.setRole(userRoleService.findAllRole(userId));

        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        /*   if(user==null||user.s)*/
        int i=0;
        Object[] objects = new Object[6];
        if(user.getRole()!=null) {
            for (Role role : user.getRole()) {
                if (null == role)
                    break;
                HashMap<String, String> authorities = new HashMap<>();
                authorities.put("authority", "" + role.getRoleId());
                objects[i] = authorities;
                i++;
            }
        }
        login.setAuthorities(objects);
        Token token = new Token();
        try {
            token.setToken(JwtToken.createToken(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        token.setUserId(userId);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        token.setFailureTime(Timestamp.valueOf(time));
        tokenService.addToken(token);
        return login;
    }



    /**
     　  * @Description: 用户登录校验
     　　* @param s 用户ID
     　　* @return org.springframework.security.core.userdetails.UserDetails
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/20 0020 17:22
     　　*/
/*    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        int userId=Integer.parseInt(s);
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRole(userRoleService.findAllRole(userId));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        *//*   if(user==null||user.s)*//*
        for(Role role:user.getRole())
        {
            if(null==role)
                break;
            authorities.add(new SimpleGrantedAuthority(""+role.getRoleId()));

        }

        return new org.springframework.security.core.userdetails.User(s, user.getUserPassword(),
                *//*  AuthorityUtils.commaSeparatedStringToAuthorityList("admin");*//*
                authorities);
    }*/
    @Override
    public int findUserByDeptPositionId(int deptPositionId){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserDeptPositionEqualTo(deptPositionId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users==null){return 0;}
        return users.size();
    }
}
