/**
 * FileName: UserVo Author:   sunnyday Date:     2019/4/29 1:46 Description: 用户业务对象 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.entity.vo;

import com.sunny.rose.practice.entity.BaseVO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户业务对象〉
 *
 * @author sunnyday
 * @create 2019/4/29
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends BaseVO {

    private static final long serialVersionUID = 7478963597281334819L;

    //主键id
    private Long id;

    //用户名
    private String userName;

    //用户密码
    private String userPassword;

    //用户昵称
    private String userNickname;

    //当前页
    private Integer pageIndex;

    //每页条目数
    private Integer pageSize;


    private String crDateStr;

    private String opDateStr;

    private String delDateStr;

}
