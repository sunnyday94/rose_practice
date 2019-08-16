/**
 * FileName: UserController Author:   sunnyday Date:     2019/4/28 18:35 Description: 用户处理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.controllers;

import com.alibaba.fastjson.JSONObject;
import com.sunny.rose.practice.controllers.base.BaseController;
import com.sunny.rose.practice.common.ResBean;
import com.sunny.rose.practice.common.utils.MD5Util;
import com.sunny.rose.practice.entity.vo.UserVo;
import com.sunny.rose.practice.service.UserService;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户处理类〉
 *
 * @author sunnyday
 * @create 2019/4/28
 * @since 1.0.0
 */
@Path("/users")
@Slf4j
public class UserController extends BaseController {

    @Resource(name="userService")
    private UserService userService;


    /* *
     * @Author sunny
     * @Description  新增用户
     * @Date 4:31 2019/5/4
     * @Param [user]
     * @return java.lang.String
     **/
    @Post("addUser")
    public String addUser(@ModelAttribute UserVo user){
        log.info("接收的user信息是:"+ JSONObject.toJSONString(user));
        try {
            if(StringUtils.isNotBlank(user.getUserPassword())){
                //对密码进行32位大写加密
                user.setUserPassword(MD5Util.MD5Encryption(user.getUserPassword()));
            }
            userService.addUser(user);
        }catch (Exception e){
            return this.getResBeanError(e.getMessage());
        }
        return this.getResBeanSuccess("用户添加成功");
    }



    /* *
     * @Author sunny
     * @Description  根据用户id删除用户(物理删除)
     * @Date 1:53 2019/5/5
     * @Param [id]
     * @return java.lang.String
     **/
    @Post("delUser/{id:[0-9]+}")
    public String delUserById(@Param("id") Long id){
        log.info("删除的用户id为:"+id);
        try{
            userService.delUserById(id);
        }catch (Exception e){
            return this.getResBeanError(e.getMessage());
        }
        return this.getResBeanSuccess(String.format("id为%d的用户删除成功!", id));
    }


    /* *
     * @Author sunny
     * @Description  根据id删除用户(逻辑删除)
     * @Date 9:49 2019/5/5
     * @Param [id]
     * @return java.lang.String
     **/
    @Post("delUserLogic/{id:[0-9]+}")
    public String delUserLogicById(@Param("id") Long id){
        log.info("删除的用户id为:"+id);
        try{
            userService.delUserLogicById(id);
        }catch(Exception e){
            return this.getResBeanError(e.getMessage());
        }
        return this.getResBeanSuccess(String.format("id为%d的用户删除成功!", id));
    }


    /* *
     * @Author sunny
     * @Description 根据id更新用户信息
     * @Date 9:57 2019/5/5
     * @Param [userVo]
     * @return java.lang.String
     **/
    @Post("updateUser")
    public String updateUserById(@ModelAttribute UserVo userVo){
        log.info("接收的user信息是:"+ JSONObject.toJSONString(userVo));
        if(userVo.getId()==null){
            return "@参数id不能为空!";
        }
        try {
            if(StringUtils.isNotBlank(userVo.getUserPassword())){
                //对密码进行32位大写加密
                userVo.setUserPassword(MD5Util.MD5Encryption(userVo.getUserPassword()));
            }
            userService.updateUserById(userVo);
        }catch (Exception e){
            return this.getResBeanError(e.getMessage());
        }
        return this.getResBeanSuccess(String.format("id为%d的用户修改成功!", userVo.getId()));
    }


    /* *
     * @Author sunny
     * @Description  根据条件查询用户列表
     * @Date 11:35 2019/5/5
     * @Param [userVo]
     * @return java.lang.String
     **/
    @Post("selectUserList")
    public String selectUserList(@ModelAttribute UserVo userVo){
        log.info("入参信息是:"+ JSONObject.toJSONString(userVo));
        if(userVo.getPageIndex()==null || userVo.getPageSize()==null){
            return "@参数pageIndex和pageSize不能为空!";
        }
        ResBean.PageInfo<UserVo> pageInfo;
        try {
            pageInfo = userService.selectUserList(userVo);
        }catch (Exception e ){
            return this.getResBeanError(e.getMessage());
        }
        return this.getResBean(pageInfo);
    }


}
