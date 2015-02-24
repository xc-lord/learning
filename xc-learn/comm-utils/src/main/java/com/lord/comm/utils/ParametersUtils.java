package com.lord.comm.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 方法参数检查的工具类
 * Created by lord on 2015/2/10.
 */
public class ParametersUtils {

    /**
     * 验证参数是否为空
     * @param params
     * @return 为空返回false，不为空返回true
     */
    public static boolean isNotEmpty(String... params) {
        if(params == null) {
            return false;
        }
        for (String param : params) {
            if (StringUtils.isEmpty(param)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查方法传入的参数是否正确，不能为空，为空则会抛出运行时异常
     * @param params
     */
    public static void isRequired(String... params) {
        if(! isNotEmpty(params)) {
            throw new RuntimeException("必要的参数为空，请检查方法传入的参数是否正确");
        }
    }

    /**
     * 检查参数不满足条件，则会抛出运行时异常
     * @param condition 检查条件
     * @param msg   异常消息
     */
    public static void checkParam(boolean condition, String msg) {
        if(! condition) {
            throw new RuntimeException(msg);
        }
    }

}
