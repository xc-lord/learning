package com.lord.learn.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.lord.utils.NumberUtils;

public class TestLucene {

	private static final String INDEX_DIR = "D:/temp/index";
	private static final String FILE_DIR = "E:/360Downloads";

	@Test
	public void testReadFile() {
		List<FileProperty> list = queryFileSystem(FILE_DIR);
		
		for (FileProperty fileProperty : list) {
			System.out.println(fileProperty);
		}
	}
	
	@Test
	public void writeIndex() {
		File indexDir = new File(INDEX_DIR);
		try {
			Directory dirIndex = FSDirectory.open(indexDir);//索引存放目录			
			Analyzer luceneAnalyzer = new IKAnalyzer();//词法分析器		
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36, luceneAnalyzer);
			iwc.setOpenMode(OpenMode.CREATE);//如果已经有索引文件在索引目录下，我们将覆盖它们
			
			IndexWriter indexWriter = new IndexWriter(dirIndex,iwc);//索引写入工具
			
			Date start = new Date();
			
			List<FileProperty> list = queryFileSystem(FILE_DIR);
			for (FileProperty fileProperty : list) {
				System.out.println(fileProperty);
				//信息域
				Field dirField = new Field("dir", fileProperty.getDir(), Field.Store.YES, Field.Index.ANALYZED_NO_NORMS);
				
				Field fileNameField = new Field("fileName", fileProperty.getName(), Field.Store.YES, Field.Index.ANALYZED);	//只索引不存储
				fileNameField.setBoost(1.2f);	//设置权重
				
				NumericField modifiedField = new NumericField("modifTime", Field.Store.YES, true);
		        modifiedField.setLongValue(fileProperty.getModifTime().getTime());
		        
		        //添加到文档
		        Document document = new Document();
		        document.add(dirField);
		        document.add(fileNameField);
		        document.add(modifiedField);
		        //写入索引
		        indexWriter.addDocument(document);
			}
			indexWriter.close();
			
			Date end = new Date();
			System.out.println((new StringBuilder())
					.append(end.getTime() - start.getTime())
					.append(" total milliseconds").toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryFromIndex() {
		String keyword = "播放器";
		try {
			/**获取IndexSearcher*/
	        IndexReader reader = IndexReader.open(FSDirectory.open(new File(INDEX_DIR)));
	        IndexSearcher searcher = new IndexSearcher(reader);
	        
	        /**搜索条件*/
	        //QueryParser parser = new QueryParser(VERSION_36, fields[0], analyzer);
	        QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_36, new String[]{"fileName"}, new IKAnalyzer());
	        
			Query query = parser.parse(keyword);
	        System.out.println("Searching for: " + query.toString());
	        
	        ScoreDoc[] hits = null;
	        if(searcher != null) {
	        	//按多少值排序
	        	SortField[] sortFields = new SortField[] { SortField.FIELD_SCORE, new SortField("modifTime", SortField.LONG, false) };
	        	Sort sort = new Sort(sortFields);
				TopDocs results = searcher.search(query, 10, sort);
				hits = results.scoreDocs;
				
				System.out.println("results.totalHits:"+ results.totalHits);
				
				if(hits.length > 0) {
					System.out.println("找到：" + hits.length + " 个结果");
					for (ScoreDoc scoreDoc : hits) {
						Document doc = searcher.doc(scoreDoc.doc);
						System.err.println("score : " + scoreDoc.toString());
						System.out.println("fileName:"+doc.get("fileName")+"\tdir:"+doc.get("dir")
								+ "\tmodifTime:" +  NumberUtils.longStringToDataStr(doc.get("modifTime")));
					}
				}
				searcher.close();
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<FileProperty> queryFileSystem(String path) {
		List<FileProperty> fileList = new ArrayList<FileProperty>();
		File folder = new File(path);
		if(folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				
				if(file.isDirectory()) {
					fileList.addAll(queryFileSystem(file.getAbsolutePath()));
				} else {
					FileProperty property = new FileProperty(file.getName(), file.getParent());
					property.setModifTime(new Date(file.lastModified()));
					fileList.add(property);
				}
			}
		} else {
			FileProperty property = new FileProperty(folder.getName(), folder.getPath());
			property.setModifTime(new Date(folder.lastModified()));
			fileList.add(property);
		}
		return fileList;
	}
}
