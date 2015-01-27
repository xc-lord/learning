package com.lord.learn.lucene;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
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
import org.wltea.analyzer.lucene.IKAnalyzer;

public class TestLucene36 
{
    public static final String indexPath = "d:/temp/lucene";
    public static final Version VERSION_36 = Version.LUCENE_36;
    
    public static Analyzer analyzer = new IKAnalyzer();
    
    /**搜索结果排序方式(该字段索引时必须isIndexed=true)*/
    public static Sort sort = new Sort(new SortField("birth",SortField.INT,false));
    
    
    public static void index() throws Exception
    {
        Date start = new Date();
        System.out.println("Indexing to directory '" + indexPath + "'");

        Directory dir = FSDirectory.open(new File(indexPath));
        IndexWriterConfig iwc = new IndexWriterConfig(VERSION_36, analyzer);
        iwc.setOpenMode(OpenMode.CREATE);
//        iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
//        iwc.setRAMBufferSizeMB(1024.0);
        
        IndexWriter writer = new IndexWriter(dir, iwc);
        indexDocs(writer);
        writer.close();

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime() + " total milliseconds");
    }
    
    
    public static void indexDocs(IndexWriter writer) throws IOException
    {
        for(String info:data)
        {
            String[] ss = info.split("\\|");
            Document doc = new Document();
            
            Field field_id = new Field("id", ss[0], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS);
            field_id.setIndexOptions(IndexOptions.DOCS_ONLY);
            doc.add(field_id);
            
            Field field_name = new Field("name", ss[1], Field.Store.YES, Field.Index.ANALYZED);
            doc.add(field_name);
            
            NumericField field_birth = new NumericField("birth",Field.Store.YES, true);
            field_birth.setIntValue(Integer.valueOf(ss[2]));
            doc.add(field_birth);
            
            Field field_desc = new Field("desc", ss[3], Field.Store.YES, Field.Index.ANALYZED);
            doc.add(field_desc);
            
            if (writer.getConfig().getOpenMode() == OpenMode.CREATE) 
                writer.addDocument(doc);
            else
                writer.updateDocument(new Term("id", ss[0]), doc);
        }
    }
    
    public static void search(String[] fields,String keyword) throws Exception
    {
        /**获取IndexSearcher*/
        IndexReader reader = IndexReader.open(FSDirectory.open(new File(indexPath)));
        IndexSearcher searcher = new IndexSearcher(reader);
        
        /**搜索条件*/
//        QueryParser parser = new QueryParser(VERSION_36, fields[0], analyzer);
        QueryParser parser = new MultiFieldQueryParser(VERSION_36, fields, analyzer);
        Query query = parser.parse(keyword);
        //分词结果
        System.out.println("Searching for: " + query.toString());
        
        TopDocs results = searcher.search(query, 5);
//        TopDocs results = searcher.search(query, 5, sort);
        ScoreDoc[] hits = results.scoreDocs;
        System.out.println("results.totalHits:"+ results.totalHits);
        System.out.println("hits.length:"+ hits.length);
        
        //高亮显示设置
//        Formatter formatter = new SimpleHTMLFormatter("<b>","</b>");   
//        Highlighter highlighter = new Highlighter(formatter,new QueryScorer(query));
//        highlighter.setTextFragmenter(new SimpleFragmenter(200));
        
        for(ScoreDoc scoreDoc:hits)
        {
            //评分详情
//            Explanation explanation = searcher.explain(query, scoreDoc.doc);
            
            Document doc= searcher.doc(scoreDoc.doc);
            System.out.println(scoreDoc.score + "\tid:"+doc.get("id")+"\tname:"+doc.get("name")+"\tbirth:"+doc.get("birth")+"\tdesc:"+doc.get("desc"));
//            System.out.println(scoreDoc.score + "  " + doc.toString());
            
//            TokenStream tokenStream = analyzer.tokenStream("token", new StringReader(doc.get(field)));
//            System.out.println(highlighter.getBestFragment(tokenStream,doc.get(field)));
        }
    }
    
    
    static String[] data = new String[]{
        "0|苍井空空|1983|因为其童顏巨乳的特色，获得极大的回响与超高人气",
        "1|羽田夕夏|1985|因为乳晕太大，让自己自惭形秽，所以有些时候才会不想发",
        "2|夏目奈奈|1982|入行前是珠宝售貨員的奈奈，坦言拍AV是她的天职",
        "3|麻美由麻|1987|巨乳女优在厚薄码同时出道引发不少疑虑",
        "4|小向杏奈|1983|如果那一天被不知名的男人强暴一定很刺激",
        "5|大浦安娜|1980|日本第一乳神为法日混血儿,她的出现为业界带来极大的冲击",
        "6|美月玲奈|1982|样貌绝对一流，另外还是高丽与扶桑的混血美女",
        "7|游佐七海|1982|喜欢游佐七海的人估计都偏好日本学生系列",
        "8|森原由紀|1978|护士制服很赞200",//森原由紀的片子涉及后门，这是我所不喜欢的地方
        "9|白石日和|1983|护士制服很赞300"//她身材不错，肉肉的感觉，很有质感，演技也不错，护士制服很赞的
    };
    
    
    public static void main(String[] args) throws Exception
    {
       // index();
        search(new String[]{"name","desc"},"苍井夕夏入行前偏好制服后门");
    }
}