package com.sunny.rose.practice.utils;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

/**
 * http client 工具类
 * @author sunny
 */
@Slf4j
public class HttpClientUtil {
    private static final  String CHARSET = "UTF-8";

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectionRequestTimeout(2000).setConnectTimeout(2000)
            .setSocketTimeout(2000).build();
    private HttpClientUtil(){}
    /**
     * GET请求
     *
     * @param url 请求地址
     * @return
     */
    public static String get(String url) {
        if (null == url || url.isEmpty()) {
            return null;
        }
        return get(url, null,CHARSET);
    }
    /**
     * GET请求设置charset
     * @param url 请求地址
     * @param charset 编码
     * @return
     */
    public static String get(String url,String charset) {
        if (null == url || url.isEmpty()) {
            return null;
        }
        return get(url, null,charset);
    }
    
    /**
     * 带参数的GET请求
     * 
     * @param url 请求地址
     * @param params 参数列表
     * @return
     */
    public static String get(String url, List<NameValuePair> params, String charset) {
        if (null == url || url.isEmpty()) {
            return null;
        }
        String body = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(REQUEST_CONFIG);
            // 设置参数
            if (null != params && !params.isEmpty()) {
                String str = EntityUtils.toString(new UrlEncodedFormEntity(params,charset));
                if (url.contains("?")) {
                    httpget.setURI(new URI(url + "?" + str));
                } else {
                    httpget.setURI(new URI(url + "&" + str));
                }
            }
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity,charset);
            httpClient.close();
            EntityUtils.consume(entity);
        } catch (ParseException | IOException e) {
            log.error("["+url+"]ParseException | IOException", e);
        } catch (URISyntaxException e) {
            log.error("["+url+"]URISyntaxException", e);
        }
        return body;
    }
    
    /**
     * POST
     * @param url 请求地址
     * @param params 参数列表
     * @return
     */
    public static String post(String url, List<NameValuePair> params){
        if (null == url || url.isEmpty()) {
            return null;
        }
        return post(url, params,CHARSET);
    }
    /**
     * POST
     * @param url 请求地址
     * @param params map参数列表
     * @return
     */
    public static String post(String url, Map<String, String> params){
        if (null == url || url.isEmpty()) {
            return null;
        }
        ArrayList<NameValuePair> list=new ArrayList<>();
        if(null!= params && !params.isEmpty()){
            Iterator<Entry<String, String>> iter= params.entrySet().iterator();
            while(iter.hasNext()){
                Entry<String,String> entry=iter.next();
                BasicNameValuePair nv=new BasicNameValuePair(entry.getKey(), entry.getValue());
                list.add(nv);
            }
        }
        return post(url, list,CHARSET);
    }
    /**
     * POST
     * @param url 请求地址
     * @param charset 编码
     * @return
     */
    public static String post(String url, String charset){
        if (null == url || url.isEmpty()) {
            return null;
        }
        return post(url, null,charset);
    }
    /**
     * POST
     * @param url 请求地址
     * @param params 参数列表
     * @param charset 编码
     * @return
     */
    public static String post(String url, List<NameValuePair> params, String charset) {
        if (null == url || url.isEmpty()) {
            return null;
        }
        String body = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            httppost.setConfig(REQUEST_CONFIG);
            //提交数据
            if(null!= params && !params.isEmpty()){
                UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, charset);
                httppost.setEntity(uefEntity);
            }
            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httppost);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            body = EntityUtils.toString(entity,charset);
            httpClient.close();
            EntityUtils.consume(entity);
        } catch (UnsupportedEncodingException e) {
            log.error("["+url+"]UnsupportedEncodingException",e);
        } catch (ClientProtocolException e) {
            log.error("["+url+"]ClientProtocolException",e);
        } catch (IOException e) {
            log.error("["+url+"]IOException",e);
        }
        return body;
    }



    /* *
     * @Author sunny
     * @Description  httpPut请求
     * @Date 13:57 2019/5/15
     * @Param [url, jsonParam, headers]
     * @return java.lang.String
     **/
    public static String httpWithPut(String url, JSONObject jsonParam, Map<String,String> headers) {
        log.info(String.format("==========================put请求的地址为:%s ===========================",url));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(REQUEST_CONFIG);
        if (headers != null && headers.size()>0) {
            for (Entry<String,String> header : headers.entrySet()) {
                httpPut.setHeader(header.getKey(), header.getValue());
            }
        }
        try {
            if (null != jsonParam && !jsonParam.isEmpty()) {
                StringEntity entity = new StringEntity(jsonParam.toString(), CHARSET);
                entity.setContentEncoding(CHARSET);
                entity.setContentType(ContentType.APPLICATION_JSON.getCharset().toString());
                httpPut.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPut);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str;
                try {
                    // 读取服务器返回过来的json字符串数据
                    str = EntityUtils.toString(result.getEntity(), CHARSET);
                    return str;
                } catch (Exception e) {
                    log.error("put请求提交失败:" + url, e);
                }
            }
        }catch (UnsupportedCharsetException ue){
            log.error("UnsupportedCharsetException:",ue);
        } catch (IOException e) {
            log.error("put请求提交失败:" + url, e);
        }finally{
            try {
                httpClient.close();
            } catch (IOException e) {
               log.error("httpClient关闭异常:{}",e.getMessage(),e);
            }
        }
        return null;
    }


    /* *
     * @Author sunny
     * @Description  httpDelete请求
     * @Date 14:28 2019/5/15
     * @Param [url, headers]
     * @return com.alibaba.fastjson.JSONObject
     **/
    public static String httpWithDelete(String url, Map<String,String> headers){
        log.info(String.format("==========================delete请求的地址为:%s ===========================",url));
        // delete请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 发送delete请求
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(REQUEST_CONFIG);
        if (headers != null && headers.size()>0) {
            for (Entry<String,String> header : headers.entrySet()) {
                httpDelete.setHeader(header.getKey(), header.getValue());
            }
        }
        ResponseHandler<String> responseHandler = response -> {
            int responseCode  = response.getStatusLine().getStatusCode();
            String reasonPhrase = response.getStatusLine().getReasonPhrase();
            //判断响应状态吗
            if (responseCode == HttpStatus.SC_OK) {
                // 读取服务器返回的entity
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            }else{
                log.error(String.format("delete请求响应失败,响应码:%d,信息:%s",responseCode,reasonPhrase));
                return null;
            }
        };
        String responseContent=null;
        try {
            responseContent =  httpClient.execute(httpDelete, responseHandler);
            log.info(String.format("请求地址为%s的响应结果是:%s",url,responseContent));
        } catch (IOException e) {
            log.error("请求地址为{}的响应结果异常:{}",url,e.getMessage());
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("httpClient关闭异常:{}", e.getMessage(), e);
            }
        }
        return responseContent;
    }


    /* *
     * @Author sunny
     * @Description  httpPut
     * @Date 15:47 2019/5/15
     * @Param [url, param]
     * @return java.lang.String
     **/
    public static String httpWithPut(String url,String param) {
        log.info(String.format("==========================put请求的地址为:%s ===========================",url));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(REQUEST_CONFIG);
        try {
            if (StringUtils.isNotBlank(param)) {
                StringEntity entity = new StringEntity(param, CHARSET);
                entity.setContentEncoding(CHARSET);
                entity.setContentType("application/json");
                httpPut.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPut);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    // 读取服务器返回过来的json字符串数据
                    return  EntityUtils.toString(result.getEntity(), "utf-8");
                } catch (Exception e) {
                    log.error("put请求提交失败:" + url, e);
                }
            }
        }
        catch (UnsupportedCharsetException ue){
            log.error("UnsupportedCharsetException:",ue);
        }catch (IOException e) {
            log.error("put请求提交失败:" + url, e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
               log.error("httpClient关闭异常:{}", e.getMessage(),e);
            }
        }
        return null;
    }
}