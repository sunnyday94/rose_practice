/**
 * FileName: UserService Author:   sunnyday Date:     2019/5/4 4:14 Description: 用户Service History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.service;

import com.sunny.rose.practice.dao.UserDAO;
import com.sunny.rose.practice.entity.vo.UserVo;
import com.sunny.rose.practice.common.ResBean;
import com.sunny.rose.practice.common.utils.BaseUtils;
import com.sunny.rose.practice.entity.metadata.User;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户Service〉
 *
 * @author sunnyday
 * @create 2019/5/4
 * @since 1.0.0
 */
@Service("userService")
public class UserService {

    @Resource
    private UserDAO userDAO;

    //日期时间格式化
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Integer addUser(UserVo user) {
        User u  = new User();
        BaseUtils.copyBeanNullInvalid(user, u);
        return userDAO.addUser(u);
    }


    public void delUserById(Long id) {
        userDAO.delUserById(id);
    }

    public void delUserLogicById(Long id) {
        userDAO.delUserLogicById(id);
    }

    public void updateUserById(UserVo userVo) {
        User u = new User();
        BaseUtils.copyBeanNullInvalid(userVo, u);
        userDAO.updateUserById(u);
    }


    public ResBean.PageInfo<UserVo> selectUserList(UserVo userVo) {
        Integer start = userVo.getPageIndex()-1;
        Integer end = userVo.getPageSize();
        ResBean.PageInfo<UserVo> pageInfo = new ResBean.PageInfo<>();
        User u = new User();
        BaseUtils.copyBeanNullInvalid(userVo, u);
        List<User> userList = userDAO.selectUserList(u,start,end);
        Integer total = userDAO.selectUserCounts(u);
        pageInfo.setTotal(total);
        pageInfo.setList(
                userList.stream().map(x -> {
                    UserVo y = new UserVo();
                    BaseUtils.copyBeanNullInvalid(x, y);
                    if(y.getCrDate()!=null){
                        y.setCrDateStr(sdf.format(y.getCrDate()));
                    }
                    if(y.getOpDate()!=null){
                        y.setOpDateStr(sdf.format(y.getOpDate()));
                    }
                    if(y.getDelDate()!=null){
                        y.setDelDateStr(sdf.format(y.getDelDate()));
                    }
                    return y;
                }).collect(Collectors.toList())
        );
        return pageInfo;
    }
}
