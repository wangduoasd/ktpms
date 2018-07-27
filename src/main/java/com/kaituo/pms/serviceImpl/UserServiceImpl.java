package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.domain.User;
import com.kaituo.pms.domain.UserExample;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: ktpms
 * @description: 关于用户的service
 * @author: su
 * @create: 2018-07-25 13:52
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DeptService deptService;

    @Override
    public long findNumberOfUser() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        return userMapper.countByExample(userExample);
    }

    @Override
    public List<User> findUsers() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("user_integral desc");
        UserExample.Criteria criteria = userExample.createCriteria();
        return userMapper.selectByExample(userExample);

    }

    @Override
    public long findNumberOfUserByCondition(String condition) {
        return userMapper.selectNumberOfUserByCondition(condition);
    }

    @Override
    public List<User> findUserByCondition(String condition) {
        return userMapper.selectByCondition(condition);
    }

    @Override
    public List<Map> reconstituteUsers(List<User> list) {
        List<Map> userMapList = new ArrayList<>();
        for(User user : list){
            Map<String , Object> userMap = new HashMap<>(8);
            //从旧数据中得到部门id
            int DeptId= user.getDeptId();
            //通过部门ID得到部门对象
            Dept dept = deptService.findDeptNameByDeptID(DeptId);
            //封装为新的数据
            userMap.put("userId",user.getUserId());
            userMap.put("userName",user.getUserName());
            //部门名字
            userMap.put("deptName",dept.getDeptName());
            userMap.put("userPosition",user.getUserPosition());
            userMap.put("userUsername",user.getUserUsername());
            userMap.put("userInductiontime",user.getUserInductiontime());
            userMap.put("userIntegral",user.getUserIntegral());
            userMap.put("userStatus",user.getUserStatus());
            //添加到新的集合中
            userMapList.add(userMap);
        }
        return userMapList;
    }

    @Override
    public boolean findUserUserName(String condition){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserUsernameEqualTo(condition);
        if (null==userMapper.selectByExample(userExample)){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean updateUser(String userUserName ,String userName , String deptID , String position ,
                                    String userPassword , String inductionTime , String userStatus){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserUsernameEqualTo(userUserName);

        Date date = Util.stampToDate(inductionTime);

        User user = new User();
        user.setUserName(userName);
        user.setDeptId(Integer.parseInt(deptID));
        user.setUserPosition(position);
        user.setUserPassword(userPassword);
        user.setUserInductiontime(date);
        user.setUserStatus(Integer.parseInt(userStatus));

        try {
            userMapper.updateByExampleSelective(user , userExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
