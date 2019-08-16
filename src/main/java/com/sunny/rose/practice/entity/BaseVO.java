/**
 * FileName: BaseVO Author:   sunnyday Date:     2019/5/8 17:50 Description: BaseVO History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈BaseVO〉
 *
 * @author sunnyday
 * @create 2019/5/8
 * @since 1.0.0
 */
@Getter
@Setter
public class BaseVO implements Serializable {

    private static final long serialVersionUID = -247731896334334105L;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crDate;

    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date opDate;

    //删除时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date delDate;

    //备注
    private String remark;

    //逻辑删除标记:0:未删除;1:已删除
    private Integer delTag;

}
