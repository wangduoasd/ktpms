package com.kaituo.pms.serviceImpl;

import com.kaituo.pms.dao.UserMapper;
import com.kaituo.pms.domain.Dept;
import com.kaituo.pms.domain.Role;
import com.kaituo.pms.domain.User;
import com.kaituo.pms.domain.UserExample;
import com.kaituo.pms.service.DeptService;
import com.kaituo.pms.service.UserService;
import com.kaituo.pms.utils.MapUtil;
import com.kaituo.pms.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;*/
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
public class UserServiceImpl implements UserService/*,UserDetailsService */{

    @Autowired
    UserMapper userMapper;

    @Autowired
    DeptService deptService;
/*    @Autowired
    private PasswordEncoder passwordEncoder;*/

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

    @Override
    public List<User> findRankingByPage() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        /*根据user_integral字段升序*/
        userExample.setOrderByClause("'user_integral' DESC");
       return userMapper.selectByExample(userExample);
    }

    @Override
    public List<User> searchRanking(String keyword) {
        return userMapper.selectBykeyWord(keyword);

    }

//    @Override
//    public User findPersonalDetail(Integer userid) {
//        return userMapper.selectByPrimaryKey(userid);
//    }

//    @Override
//    public User login(User user) {
//        UserExample userExample = new UserExample();
//        UserExample.Criteria criteria = userExample.createCriteria();
//        criteria.andUserUsernameEqualTo(user.getUserUsername()).andUserPasswordEqualTo(user.getUserPassword());
//        List<User> users = userMapper.selectByExample(userExample);
//        return  users.get(0);
//    }

/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//重写loadUserByUsername 方法获得 userdetails 类型用户
        User user = userMapper.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        for(Role role:user.getRoles())
        {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
 String ps=passwordEncoder.encode(user.getUserPassword());
        System.out.println(ps);
        return new org.springframework.security.core.userdetails.User(user.getUserUsername(), ps, *//*AuthorityUtils.commaSeparatedStringToAuthorityList("admin")*//*authorities);

    }*/

    @Override
    public Map<String, Object> findPersonalDetail(Integer userID) {
        User user = userMapper.selectByPrimaryKey(userID);
        Map<String , Object> map = null;
        List<Map> userMapList = new ArrayList<>();
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
        //封装map
        Map<String,Object> data = new HashMap<>(2);

        //员工的信息
        data.put("User",userMapList);
        map = MapUtil.setMap2("0","成功",data);
        return map;
    }

    @Override
    public User findUserByID(int userID) {
        User user = userMapper.selectByPrimaryKey(userID);
        return user;
    }

    @Override
    public int upDate(User record, UserExample example) {
        return userMapper.updateByExample(record,example);
    }
}
