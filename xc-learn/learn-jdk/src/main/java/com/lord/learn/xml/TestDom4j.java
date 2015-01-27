package com.lord.learn.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TestDom4j {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//read();
		//read2();
		//write();
		Document doc = load("test.xml");
		findByName(doc);
		//findStudent(doc);
		//update(doc);
		//add(doc);
		//delete(doc);
		
	}

	private static Document load(String document) {
		Document doc = null;
		try {
			SAXReader reader = new SAXReader();
			InputStream in = TestDom4j.class.getClassLoader().getResourceAsStream("test.xml");
			doc = reader.read(in);
			Element root = doc.getRootElement();//获得根结点
			//readNode(root);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return doc;
		
	}
	
	private static void findStudent(Document doc) {
		String xpath = "//college/class/student";
		List list = doc.selectNodes(xpath);
		Node node = doc.selectSingleNode(xpath);
		
		String name = node.valueOf("@name");
		System.out.println(name);
		
	}
	private static void findByName(Document doc) {
		String xpath = "//college/class/student[@name='stu5']";
		List list = doc.selectNodes(xpath);
		Node node = doc.selectSingleNode(xpath);
		String age = node.valueOf("@age");
		System.out.println(node.getNodeType());
		System.out.println(age);
		
	}
	
	@SuppressWarnings("unchecked")
	private static void update(Document doc) {
		String xpath = "//college/class/student/@name";
		List list = doc.selectNodes(xpath);
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			Attribute attr = (Attribute) iter.next();
			if(attr.getValue().equals("stu3")) {
				attr.setValue("卢俊");
			}
		}
		try {
			persist(doc,"test.xml");
			System.out.println("update success!");
		} catch (Exception e) {
			System.err.println("update fail!");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void add(Document doc) {
		String xpath = "//college/class/student";
		List list = doc.selectNodes(xpath);
		Iterator iter = list.iterator();
		if(iter.hasNext()) {
			Element student = (Element) iter.next();
			student.setText("手机号");
			Element mobile = student.addElement("mobile");
			mobile.setText("13257598132");
			mobile.addAttribute("type", "联通");
		}
		try {
			persist(doc,"test.xml");
			System.out.println("update success!");
		} catch (Exception e) {
			System.err.println("update fail!");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void delete(Document doc) {
		String xpath = "//college/class/student";
		List list = doc.selectNodes(xpath);
		Iterator iter = list.iterator();
		if(iter.hasNext()) {
			Element student = (Element) iter.next();
			Iterator it = student.elementIterator("mobile");
			while (it.hasNext()) {
				Element mobile = (Element) it.next();
				if(mobile.getName().equals("mobile")) {
					student.remove(mobile);
				}
				
			}
		}
		try {
			persist(doc,"test.xml");
			System.out.println("update success!");
		} catch (Exception e) {
			System.err.println("update fail!");
			e.printStackTrace();
		}
	}
	
	private static void persist(Document doc, String fileName) throws Exception {
		fileName = "src/" + fileName;
		OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), format);  
        xmlWriter.write(doc);
        xmlWriter.flush();
        xmlWriter.close();
	}
	
	private static void read() {
		try {
			SAXReader reader = new SAXReader();
			InputStream in = TestDom4j.class.getClassLoader().getResourceAsStream("test.xml");
			Document doc = reader.read(in);
			Element root = doc.getRootElement();//获得根结点
			readNode(root);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	private static void readNode(Element root) {
		if(root == null) return;
		List<Attribute> attrs = root.attributes();//获得根结点的属性
		if(attrs != null && attrs.size() > 0) {
			for (Attribute attr : attrs) {
				System.err.print(root.getName() + "-" + attr.getName() + "-" + attr.getValue() + "\t");
			}
			System.err.println();
		}
		List<Element> childNodes = root.elements();
		for(Element e : childNodes) {
			readNode(e);
		}
	}
	
	public static void read2() {  
        try {  
            SAXReader reader = new SAXReader();  
            InputStream in = TestDom4j.class.getClassLoader().getResourceAsStream("test.xml");  
            Document doc = reader.read(in);  
            doc.accept(new MyVistor());  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
    }
	
	public static void write() {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("user");
		root.addAttribute("name", "仙魔恋");
		//注释
		root.addComment("根结点：");
		Element money = root.addElement("money");
		money.addAttribute("doubleVal","1200");
		money.setText("1024");
		try {
			File file = new File("src/将神.xml");
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			//设置文档格式，支持中文
			OutputFormat format = OutputFormat.createPrettyPrint();  
	        format.setEncoding("UTF-8");
 
	        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(file), format);  
	        xmlWriter.write(doc);
	        xmlWriter.flush();
	        xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
