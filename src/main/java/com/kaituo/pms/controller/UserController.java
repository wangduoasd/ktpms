package com.kaituo.pms.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaituo.pms.bean.User;
import com.kaituo.pms.service.UserService;

import com.kaituo.pms.utils.CodeAndMessageEnum;
import com.kaituo.pms.utils.OutJSON;
import com.kaituo.pms.utils.Util;
import com.kaituo.pms.utils.ExportExcelSeedBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    /**
    * @Description: 积分排行榜分页
    * @Param:
    * @return:
    * @Author: 苏泽华
    * @Date: 2018/8/9
    */
    @GetMapping("userIntegrals/{pageNumber}")
    public OutJSON findRankingByPage(@PathVariable(value = "pageNumber") int pageNumber) {
        try {
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
    @GetMapping(value = "user/{id}")
    public OutJSON findPersonalDetail(@PathVariable("id") int id) {

        //个人信息获取成功
        try {
            User personalDetail = userService.findPersonalDetail(id);
            if (null != personalDetail)
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , personalDetail);

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
                objs[6] = user.getUserId();
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
    @GetMapping(value = {"userIntegrals/{pageNumber}/{pageSize}/{condition}" , "userIntegrals/{pageNumber}/{condition}"})
    public OutJSON findRankingByPageAndCondition(@PathVariable(value = "pageNumber")
                                                         int pageNumber,
                                                 @PathVariable(required = false)
                                                         Integer pageSize,
                                                 @PathVariable(value = "condition" , required = false) String condition) {
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
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, data);
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
    @GetMapping(value = "users/{pn}")
    public OutJSON findAllUser(@PathVariable(value = "pn") int pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        try {
            PageHelper.startPage(pageNumber, pageSize);
            List<User> list = userService.findAllUser();
            if(list==null)
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
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
    @GetMapping(value = "users/s/{keyword}/{pn}")
    public OutJSON findByKeyWord(@PathVariable(value = "keyword") String keyword,
                               @PathVariable(value = "pn") int pageNumber,
                               @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {
        try {
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
    @PostMapping(value = "user")
    public OutJSON addUser(User user) {
        try {
            int i=userService.addUser(user);
            if(i==2){
                return OutJSON.getInstance(CodeAndMessageEnum.USER_ADD_ERROR);
            }
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
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
    @PutMapping(value = "user")
    public OutJSON upUser(User user) {

        try {
            int i=userService.upUser(user);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }
    /**
     　  * @Description: 权限设置_添加员工_下拉列表 获取没有权限的用户
     　　* @param []
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:37
     　　*/
    @GetMapping(value = "role/users")
    public OutJSON findUserRole() {

        try {
            List<User> userRole = userService.findUserRole();
            if (null != userRole)
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS , userRole);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("" + e.getMessage());

        }
        //个人信息获取失败
        return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
    }
    /**
     　  * @Description: 部门设置_权限用户列表
     　　* @param [pageNumber, pageSize]
     　　* @return com.kaituo.pms.utils.OutJSON
     　　* @throws
     　　* @author 张金行
     　　* @date 2018/8/23 0023 13:39
     　　*/
    @ResponseBody
    @GetMapping(value = "role/users/{pn}")
    public OutJSON findRoleUser(@PathVariable(value = "pn") int pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "8") int pageSize) {

        try {
            PageHelper.startPage(pageNumber, pageSize);
            List<User> list = userService.findRoleUser();
            if(list==null)
                return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
            PageInfo pageInfo = new PageInfo(list, 5);
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_SUCCESS, pageInfo);
        } catch (Exception e) {
            log.error( e.getMessage());
            return OutJSON.getInstance(CodeAndMessageEnum.ALL_ERROR);
        }
    }

}