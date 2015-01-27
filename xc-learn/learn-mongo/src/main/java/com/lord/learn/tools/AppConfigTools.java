package com.lord.learn.tools;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xj_xiaocheng on 2015/1/8.
 */
public class AppConfigTools {
    protected Logger logger = LoggerFactory.getLogger(AppConfigTools.class);
    private Map<String, String> map;

    private AppConfigTools() {
        init();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void init() throws ExceptionInInitializerError {
        String fileName = "appConfig.properties";
        try {
            //读取配置文件
            Properties properties = readConfigFile(fileName);
            map = new HashMap<String, String>((Map) properties);
            for (String key : map.keySet()) {
                PropertyUtils.setSimpleProperty(this, key, map.get(key));
            }
        } catch (Exception e) {
            logger.error("读取配置文件:" + fileName + "出错:" + e.getMessage(), e);
            throw new ExceptionInInitializerError("初始化应用通用配置出错");
        }
    }

    private static class SingletionHolder {
        private static AppConfigTools appConfigTools = new AppConfigTools();
    }

    public static AppConfigTools getInstance() {
        return SingletionHolder.appConfigTools;
    }

    /**
     * 读取配置文件
     * @param filePath  配置文件路径（默认搜寻路径为classpath路径下）
     * @return  返回配置属性键值对
     */
    public static Properties readConfigFile(String filePath) throws java.io.IOException {
        Properties property = new Properties();
        property.load(AppConfigTools.class.getClassLoader().getResourceAsStream(filePath));
        return property;
    }
}
