package com.lord.learn;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class TestMongoSpring {

	@Test
	public void test() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
			MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
			//mongoTemplate.setReadPreference(ReadPreference.secondaryPreferred());
			
			DBCollection coll = mongoTemplate.getCollection("test_prd");
			
			//testQuery(coll);
			
			testWrite(coll);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private void testQuery(DBCollection coll) throws InterruptedException {
		for (int i = 0; i < 10000; i++) 
		{
			try{
				 DBObject obj = coll.findOne();
				 System.out.println(obj);
			} catch(Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(1000); 
		}
	}
	
	public void testWrite(DBCollection coll) throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			try{
				BasicDBObject obj = new BasicDBObject("name", "insert_" + i);
				//System.out.println("start:" + obj);
				coll.save(obj);
				//System.out.println("end:" + obj);
			} catch (Exception e) {
				System.err.println("error");
				e.printStackTrace();
			}
			//Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		System.err.println("所用时间: " + (end - start));
	}
}
