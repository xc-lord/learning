package com.lord.controller;

import com.alibaba.fastjson.JSONObject;
import com.lord.comm.utils.Log4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;

/**
 * Created by lord on 2015/2/10.
 */
@Controller
public class HomeController {
    protected Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * 首页 返回至/page/home.jsp页面
     *
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        //创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

}
