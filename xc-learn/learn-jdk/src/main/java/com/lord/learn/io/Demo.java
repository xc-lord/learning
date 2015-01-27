package com.lord.learn.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {
	private static String fileName = "D:/temp/t1.txt";

	public static void main(String[] args) {
		writeFile();
		readFile();
		bufferedCopy();
	}
	/**
	 * 通过缓冲区对文件进行复制
	 */
	private static void bufferedCopy() {
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(fileName.replace("t1.txt", "t5.txt")));
			String line = null;
			
			while ((line = buffReader.readLine()) != null) {
				System.out.println(line);
				buffWriter.write(line);
				buffWriter.newLine();
				buffWriter.flush();
			}
			buffWriter.close();
			buffReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读文件
	 */
	private static void readFile() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileName);
			char[] buff = new char[1024];
			int length = 0;
			while((length = fileReader.read(buff)) != -1) {
				System.out.println(new String(buff,0,length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fileReader, null);
		}
	}

	/**
	 * 写文件
	 */
	private static void writeFile() {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.write("你好啊，");
			fileWriter.flush();
			fileWriter.write("bbbbbbbbbbbbbbbbbbbbbbbb");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(null, fileWriter);
		}
	}

	private static void close(FileReader fileReader, FileWriter fileWriter) {
		if(fileReader != null) {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(fileWriter != null) {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
