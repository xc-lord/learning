package com.lord.comm.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Enumeration;

/**
 * 动态修改类的日志级别的工具类
 * Created by lord on 2015/2/16.
 */
public class Log4jUtils {

    /**
     * 修改日志的级别
     * @param loggerName    类的全名或包名
     * @param level         日志等级：DEBUG、INFO、WARN、ERROR
     * @return
     */
    public static JSONObject changeLoggerLevel(String loggerName, String level) {
        JSONObject jsonObject = new JSONObject();
        Logger logger = LogManager.exists(loggerName);
        String before = null;
        String result = null;
        String msg = null;
        if (logger == null) {
            msg = "类：" + logger.getName() + "的日志不存在。";
            System.out.println(msg);
            jsonObject.put("exits", false);
            jsonObject.put("msg", msg);
            return jsonObject;
        }

        before = getLoggerLevel(logger);
        logger.setLevel(Level.toLevel(level));
        result = getLoggerLevel(logger);

        msg = "将类：" + logger.getName() + "的日志级别从" + before + "修改为" + result;
        System.out.println(msg);

        jsonObject.put("exits", true);
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    /**
     *  查询一个包下面所有类的日志级别
     * @param loggerNamePrefix  包名或是类的前缀
     * @return
     */
    public static JSONObject listLoggerInfo(String loggerNamePrefix) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<Logger> currentLoggers = LogManager.getCurrentLoggers();
        int count = 0;
        int subCount = 0;
        JSONArray jsonArray = new JSONArray();
        while (currentLoggers.hasMoreElements()) {
            Logger logger = currentLoggers.nextElement();
            if (logger.getName().startsWith(loggerNamePrefix)) {
                String level = getLoggerLevel(logger);
                JSONObject json = new JSONObject();
                json.put("className", logger.getName());
                json.put("loggerLevel", level);
                jsonArray.add(json);
                subCount++;
            }
            count++;
        }
        jsonObject.put("total", count);
        jsonObject.put("list", jsonArray);
        jsonObject.put("count", subCount);
        jsonObject.put("packageName", loggerNamePrefix);
        return jsonObject;
    }

    private static String getLoggerLevel(Logger logger) {
        return logger.getLevel() == null ? "默认" : logger.getLevel().toString();
    }

}
