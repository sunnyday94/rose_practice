/**
 * FileName: BaseBean Author:   sunnyday Date:     2019/5/8 17:51 Description: BaseBean History:
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
 * 〈BaseBean〉
 *
 * @author sunnyday
 * @create 2019/5/8
 * @since 1.0.0
 */
@Getter
@Setter
public class BaseBean implements Serializable {


    private static final long serialVersionUID = 4471239376740485729L;
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
