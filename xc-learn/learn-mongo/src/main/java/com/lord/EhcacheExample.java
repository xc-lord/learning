package com.lord;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by xj_xiaocheng on 2015/1/6.
 */
public class EhcacheExample {

    public static void main(String[] args) {
        CacheManager cacheManager = CacheManager.create();

        Cache pidShardingInfoCache = cacheManager.getCache("pidShardingInfoCache");

        pidShardingInfoCache.put(new Element("pid1", "Sharding31"));
        pidShardingInfoCache.put(new Element("pid1", "Sharding1"));
        pidShardingInfoCache.put(new Element("pid2", "Sharding2"));
        pidShardingInfoCache.put(new Element("pid3", "Sharding3"));

        Element element = pidShardingInfoCache.get("pid1");
        System.out.println(element.getObjectKey());
        System.out.println(element.getObjectValue());
        System.out.println("pid1 = " + pidShardingInfoCache.get("pid1"));
    }
}
