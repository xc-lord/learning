package com.lord.controller;

import com.alibaba.fastjson.JSONObject;
import com.lord.comm.utils.CommResultUtils;
import com.lord.comm.utils.Log4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志级别修改
 * Created by lord on 2015/2/10.
 */
@Controller
public class LoggerController {
    protected Logger logger = LoggerFactory.getLogger(LoggerController.class);

    @RequestMapping("loggerInfo")
    public ModelAndView loggerInfo(String loggerName) {
        if(StringUtils.isEmpty(loggerName))
            loggerName = "com.lord";

        ModelAndView mav = new ModelAndView("logger");
        JSONObject loggerInfo = Log4jUtils.listLoggerInfo(loggerName);
        mav.addObject("loggerInfo", loggerInfo);

        logger.debug("显示" + loggerName + "日志级别信息。");
        return mav;
    }

    @RequestMapping("changeLoggerLevel")
    @ResponseBody
    public String changeLoggerLevel(String loggerName, String level, String callback) {
        JSONObject json = Log4jUtils.changeLoggerLevel(loggerName, level);
        String result = CommResultUtils.toJsonpString(callback, json);

        logger.debug("修改类的日志级别返回消息：" + result);
        return result;
    }

}
