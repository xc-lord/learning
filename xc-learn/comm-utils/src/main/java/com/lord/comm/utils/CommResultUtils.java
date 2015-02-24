package com.lord.comm.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 方法返回结果的公共封装方法工具类
 * Created by lord on 2015/2/24.
 */
public class CommResultUtils {

    /**
     * 如果回调函数名不为空，将Json格式的结果转换为Jsonp格式
     * @param callback  回调函数名
     * @param result    Json格式的字符串
     * @return  Jsonp格式字符串
     */
    public static String toJsonpString(String callback, String result) {
        if(StringUtils.isNotEmpty(callback)) {
            result = callback + "(" + result + ")";
        }
        return result;
    }

    /**
     * 如果回调函数名不为空，将Json格式的结果转换为Jsonp格式
     * @param callback  回调函数名
     * @param json    Json格式的结果
     * @return  Jsonp格式字符串
     */
    public static String toJsonpString(String callback, JSONObject json) {
        if(json == null)
            return null;
        return toJsonpString(callback, json.toJSONString());
    }
}
