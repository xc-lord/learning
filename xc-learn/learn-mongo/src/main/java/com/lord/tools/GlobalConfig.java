package com.lord.tools;

/**
 * Created by xj_xiaocheng on 2015/1/8.
 */
public class GlobalConfig {

    private static String env;

    /**
     * 私有化构造函数，由Spring进行构造
     */
    private GlobalConfig() {
    }

    public void setEnv(String env) {
        GlobalConfig.env = env;
    }

    public static String getEnv() {
        return env;
    }
}
