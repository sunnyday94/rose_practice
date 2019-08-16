/**
 * FileName: CommonDao Author:   sunnyday Date:     2019/4/28 17:40 Description: 公共dao History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rose.practice.dao.base;

import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈公共dao〉
 *
 * @author sunnyday
 * @create 2019/4/28
 * @since 1.0.0
 */
public interface CommonDAO<T> {

    @SQL("select $field from $table where id=:id")
    T byId(@SQLParam("id") long id);

    /**
     * 修改
     * 为了形成sql 方便 sql语句的实现都加了  update table set id=id...  ,"id=id"表明每个表都要有id字段。
     * @param modField  需要修改的字段 key为字段名，value为要改变的值 字段不能为null
     * @param condField 条件字段 key为字段名，value为要改变的值，该字段不能为null
     * @param cIsEmpty  默认写:false  为了防止condField为空 造成修改全表。在condField为true时可以修改全表
     * @return
     */
    @SQL("update $table set id=id "
            + "#for(key in :m.keySet()){ ,##(:key) = #(:m[:key]) }"
            + "#if(:c==null || :c.keySet().size()==0){"
            + " #if(:cIsEmpty){}#else{ where 1=2}"
            + "}#else{"
            + "	where 1=1  #for(key in :c.keySet()){ and ##(:key) = #(:c[:key]) }"
            + "}"
    )
    int update(@SQLParam("m") Map<String, Object> modField,
               @SQLParam("c") Map<String, Object> condField, @SQLParam("cIsEmpty") boolean cIsEmpty);




    /**
     * 分页
     *
     * @param condField 条件字段
     * @param start  开始数
     * @param size   每页个数
     * @param orderField  排序的字段
     * @param direction  正序(asc)，倒序(desc)
     * @return
     */
    @SQL("select $field from $table "
            + "#if(:c!=null || :c.keySet().size()>0){"
            + "	where 1=1  #for(key in :c.keySet()){ and ##(:key) = #(:c[:key]) }"
            + "}"
            + " order by ##(:orderField) ##(:direction)"
            + " limit :start,:size ")
    List<T> FindPage(@SQLParam("c") Map<String, Object> condField, @SQLParam("start") long start
            , @SQLParam("size") int size, @SQLParam("orderField") String orderField,
                     @SQLParam("direction") String direction);

    /**
     * 条件查询 总数量
     * @return
     */
    @SQL("select count(*) from $table "
            + "#if(:c!=null || :c.keySet().size()>0){"
            + "	where 1=1  #for(key in :c.keySet()){ and ##(:key) = #(:c[:key]) }"
            + "}")
    int FindCount(@SQLParam("c") Map<String, Object> condField);


    /**
     * 根据条件查找一个
     * @param condField
     * @return    条件为空 返回空
     */
    @SQL("select $field from $table "
            + "#if(:c!=null || :c.keySet().size()>0){"
            + "	where 1=1  #for(key in :c.keySet()){ and ##(:key) = #(:c[:key]) }"
            + "}#else{ where 1=2}")
    T findOne(@SQLParam("c") Map<String, Object> condField);

    /**
     * 根据条件查找
     * @param condField
     * @return   条件为空 返回空
     */
    @SQL("select $field from $table "
            + "#if(:c!=null || :c.keySet().size()>0){"
            + "	where 1=1  #for(key in :c.keySet()){ and ##(:key) = #(:c[:key]) }"
            + "}#else{ where 1=2}")
    List<T> find(@SQLParam("c") Map<String, Object> condField);

}
