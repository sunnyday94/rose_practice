/**
 * FileName: User Author:   sunnyday Date:     2019/4/28 18:01 Description: 用户类 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.entity.metadata;

import com.sunny.rose.practice.entity.BaseBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户类〉
 *
 * @author sunnyday
 * @create 2019/4/28
 * @since 1.0.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseBean {
    private static final long serialVersionUID = -8929360818301496892L;

    //主键id
    private Long id;

    //用户名
    private String userName;

    //用户密码
    private String userPassword;

    //用户昵称
    private String userNickname;

    //逻辑删除标记:0:未删除;1:已删除
    private Integer delTag;


}
