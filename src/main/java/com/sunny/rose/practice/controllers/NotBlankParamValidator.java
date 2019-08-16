/**
 * FileName: NotBlankParamValidator Author:   sunnyday Date:     2019/5/5 1:37 Description: 参数不为空验证
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.controllers;

import com.sunny.rose.practice.annotation.NotBlank;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.ParamValidator;
import net.paoding.rose.web.paramresolver.ParamMetaData;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈参数不为空验证〉
 *
 * @author sunnyday
 * @create 2019/5/5
 * @since 1.0.0
 */
public class NotBlankParamValidator implements ParamValidator {

    @Override
    public boolean supports(ParamMetaData paramMetaData) {
        return paramMetaData.getAnnotation(NotBlank.class)!=null;
    }


    /* *
     * @Author sunny
     * @Description
     *    paramMetaData里放的是参数的原型
          invocation是rose的基础调用
          target是这个参数的最后解析结果
          errors是这个参数解析时出来的错误
     * @Date 1:38 2019/5/5
     * @Param [paramMetaData, invocation, o, errors]
     * @return java.lang.Object
     **/
    @Override
    public Object validate(ParamMetaData paramMetaData, Invocation invocation, Object target, Errors errors) {
        String paramName = paramMetaData.getParamName();
        String value = invocation.getParameter(paramName);
        if(StringUtils.isBlank(value)){
            return "@参数不能为空!";
        }
        return null;
    }
}
