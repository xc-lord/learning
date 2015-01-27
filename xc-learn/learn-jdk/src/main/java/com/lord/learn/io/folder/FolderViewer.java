package com.lord.learn.io.folder;

import java.io.File;

public class FolderViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		changeDirectory("91熊猫看书 5.0.apk", "E:/360Downloads/Apk", "E:/360Downloads", false);
		listAllFile("E:/360Downloads");
	}

	public static void testlist() {
		String fileName = "D:/WorkSpace/xc";
		File file = new File(fileName);
		if(file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				String childFileName = fileName + "/" + filelist[i];
				File readFile = new File(childFileName);
				if(readFile.isDirectory()) {
					System.out.println("目录:" + childFileName);
				} else {
					System.out.println("文件:" + childFileName);
				}
			}
		} else {
			System.out.println(fileName + " 不是一个目录.");
		}
	}
	
	public static void listAllFile(String path) {
		File folder = new File(path);
		if(folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if(file.isDirectory()) {
					listAllFile(file.getAbsolutePath());
				} else {
					System.out.println("文件:" + file.getAbsolutePath());
				}
			}
		} else {
			System.out.println(folder.getAbsolutePath() + " 不是一个目录.");
		}
	}
	
	 /** 移动文件目录
     * @param fileName 文件名
     * @param oldPath 旧目录
     * @param newPath 新目录
     * @param cover 若新目录下存在和转移文件具有相同文件名的文件时，是否覆盖新目录下文件，cover=true将会覆盖原文件，否则不操作
     */
    public static void changeDirectory(String fileName,String oldPath,String newPath, boolean cover) {
        if(!oldPath.equals(newPath)){
            File oldfile=new File(oldPath+"/"+fileName);
            File newfile=new File(newPath+"/"+fileName);
            if(newfile.exists()){//若在待转移目录下，已经存在待转移文件
                if(cover)//覆盖
                    oldfile.renameTo(newfile);
                else
                    System.out.println("在新目录下已经存在："+fileName);
            }
            else{
                oldfile.renameTo(newfile);
            }
        }      
    }
}
