package com.lord.controller;

import com.lord.comm.utils.ParametersUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lord on 2015/2/11.
 */
@Controller
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    /***
     * 用户登陆
     * <p>注解配置，只允许POST提交到该方法
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="login",method= RequestMethod.POST)
    public ModelAndView login(String username,String password){
        //验证传递过来的参数是否正确，否则返回到登陆页面。
        if(ParametersUtils.isNotEmpty(username, password)){
            //指定要返回的页面为succ.jsp
            ModelAndView mav = new ModelAndView("succ");
            //将参数返回给页面
            mav.addObject("username",username);

            return mav;
        }
        return new ModelAndView("home");
    }
}
