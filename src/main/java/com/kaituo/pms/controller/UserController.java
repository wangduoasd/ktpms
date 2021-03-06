package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.Login;
import com.kaituo.pms.bean.Role;
import com.kaituo.pms.bean.Token;
import com.kaituo.pms.bean.User;

import com.kaituo.pms.service.RoleService;
import com.kaituo.pms.service.TokenService;
import com.kaituo.pms.service.UserService;

import com.kaituo.pms.utils.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.OutputStream;

import java.util.*;

/**
 * @program: ktpms
 * @description: 有关任务的controller
 * @author: 苏泽华,张金行，侯鹏
 * @create: 2018-08-09 23:00
 **/
@Slf4j
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    RoleService roleService;

    /**
    * @Description: 积分排行榜分页
    * @Param:
    * @return:
    * @Author: 苏泽华
    * @Date: 2018/8/9
    */
    @GetMapping("userIntegrals/{pageNumber}/{token:.+}")
    public OutJSON findRankingByPage(@PathVariable(value = "pageNumber") int pageNumber,@PathVariable("token")String token) {
        try {

            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 每页显示数量设置为8条
            int pageSize = 8;
            // 查询总条数
            int total = (int) userService.countUsersByView();

            if (0 < total) {
                //查询条件满足的员工的信息
                //分页
                PageHelper.startPage(pageNumber, pageSize);
                //从用户视图中获取除超级管理员外全部数据
                List<User> leaderboardList = userService.listUserRankingByPage();
                //封装map
                Map<String, Object> data = new HashMap<>(2);

                //员工的总数
                data.put("total", total);
                //员工的信息
                data.put("User", leaderboardList);
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, data);
            } else {
                return OutJSON.getInstance(CodeAndMessageEnum.FIND_RANKING_BY_PAGE_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            //发生异常
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * 通过ID查询员工个人信息
     * @Description: 个人中心我的信息
     * @Param: id
     * @return:
     * @Author: 侯鹏
     * @Date: 2018/8/8
     */
    @GetMapping(value = "user/{token:.+}")
    public OutJSON findPersonalDetail(@PathVariable("token") String token) {

        //个人信息获取成功
        try {
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            Integer userId = token1.getUserId();
            User personalDetail = userService.findPersonalDetail(userId);
            if(personalDetail==null){
                personalDetail=userService.findUserById(userId);
              }
            if (null != personalDetail){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , personalDetail);}
        } catch (Exception e) {
            e.printStackTrace();
            log.error("" + e.getMessage());

        }
        //个人信息获取失败
        return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }

    /**
     * 导出Excel
     *
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @RequestMapping("excel")
    public void orderExport(HttpServletRequest request, HttpServletResponse response) {

        // 进行查询并导出
        List<User> leaderboardList = userService.listUserRankingByPage();

        // 查询的数据不为空就对数据进行导出
        if (leaderboardList != null && leaderboardList.size() > 0) {
            // 导出文件的标题
            String title = "积分排行榜" + Util.dateUtil(new Date()) + ".xls";
            // 设置表格标题行
            String[] headers = new String[]{"排名", "姓名", "部门", "职位", "工号", "入职时间", "当前积分", "状态"};
            List<Object[]> dataList = new ArrayList<Object[]>();
            Object[] objs = null;

            // 循环每一条数据
            for (User user : leaderboardList) {
                objs = new Object[headers.length];
                // 排名
                objs[0] = user.getNum();
                // 姓名
                objs[1] = user.getUserName();
                // 部门
                objs[2] = user.getDeptName();
                // 职位
                objs[3] = user.getPositionName();
                // 工号
                objs[4] = user.getUserId();
                // 入职时间
                if (user.getUserInductiontime() != null) {
                    objs[5] = Util.dateUtil(user.getUserInductiontime());
                } else {
                    objs[5] = "无";
                }
                // 当前积分
                objs[6] = user.getUserIntegral();
                // 状态
                switch (user.getUserStatus()) {
                    case 0:
                        objs[7] = "正常";
                        break;
                    case 1:
                        objs[7] = "积分冻结";
                        break;
                    case 2:
                        objs[7] = "书面警告";
                        break;
                    case 3:
                        objs[7] = "留职观察";
                        break;
                    case 4:
                        objs[7] = "离职";
                        break;
                    case 5:
                        objs[7] = "超级管理员";
                        break;
                    default:
                        objs[7] = "";
                        break;
                }

                // 数据添加到excel表格
                dataList.add(objs);
            }

            // 使用流将数据导出
            OutputStream out = null;
            try {
                // 防止中文乱码
                String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "ISO8859-1") + "\"";
                response.setContentType("octets/stream");
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", headStr);
                out = response.getOutputStream();

                ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);
                ex.export(out);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());

            }
        }


    }

    /**
     * @Description: 积分排行榜搜索
     * @Param:
     * @return:
     * @Author: 苏泽华
     * @Date: 2018/8/9
     */
    @GetMapping(value = {"userIntegrals/{pageNumber}/{pageSize}/{condition}/{token:.+}" , "userIntegrals/{pageNumber}/{condition}/{token:.+}"})
    public OutJSON findRankingByPageAndCondition(@PathVariable(value = "pageNumber")
                                                         int pageNumber,
                                                 @PathVariable(required = false)
                                                         Integer pageSize,
                                                 @PathVariable(value = "condition" , required = false) String condition ,
                                                 @PathVariable(value = "token") String token) {
        // 检查token并获得userID
        Token token1 = tokenService.selectUserIdByToken(token);
        if (null == token1){
            return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
        }
        try {
            // 如果没有传每页显示数量设置为8条
            if (null==pageSize){
                pageSize = 8;
            }
            // 查询总条数
            int total = (int) userService.countUsersByViewAndCondition(condition);

            if (0 < total) {
                //查询除超级管理员外条件满足的员工的信息
                //分页
                PageHelper.startPage(pageNumber, pageSize);
                //从用户视图中获取除超级管理员外条件满足的全部数据
                List<User> leaderboardList = userService.selectUsersByViewAndCondition(condition);
                //封装map
                Map<String, Object> data = new HashMap<>(2);

                //员工的总数
                data.put("total", total);
                //员工的信息
                data.put("User", leaderboardList);

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, data );
            } else {
                return OutJSON.getInstance(CodeAndMessageEnum.FIND_RANKING_BY_PAGE_AND_CONDITION_NULL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            //发生异常
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

    /**
     * @Description: 综服中心-员工设置-分页查询所有员工
     * @param
     * @return
     * @throws
     * @author 张金行
     * @date 2018/8/17 0017 17:35
     */
    @ResponseBody
    @GetMapping(value = "authority/one/users/{pn}/{token:.+}")
    public OutJSON findAllUser(@PathVariable(value = "pn") int pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                               @PathVariable("token") String token) {
        try {
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }

            // 权限控制

            if(roleService.checkRole(Constant.ROLE_USER_SET,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }


            PageHelper.startPage(pageNumber, pageSize);
            List<User> list = userService.findAllUser();
            if(list==null){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);}
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     * @Description: 综服中心-员工设置-员工修改 通过用户ID获得用户信息
     * @param
     * @return
     * @throws
     * @author 张金行
     * @date 2018/8/17 0017 17:35
     */
    @ResponseBody
    @GetMapping(value = "authority/one/user/{userId}/{token:.+}")
    public OutJSON findAllUser(@PathVariable(value = "userId") int userId,@PathVariable(value = "token") String token) {
        try {
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_USER_SET,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            User user = userService.getUserById(userId);
            if(user==null){
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);}

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,user);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 综服中心_员工设置_员工搜索  通过部门名或者员工名
     　　* @param [user]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:42
     　　*/
    @ResponseBody
    @GetMapping(value = "authority/one/users/{keyword}/{pn}/{token:.+}")
    public OutJSON findByKeyWord(@PathVariable(value = "keyword",required = false) String keyword,
                               @PathVariable(value = "pn") int pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                 @PathVariable(value = "token") String token ) {
        try {
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_USER_SET,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }

            PageHelper.startPage(pageNumber, pageSize);
            List<User> list = userService.findByKeyWord(keyword);
            PageInfo pageInfo = new PageInfo(list, 5);

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 综服中心_员工设置_新增员工
     　　* @param [user]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:42
     　　*/
    @ResponseBody
    @PostMapping(value = "authority/one/user")
    public OutJSON addUser(User user) {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_USER_SET,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            if(user.getUserId()<=0){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_ADD_USERID_ERROR);
            }
            if (null == user.getUserPassword()||user.getUserPassword().isEmpty()){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_PASSWORD_EMPTY_CHECK);
            }
            if (user.getUserPassword().length()<6){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_PASSWORD_LONG_CHECK);
            }
            System.out.println(user.getUserInductiontime());
            int i=userService.addUser(user);
            if(i==1){

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);}
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_ADD_ERROR);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 综服中心_员工设置_修改员工
     　　* @param [user]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:40
     　　*/
    @ResponseBody
    @PutMapping(value = "authority/one/user")
    public OutJSON upUser(User user,@RequestParam("oldUserId") int oldUserId) {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_USER_SET,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }

            int i=userService.upUser(user,oldUserId);
            if(i==1){

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);}
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_UP_ERROR);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 权限管理_添加员工_下拉列表 获取没有权限的用户
     　　* @param []
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:37
     　　*/
    @GetMapping(value = "authority/all/role/users/{token:.+}")
    public OutJSON findUserRole( @PathVariable("token")String token) {
        try {
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_ALL,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }

            List<User> userRole = userService.findUserRole();

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS,userRole);
        } catch (Exception e) {
            log.error("" + e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);


        }
        //个人信息获取失败

    }
    /**
     　  * @Description: 权限管理_权限用户列表
     　　* @param [pageNumber, pageSize]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:39
     　　*/
    @ResponseBody
    @GetMapping(value = "authority/all/role/users/{pn}/{token:.+}")
    public OutJSON findRoleUser(@PathVariable(value = "pn") int pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "8") int pageSize,
                                @PathVariable("token")String token) {

        try {
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_ALL,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }

            PageHelper.startPage(pageNumber, pageSize);
            List<User> list = userService.findRoleUser();
            PageInfo pageInfo = new PageInfo(list, 5);

            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 密码修改
     　　* @param [user]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:40
     　　*/
    @ResponseBody
    @PutMapping(value = "user/password")
    public OutJSON upUserPassword(@RequestParam("oldPassWord") String oldPassWord,@RequestParam("newPassWord")String newPassWord) {
        try {
            if(newPassWord.isEmpty()||null==newPassWord){
              return OutJSON.getInstance(CodeAndMessageEnum.User_PASSWORD_ERROR);
            }
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            int i=userService.upUserPassword(token1.getUserId(),MD5Util.getMD5(oldPassWord),MD5Util.getMD5(newPassWord));
            if(i==1){

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);}
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_PASSWORD_CHECK);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 综服中心_员工设置_修改积分
     　　* @param [user]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:40
     　　*/
    @ResponseBody
    @PutMapping(value = "authority/one/user/integral/{userId}")
    public OutJSON upUserPassword(
                                  @RequestParam("changeInt")int changeInt,
                                  @RequestParam("changestr") String changestr,
                                  @PathVariable(value = "userId")int userId
                                  ) {

        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            // 权限控制

            if(roleService.checkRole(Constant.ROLE_USER_SET,token1.getUserId())){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            User userById = userService.findUserById(userId);
            if(userById.getUserStatus()==1){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_STATUS_ERROR);
            }

            int i=userService.upUserIntegral(token1.getUserId(),userId, changestr, changeInt);
            if(i==1){

                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);}
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_PASSWORD_CHECK);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_OPERATION_ERROR);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    @PostMapping("user/login")
        public OutJSON login(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        try {

            int userId= 0;
            try {
                userId = Integer.parseInt(username);
            } catch (NumberFormatException e) {
                return OutJSON.getInstance(CodeAndMessageEnum.USER_LOGIN_ERROR);
            }
            String passwordMD5 = MD5Util.getMD5(password);

            Login login = userService.login(userId, passwordMD5);

            if (login == null) {
                return OutJSON.getInstance(CodeAndMessageEnum.USER_LOGIN_ERROR);
            }
            String token = JwtToken.createToken(userId);
            System.out.println(token);
            login.setToken(token);

            if (tokenService.haveToken(userId)){
                tokenService.delectToken(userId);
            }
            tokenService.addToken(Token.getNewToken(userId,token));
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, login);
        } catch (Exception e) {

            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    @PutMapping("logout")
    public OutJSON logout() {
        try {
            String token =ContextHolderUtils.getRequest().getHeader("token");
            // 检查token并获得userID
            Token token1 = tokenService.selectUserIdByToken(token);
            if (null == token1){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            int i = tokenService.delectToken(token);
            if(i==0){
                return OutJSON.getInstance(CodeAndMessageEnum.TOKEN_EXPIRED);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

}