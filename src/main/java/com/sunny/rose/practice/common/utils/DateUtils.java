/**
 * FileName: DateUtils Author:   sunnyday Date:     2019/4/28 19:27 Description: 时间工具类 History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 〈一句话功能简述〉<br> 
 * 〈时间工具类〉
 *
 * @author sunnyday
 * @create 2019/4/28
 * @since 1.0.0
 */
public class DateUtils {

    //private static SimpleDateFormat df = null;

    private final static String strPattern = "yyyy-MM-dd HH:mm:ss";
    private final static SimpleDateFormat df1 = new SimpleDateFormat(strPattern);
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    public static String dateToYMDHMS(Date date) {
        if (date == null) return null;
        return df1.format(date);
    }

    public static Date strYMDHMStoDate(String date) {
        if (date == null) return null;
        try {
            return df1.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回：yyyyMMddHHmmss格式
     */
    public static String toYMDHMS(Date date) {
        if (date == null) return null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(date);
    }

    /**
     * 返回：yyyyMM格式
     */
    public static String dateToYM(Date date) {
        if (date == null) return null;
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        return df.format(date);
    }

    public static String dateToYMD(Date date) {
        if (date == null) return null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static Date StrToDate(String str) {


        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date StrToDate(String str, String format) {
        if (StringUtils.isBlank(format))
            format = strPattern;
        else if (strPattern.equals(format)) {
            return strYMDHMStoDate(str);
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date jsonTodate(String json) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdf.setLenient(false);
        Date d = null;
        try {
            d = sdf.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String toString(Date date, String format) {
        if (date == null) return null;
        if (StringUtils.isBlank(format))
            format = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date getNow() {
        return new Date();
//		return System.currentTimeMillis();
    }

    public static String getNowForString() {
        SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
        return sdf.format(new Date());
    }

    public static boolean isDateString(String str, String formatString) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            format.parse(str);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isDateString(String str) {
        // TODO Auto-generated method stub
        return isDateString(str, "yyyy-MM-dd");
    }

    /**
     * 计算时间小时差
     *
     * @param sdate yyyy-MM-dd HH:mm:ss
     * @param edate yyyy-MM-dd HH:mm:ss
     */
    public static long balanceHour(String sdate, String edate) {

        long time = StrToDate(edate, "yyyy-MM-dd HH:mm:ss").getTime() - StrToDate(sdate, "yyyy-MM-dd HH:mm:ss").getTime();

        return time / (60 * 60 * 1000);
    }

    /**
     * 计算时间小时差
     */
    public static Float balanceHour(Date sdate, Date edate) {
        if (sdate == null || edate == null)
            return 0f;
        Long time = edate.getTime() - sdate.getTime();
        Float ftime = time.floatValue() / (60 * 60 * 1000);
        int scale = 2;//设置位数
        int roundingMode = 4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
        BigDecimal bd = new BigDecimal(ftime);
        bd = bd.setScale(scale, roundingMode);
        ftime = bd.floatValue();
        return ftime;
    }

    /**
     * 计算超时时数
     *
     * @param baseTime 基础超时时间 如8小时 8.5小时
     */
    public static Float OverHour(Date sdate, Date edate, Float baseTime) {

        Float time = balanceHour(sdate, edate);
        Float ov = time.floatValue() - baseTime;
        int scale = 2;//设置位数
        int roundingMode = 4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
        BigDecimal bd = new BigDecimal(ov);
        bd = bd.setScale(scale, roundingMode);
        ov = bd.floatValue();
        return ov < 0 ? 0f : ov;
    }

}