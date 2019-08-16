/**
 * FileName: UserDAO Author:   sunnyday Date:     2019/4/28 17:47 Description: 用户dao History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.dao;

import com.sunny.rose.practice.entity.metadata.User;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户dao〉
 *
 * @author sunnyday
 * @create 2019/4/28
 * @since 1.0.0
 */
@DAO
public interface UserDAO{


    /* *
     * @Author sunny
     * @Description  新增用户并返回新增主键id
     * @Date 4:24 2019/5/4
     * @Param [u]
     * @return int
     **/
    @ReturnGeneratedKeys
    @SQL("insert into t_user(user_name,user_password,user_nickname," +
            "del_tag,cr_date,op_date,del_date,remark)"
            + " values(:u.userName,:u.userPassword,:u.userNickname," +
            "0,NOW(),NOW(),:u.delDate,:u.remark)")
    Integer addUser(@SQLParam("u")User u);


    /* *
     * @Author sunny
     * @Description  根据用户id删除用户(物理删除)
     * @Date 1:58 2019/5/5
     * @Param [id]
     * @return void
     **/
    @SQL("delete from t_user where id = :id")
    void delUserById(@SQLParam("id") Long id);

    /* *
     * @Author sunny
     * @Description  根据用户id删除用户(逻辑删除)
     * @Date 9:52 2019/5/5
     * @Param [id]
     * @return void
     **/
    @SQL("update t_user set del_date =NOW(),op_date=NOW(),del_tag=1 where id = :id")
    void delUserLogicById(@SQLParam("id") Long id);


    /* *
     * @Author sunny
     * @Description  修改用户信息
     * @Date 10:19 2019/5/5
     * @Param [u]
     * @return void
     **/
    @SQL("update t_user set id=:u.id #if(:u.userName!=null){,user_name=:u.userName}" +
            "#if(:u.userPassword!=null){,user_password=:u.userPassword}" +
            "#if(:u.userNickname!=null){,user_nickname=:u.userNickname}" +
            "#if(:u.remark!=null){,remark=u.remark}" +
            ",op_date=NOW() where id=:u.id")
    void updateUserById(@SQLParam("u") User u);



    /* *
     * @Author sunny
     * @Description  根据条件查询用户列表
     * @Date 11:53 2019/5/5
     * @Param [u, start, end]
     * @return java.util.List<User>
     **/
    @SQL("select id,user_name,user_password,user_nickname,del_tag,cr_date,op_date,del_date,remark" +
            " from t_user where 1=1 #if(:u.id!=null){ and id=:u.id}" +
            " #if(:u.userName!=null){ and user_name like CONCAT('%',:u.userName,'%')}" +
            " #if(:u.userNickname!=null){ and user_nickname like CONCAT('%',:u.userNickname,'%')}" +
            " order by cr_date desc" +
            " #if(:start>-1 && :end>-1){ limit :start,:end}")
    List<User> selectUserList(@SQLParam("u") User u,@SQLParam("start") Integer start,@SQLParam("end") Integer end);


    /* *
     * @Author sunny
     * @Description  根据条件查询用户数量
     * @Date 11:45 2019/5/5
     * @Param [u]
     * @return java.lang.Integer
     **/
    @SQL("select count(1)" +
            " from t_user where 1=1 #if(:u.id!=null){ and id=:u.id}" +
            " #if(:u.userName!=null){ and user_name like CONCAT('%',:u.userName,'%')}" +
            " #if(:u.userNickname!=null){ and user_nickname like CONCAT('%',:u.userNickname,'%')}")
    Integer selectUserCounts(@SQLParam("u") User u);
}