package com.lord.service.impl;

import com.lord.service.ISimpleService;

/**
 * Created by xj_xiaocheng on 2015/1/12.
 */
public class SimpleServiceImpl implements ISimpleService {

    @Override
    public String getMsg(String msg) {
        System.out.println("服务端接收到的消息：" + msg);
        return "服务端返回：" + msg + "_server";
    }

}
