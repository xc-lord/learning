package com.lord.learn.video;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestVideo {

	public static void main(String[] args) {
		try {
			String fileName = "E:/Wildlife.wmv";
			String batFile = createBatFile(fileName);
			// 调用批处理文件
			Runtime.getRuntime().exec("cmd /c start " + batFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testFFmpeg() {
		// 视频文件
		String videoRealPath = "E://Wildlife.wmv";
		// 截图的路径（输出路径）
		String imageRealPath = "E://a.jpg";
		try {
			// 调用批处理文件
			Runtime.getRuntime().exec("cmd /c start E://ffmpeg.bat " + videoRealPath + " " + imageRealPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成批处理文件
	 * @param fileName
	 * @return
	 */
	public static String createBatFile(String fileName) {
		File source = new File(fileName);
		String filePrefix = fileName.substring(0, fileName.lastIndexOf("."));
		Encoder encoder = new Encoder();
		int picNum = 9;
		try {
			MultimediaInfo m = encoder.getInfo(source);
			long ls = m.getDuration();
			//System.out.println("此视频时长为:" + ls / 60000 + "分" + (ls) / 1000 + "秒！");
			int seconds = (int) ((ls) / 1000);
			
			int count = seconds/(picNum+1);
			List<String> lines = new ArrayList<String>();
			for (int i = 0; i < picNum; i++) {
				int start = i*count;
				int end = start + count;
				
				String videoRealPath = fileName;
				// 截图的路径（输出路径）
				String imageRealPath = filePrefix + "_" + i + ".jpg";
				
				//System.out.println("start: " + start + " end: " + end);
				
				String line = "D:/java/ffmpeg/bin/ffmpeg.exe -i " + videoRealPath + " -ss " + end + " -vframes 1 -r 1 -ac 1 -ab 2 -s 1280*720 -f  image2 " + imageRealPath + "  ";
				System.out.println(line);
				lines.add(line);
			}
			lines.add("exit");
			
			write2File(lines, filePrefix + ".bat");
			
			return filePrefix + ".bat";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把文本内容写入文件
	 * @param lines		文本内容
	 * @param fileName	文件名
	 */
	public static void write2File(List<String> lines, String fileName) {
		try {
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(fileName));
			
			for(String e : lines) {
				buffWriter.write(e.toString());
				buffWriter.newLine();
				buffWriter.flush();
			}
			buffWriter.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
