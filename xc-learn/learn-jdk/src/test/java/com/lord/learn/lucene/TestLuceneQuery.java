package com.lord.learn.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestLuceneQuery {
	public static final String indexPath = "D:/temp/index";

	public static void main(String[] args) {		
        test();
        String relativelyPath=System.getProperty("user.dir");
		System.out.println(relativelyPath);
	}

	private static void test2(){
		try {
			/**获取IndexSearcher*/
	        IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexPath)));
	        IndexSearcher searcher = new IndexSearcher(reader);
	        
	        /**搜索条件*/
	        //QueryParser parser = new QueryParser(VERSION_36, fields[0], analyzer);
	        QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_36, new String[]{"body"}, new IKAnalyzer());
	        Query query = parser.parse("中华");
	        System.out.println("Searching for: " + query.toString());
	        
	        TopDocs results = searcher.search(query, 5);
	        //TopDocs results = searcher.search(query, 5, sort);
	        ScoreDoc[] hits = results.scoreDocs;
	        System.out.println("results.totalHits:"+ results.totalHits);
	        System.out.println("hits.length:"+ hits.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void test() {
		File indexPath = new File("D:/temp/index");
		Directory directory;
		try {
			System.out.println("Begin to search");
			directory = FSDirectory.open(indexPath);
			IndexReader indexReader = IndexReader.open(directory );
			IndexSearcher searcher = new IndexSearcher(indexReader);
			ScoreDoc[] hits = null;
			String queryString = "国美在线";
			//String queryString = "+(body:中国人 )";
			Query query = null;
			Analyzer analyzer = new IKAnalyzer();
			QueryParser queryParser = new QueryParser(Version.LUCENE_36, "body", analyzer);
			query = queryParser.parse(queryString);
			System.out.println("分词结果：" + query.toString());
			if(searcher != null) {
				TopDocs results = searcher.search(query, 5);
				hits = results.scoreDocs;
				System.out.println(hits.length);
				if(hits.length > 0) {
					System.out.println("找到：" + hits.length + " 个结果");
					for (ScoreDoc scoreDoc : hits) {
						Document doc = searcher.doc(scoreDoc.doc);
						System.out.println(scoreDoc.score + "\tpath:"+doc.get("path")+"\tbody:"+doc.get("body") + doc.get("modified"));
					}
				}
				searcher.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
