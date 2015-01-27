package com.lord.learn;

import java.net.UnknownHostException;
import java.util.Date;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.QueryOperators;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class TestMongoCRUD {
	private MongoClient mongo = null;
	private DB db;
	private DBCollection testData;

	@Before
	public void init() {
		try {
			//服务器和端口号
			ServerAddress addr = new ServerAddress("10.126.53.191", 27017);
			mongo = new MongoClient(addr);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		// 获取gome DB；如果默认没有创建，mongodb会自动创建
		db = mongo.getDB("gome");
		db.authenticate("", "".toCharArray());
		// 获取users DBCollection；如果默认没有创建，mongodb会自动创建
		testData = db.getCollection("testData");
	}

	@After
	public void destory() {
		if (mongo != null)
			mongo.close();
		mongo = null;
		db = null;
		testData = null;
		System.gc();
	}

	public void print(Object o) {
		System.out.println(o);
	}

	@Test
	public void displayAll() {
		print("testData共有" + testData.count() + "条记录，数据如下：");
		
		DBCursor cur = testData.find();
		while (cur.hasNext()) {
			print(cur.next());
		}
	}
	
	@Test
	public void show() {
		displayAll();
	}

	@Test
	public void add() {	
		
		DBObject user = new BasicDBObject();
		user.put("name", "jack");
		user.put("age", 10);
		user.put("sex", "男");
		user.put("createTime", new Date());
		testData.save(user);
		
		for (int i = 0; i < 10; i++) {
			DBObject user1 = new BasicDBObject();
			user1.put("name", "lord");
			user1.put("age", 18);
			user1.put("sex", "男");
			user1.put("createTime", new Date());
			testData.insert(user1);
		}
		
		displayAll();
	}

	@Test
	public void remove() {
		
		print("删除id = 521887021e76d757f3449000："
				+ testData.remove(
						new BasicDBObject("_id", new ObjectId("521887021e76d757f3449000"))).getN());
		print("remove age >= 100: "
				+ testData.remove(new BasicDBObject("age", new BasicDBObject("$gte", 99)))
						.getN());
		print("remove name = lord2: "
				+ testData.remove(new BasicDBObject("name", "lord2"))
				.getN());
		displayAll();
	}

	@Test
	public void modify() {
		DBObject queryObj = new BasicDBObject("name", "lord51");
		
		DBObject u_obj = testData.findOne(queryObj);
		u_obj.put("sex", "女");
		u_obj.put("age", 24);
		
		print("修改" + queryObj + "的记录："
				+ testData.update(
						queryObj,
						u_obj).getN());
		/*
		print("修改" + queryObj + "的记录："
				+ testData.update(
						queryObj,
						new BasicDBObject("age", 121), 
						true,// 如果数据库不存在，是否添加
						false// 多条修改
				).getN());
		print("修改" + queryObj + "的记录："
				+ testData.update(new BasicDBObject("name", "jack541"),
						new BasicDBObject("name", "dingding"), true,// 如果数据库不存在，是否添加
						true// false只修改第一天，true如果有多条就不修改
				).getN());
		*/
		displayAll();
	}

	@Test
	public void query() {
		// 查询所有
		// queryAll();
		// 查询id = 521889701e76cf5d297d032d
		print("find id = 521889701e76cf5d297d032d: "
				+ testData.find(
						new BasicDBObject("_id", new ObjectId(
								"521889701e76cf5d297d032d"))).toArray());
		// 查询age = 24
		print("find age = 24: "
				+ testData.find(new BasicDBObject("age", 24)).toArray());
		// 查询age >= 24
		print("find age >= 24: "
				+ testData.find(
						new BasicDBObject("age", new BasicDBObject("$gte", 24)))
						.toArray());
		print("find age <= 24: "
				+ testData.find(
						new BasicDBObject("age", new BasicDBObject("$lte", 24)))
						.toArray());
		print("查询age!=24："
				+ testData.find(
						new BasicDBObject("age", new BasicDBObject("$ne", 24)))
						.toArray());
		print("查询age in 25/26/27："
				+ testData.find(
						new BasicDBObject("age", new BasicDBObject(
								QueryOperators.IN, new int[] { 25, 26, 27 })))
						.toArray());
		print("查询age not in 25/26/27："
				+ testData.find(
						new BasicDBObject("age", new BasicDBObject(
								QueryOperators.NIN, new int[] { 25, 26, 27 })))
						.toArray());
		print("查询age exists 排序："
				+ testData.find(
						new BasicDBObject("age", new BasicDBObject(
								QueryOperators.EXISTS, true))).toArray());
		print("只查询age属性："
				+ testData.find(null, new BasicDBObject("age", true)).toArray());

		// 只查询一条数据，多条去第一条
		print("findOne: " + testData.findOne());
		print("findOne: " + testData.findOne(new BasicDBObject("age", 26)));
		print("findOne: "
				+ testData.findOne(new BasicDBObject("age", 26),
						new BasicDBObject("name", true)));
		// 查询修改、删除
		print("findAndRemove 查询age=25的数据，并且删除: "
				+ testData.findAndRemove(new BasicDBObject("age", 25)));
		// 查询age=26的数据，并且修改name的值为Abc
		print("findAndModify: "
				+ testData.findAndModify(new BasicDBObject("age", 26),
						new BasicDBObject("name", "Abc")));
		print("findAndModify: "
				+ testData.findAndModify(new BasicDBObject("age", 39), // 查询age=39的数据
						new BasicDBObject("name", true), // 查询name属性
						new BasicDBObject("age", true), // 按照age排序
						false, // 是否删除，true表示删除
						new BasicDBObject("name", "Abc2"), // 修改的值，将name修改成Abc2
						true, true));
		
		//testData.findAndModify(query, fields, sort, remove, update, returnNew, upsert);
		displayAll();
	}

	public void testOthers() {
		DBObject user = new BasicDBObject();
		user.put("name", "hoojo");
		user.put("age", 24);
		// JSON 对象转换
		print("serialize: " + JSON.serialize(user));
		// 反序列化
		print("parse: " + JSON.parse("{ \"name\" : \"hoojo\" , \"age\" : 24}"));
		print("判断temp Collection是否存在: " + db.collectionExists("temp"));
		// 如果不存在就创建
		if (!db.collectionExists("temp")) {
			DBObject options = new BasicDBObject();
			options.put("size", 20);
			options.put("capped", 20);
			options.put("max", 20);
			print(db.createCollection("account", options));
		}
		// 设置db为只读
		db.setReadOnly(true);
		// 只读不能写入数据
		db.getCollection("test").save(user);
	}
}
