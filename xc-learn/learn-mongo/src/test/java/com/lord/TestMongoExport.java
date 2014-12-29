package com.lord;

import com.mongodb.*;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xj_xiaocheng on 2014/12/24.
 */
public class TestMongoExport {

    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;

    @Before
    public void init() {
        String host = "10.58.46.135";
        String port = "27067";
        String database = "appraise";
        String userName = "user_appraise";
        String password = "H3jn7UsKif5fLA8A";
        String collectionName = "goodsAppraise";

        try {
            //服务器和端口号
            ServerAddress addr = new ServerAddress(host, Integer.parseInt(port));
            //用户名和密码
            MongoCredential credential = MongoCredential.createMongoCRCredential(userName, database, password.toCharArray());

            mongoClient = new MongoClient(addr, Arrays.asList(credential));

            // 获取DB；如果默认没有创建，mongodb会自动创建
            db = mongoClient.getDB(database);
            // 获取users DBCollection；如果默认没有创建，mongodb会自动创建
            coll = db.getCollection(collectionName);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryProduct() throws Exception {
        DBObject query = new BasicDBObject();

        DBObject timeQuery = new BasicDBObject();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 11, 1, 0, 0, 0);
        timeQuery.put("$gte", new Date(calendar.getTimeInMillis()));
        calendar.set(2014, 11, 30, 0, 0, 0);
        timeQuery.put("$lt", new Date(calendar.getTimeInMillis()));

        query.put("postTime", timeQuery);
        query.put("isImport", "N");
        query.put("skuId", "pop8004247690");

        long count = coll.count(query);
        System.out.println("查询到的数量为：" + count);
    }

    @Test
    public void testQuery() throws Exception {
        DBObject query = new BasicDBObject();

        DBObject timeQuery = new BasicDBObject();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, 9, 25, 0, 0, 0);
        timeQuery.put("$gte", new Date(calendar.getTimeInMillis()));
        calendar.set(2014, 11, 25, 0, 0, 0);
        timeQuery.put("$lt", new Date(calendar.getTimeInMillis()));

        query.put("postTime", timeQuery);
        query.put("isImport", "N");
        
        long count = coll.count(query);
        System.out.println("查询到的数量为：" + count);

        //分页查询的数量
        int pageSize = 500;

        //查询的字段
        DBObject fields = new BasicDBObject();
        fields.put("productId", 1);
        fields.put("skuId", 1);
        fields.put("score", 1);
        fields.put("postTime", 1);

        File file =  new File("c:\\name.txt");

        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化日期
        List<String> list = new ArrayList<String>();
        int index = 0;
        while (index < count) {
            DBCursor cursor = coll.find(query, fields).skip(index).limit(pageSize);
            System.out.println("打印数据：" + index + "-" + (index+pageSize));
            for (DBObject dbObject : cursor) {
                String data = dbObject.get("productId") + "," + dbObject.get("skuId") + "," + dbObject.get("score") + "," + dateFm.format(dbObject.get("postTime"));
                list.add(data);
            }
            index += pageSize;
        }

        FileUtils.writeLines(file, list);
    }

    @Test
    public void testExport() throws Exception {
        DBObject query = new BasicDBObject();

        int day = 90;
        DBObject timeQuery = new BasicDBObject();
        Calendar calendar = Calendar.getInstance();
        timeQuery.put("$lt", new Date(calendar.getTimeInMillis()));
        calendar.add(Calendar.DATE, -day);
        timeQuery.put("$gte", new Date(calendar.getTimeInMillis()));


        query.put("postTime", timeQuery);
        query.put("isImport", "N");

        long totalCount = coll.count(query);
        System.out.println("查询到的数量为：" + totalCount);

        //分页查询的数量
        int pageSize = 500;

        //查询的字段
        DBObject fields = new BasicDBObject();
        fields.put("productId", 1);
        fields.put("skuId", 1);
        fields.put("score", 1);
        fields.put("postTime", 1);

        File file =  new File("c:\\newData.txt");

        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化日期

        Calendar calendarNow = Calendar.getInstance();
        for (int i = 1; i <= day; i++) {
            DBObject varQuery = new BasicDBObject();
            DBObject vraTimeQuery = new BasicDBObject();
            System.out.println("开始时间：" + dateFm.format(calendarNow.getTime()));
            vraTimeQuery.put("$lt", new Date(calendarNow.getTimeInMillis()));
            calendarNow.add(Calendar.DATE, -1);
            System.out.println("结束时间：" + dateFm.format(calendarNow.getTime()));
            vraTimeQuery.put("$gte", new Date(calendarNow.getTimeInMillis()));
            varQuery.put("postTime", vraTimeQuery);
            varQuery.put("isImport", "N");

            long count = coll.count(varQuery);
            System.out.println("正在导出" + dateFm.format(calendarNow.getTime()) + "的数据" + count);
            int index = 0;
            List<String> list = new ArrayList<String>();
            while (index < count) {
                DBCursor cursor = coll.find(varQuery, fields).sort(new BasicDBObject("postTime", 1)).skip(index).limit(pageSize);
                //System.out.println("打印数据：" + index + "-" + (index+pageSize));
                for (DBObject dbObject : cursor) {
                    String data = dbObject.get("productId") + "," + dbObject.get("skuId") + "," + dbObject.get("score") + "," + dateFm.format(dbObject.get("postTime"));
                    //System.out.println(data);
                    list.add(data);
                }
                index += pageSize;
            }
            FileUtils.writeLines(file, list, true);
        }

    }

}
