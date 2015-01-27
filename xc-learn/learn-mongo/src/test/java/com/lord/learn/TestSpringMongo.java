package com.lord.learn;

import com.lord.learn.tools.GlobalConfig;
import com.lord.learn.tools.SpringTools;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by xj_xiaocheng on 2015/1/8.
 */
public class TestSpringMongo {

    private MongoTemplate mongoTemplate;

    @Before
    public void init() {
        mongoTemplate = SpringTools.getContext().getBean(MongoTemplate.class);
    }

    @Test
    public void test() {
        DBCollection coll = mongoTemplate.getCollection("goodsAppraise");
        DBObject query = new BasicDBObject("productId", "9100017390");
        DBCursor cursor = coll.find(query).limit(10);
        for (DBObject object : cursor) {
            System.out.println(object);
        }
    }

    @Test
    public void testConfig() {
        System.out.println("当前环境是：" + GlobalConfig.getEnv());
    }
}
