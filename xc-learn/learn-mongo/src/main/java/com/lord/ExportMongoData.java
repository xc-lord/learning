package com.lord;

import com.mongodb.*;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by xj_xiaocheng on 2014/12/31.
 */
public class ExportMongoData {
    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;

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

    public void export() {
        String fileName = "D:/temp/orderAppraise.txt";
        try {
            InputStream input = new FileInputStream(new File(fileName));
            List<String> list = IOUtils.readLines(input);
            for (String s : list) {
                //System.out.println("->" + s);
                String orderId = s;
                DBObject query = new BasicDBObject();

                DBObject timeQuery = new BasicDBObject();
                Calendar calendar = Calendar.getInstance();
                calendar.set(2014, 11, 1, 0, 0, 0);
                timeQuery.put("$gte", new Date(calendar.getTimeInMillis()));

                query.put("postTime", timeQuery);
                query.put("isImport", "N");
                query.put("orderId", orderId);

                long count = coll.count(query);
                if(count == 0) {
                    //System.out.println(orderId + "--查询到的数量为：" + count);
                    System.out.println(orderId);
                }

                DBCursor cursor = coll.find(query);
                for (DBObject object : cursor) {
                    String productId = (String) object.get("productId");
                    String skuId = (String) object.get("skuId");
                    String userId = (String) object.get("postUser");
                    String vorderId = (String) object.get("orderId");
                    String content = (String) object.get("summary");
                    int score = (Integer) object.get("score");

                    System.out.println(vorderId + "\t" + userId + "\t" + productId + "\t" + skuId + "\t" + score + "\t" + content);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExportMongoData exportMongoData = new ExportMongoData();
        exportMongoData.init();
        exportMongoData.export();
    }
}
