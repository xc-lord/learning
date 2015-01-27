package com.lord.learn.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestLuceneBuildIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File docDir = new File("D:/temp");//将要索引的文件目录
		File indexDir = new File("D:/temp/index");
		
		try {
			Directory dirIndex = FSDirectory.open(indexDir);//索引存放目录			
			Analyzer luceneAnalyzer = new IKAnalyzer();//词法分析器		
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36, luceneAnalyzer);
			iwc.setOpenMode(OpenMode.CREATE);//如果已经有索引文件在索引目录下，我们将覆盖它们
			
			IndexWriter indexWriter = new IndexWriter(dirIndex,iwc);//索引写入工具
			
			Date start = new Date();
			
			File[] textFiles = docDir.listFiles();			
			for (File file : textFiles) {
				if(file.isFile() && file.getName().endsWith(".txt")) {
					System.out.println("File " + file.getCanonicalPath() + " 正在被索引..., ");
					//读取文件内容
					FileInputStream fis = new FileInputStream(file);
					InputStreamReader inputReader = new InputStreamReader(fis, "UTF-8");
					BufferedReader bufferedReader = new BufferedReader(inputReader);
					String line = null;
					StringBuffer context = new StringBuffer();
					while((line=bufferedReader.readLine()) != null){
						System.out.println(line);
						context.append(line + "\n");
					}
					//信息域
					Field pathField = new Field("path", file.getPath(), Field.Store.YES, Field.Index.ANALYZED_NO_NORMS);
					Field bodyField = new Field("body", context.toString(), Field.Store.NO, Field.Index.ANALYZED);	//只索引不存储
					
					bodyField.setBoost(1.2f);	//设置权重
					
					NumericField modifiedField = new NumericField("modified");
					System.out.println(file.lastModified());
			        modifiedField.setLongValue(file.lastModified());
			        //添加到文档
			        Document document = new Document();
			        document.add(pathField);
			        document.add(bodyField);
			        document.add(modifiedField);
			        //写入索引
			        indexWriter.addDocument(document);
			        fis.close();
				}
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
}
