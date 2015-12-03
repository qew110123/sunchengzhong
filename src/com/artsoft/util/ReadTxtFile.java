package com.artsoft.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadTxtFile {
	public static String getKeyWordFromFile(String FileName){
		//FileName : Config/keyword.txt
		StringBuilder strTemp =new StringBuilder();
		//String FilePath = System.getProperty("user.dir")+ "\\Config\\"+FileName;
		String FilePath = "config/"+FileName;
		File file = new File(FilePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				strTemp.append(tempString);
				strTemp.append("\n");
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return strTemp.toString();
	}
	public static void wirterfile(String FileName,String txtString){
		try {
//			String path = "d:\\json\\" + title_cn + ".json";// 文件保存路径、名字
			String path = "config/"+FileName;
//			File file = new File(FilePath);
			File file = new File(path);
			BufferedWriter ow = new BufferedWriter(new FileWriter(file));
			String s = txtString;// 写入内容
			ow.write(s.toString());
			ow.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
//		String[] keys=getKeyWordFromFile("keyword.txt").split("\n");
//		for (int i = 0; i < keys.length; i++) {
//			System.out.println(i);
//			System.out.println(keys[i]);
//		}
		wirterfile("111.txt", "txtString");
		wirterfile("111.txt", "txtString1111");
	}
	
	
}