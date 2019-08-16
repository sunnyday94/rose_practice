/**
 * FileName: ResultCodeEnum Author:   sunnyday Date:     2019/4/28 17:33 Description: 错误消息枚举 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.common.enums;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br> 
 * 〈错误消息枚举〉
 *
 * @author sunnyday
 * @create 2019/4/28
 * @since 1.0.0
 */
public enum ResultCodeEnum {
    成功(0, 200, "succeed"),
    未定义状态码(1, 503, "unknow error"),
    系统异常(2, 500, "service error"),
    参数异常(3, 400, "paramter error"),
    版本号无效(4, 403, "unknow version"),
    请求的资源不存在(5, 404, "not found"),
    跳转登陆(6,401,"go to login"),
    上传的文件不合法(7,400,"file illegality"),
    上传文件失败(8,500,"file error"),
    数据已存在(10000,200,"existed");

    public final Integer flag;
    public final Integer httpStatus;
    public final String defaultMsg;

    ResultCodeEnum(Integer flag,Integer httpStatus, String defaultMsg){
        this.flag   = flag;
        this.httpStatus = httpStatus;
        this.defaultMsg = defaultMsg;
    }

    public Integer getFlag() {
        return flag;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    /* *
     * @Author sunny
     * @Description  根据flag获取枚举
     * @Date 17:35 2019/4/28
     * @Param [code]
     * @return ResultCodeEnum
     **/
    public static ResultCodeEnum getErrorEnumByCode(Integer code) {
        return Arrays.stream(ResultCodeEnum.values()).filter(line -> line.getFlag().equals(code)).findAny()
                .orElseThrow(() -> new RuntimeException("未知的提示信息！ code:" + code));
    }


    /* *
     * @Author sunny
     * @Description  根据flag获取枚举信息
     * @Date 17:36 2019/4/28
     * @Param [code]
     * @return java.lang.String
     **/
    public static String getMessageByCode(Integer flag) {
        for (ResultCodeEnum e : values()) {
            if (flag.equals(e.getFlag()))
                return e.getDefaultMsg();
        }
        return null;
    }
}
