/**
 * FileName: BaseController
 * Author:   sunny
 * Date:     2018/8/24 15:45
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.controllers.base;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunny.rose.practice.common.ResBean;
import com.sunny.rose.practice.common.utils.BaseUtils;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseController {



    /* *
     * @Author sunny
     * @Description  成功返回(200)
     * @Date 16:55 2019/8/16
     * @Param [message, data]
     * @return java.lang.String
     **/
    protected String getResBeanSuccess(String message,Object data) {
        ResBean resBean = new ResBean();
        resBean.setMsg(message);
        resBean.setData(data);
        return "@json:"+ JSONObject.toJSONString(resBean);
    }


    /* *
     * @Author sunny
     * @Description  成功返回(200)
     * @Date 3:36 2019/5/4
     * @Param []
     * @return java.lang.String
     **/
    protected String getResBeanSuccess(String message) {
        ResBean resBean = new ResBean();
        resBean.setMsg(message);
        return "@json:"+ JSONObject.toJSONString(resBean);
    }


    
    /* *
     * @Author sunny
     * @Description  返回异常(500)
     * @Date 3:37 2019/5/4
     * @Param [message]
     * @return java.lang.String
     **/
    protected  String getResBeanError(String message){
        ResBean resBean = new ResBean();
        resBean.setCode(500);
        resBean.setMsg(message);
        return "@error:"+JSONObject.toJSONString(resBean);
    }



    /* *
     * @Author sunny
     * @Description  执行成功返回分页对象
     * @Date 3:38 2019/5/4
     * @Param [pageInfo]
     * @return java.lang.String
     **/
    public String getResBean(ResBean.PageInfo pageInfo) {
        ResBean.PageInfo page=new ResBean().istansPageInfo();
        BaseUtils.copyBeanNullInvalid(pageInfo, page);
        return "@json:"+ JSONObject.toJSONString(page);
    }


}