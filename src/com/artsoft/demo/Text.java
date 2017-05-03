package com.artsoft.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;
import com.artsoft.util.DownloadUtil;

public class Text {
	private static void writerTxt(String text) {
		BufferedWriter fw = null;
		try {
			File file = new File("D://text.txt");
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			fw.append(text);
			// fw.newLine();
			// fw.append("我又写入的内容");
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//// writerTxt("111");
		// String strHtml = "";
		// String url="http://www.open-open.com/jsoup/set-html.htm";
		// strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null,
		//// null);
		//
		// System.out.println(strHtml);
		//
		// System.out.println("****************************************");
		//
		// Document doc = Jsoup.parse(strHtml);
		//
		// Element div = doc.select("div").first(); // <div></div>
		// div.html("<p>lorem ipsum</p>"); // <div><p>lorem ipsum</p></div>
		// div.prepend("<p>First</p>");//在div前添加html内容
		// div.append("<p>Last</p>");//在div之后添加html内容
		// // 添完后的结果: <div><p>First</p><p>lorem ipsum</p><p>Last</p></div>
		//
		//// Element span = doc.select("span").first(); // <span>One</span>
		//// span.wrap("<li><a href='http://example.com/'></a></li>");
		// // 添完后的结果: <li><a href="http://example.com"><span>One</span></a></li>
		//
		//
		// System.out.println(doc);

//		try {
//			// 返回给定字符串名的类 Class 对象
//			// 并创建此 Class 对象所表示的类的一个新实例
//			Object object = Class.forName("com.artsoft.demo.Quizzee").newInstance();
//			// 返回方法名为“testMethod”的一个 Method 对象，后面跟的是该方法参数
//			Method method = object.getClass().getMethod("testMethod", new Class[] { String.class, Integer.class });
//			// 执行该方法
//			method.invoke(object, new Object[] { new String("数据1"), 520 });
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			// 返回给定字符串名的类 Class 对象
//			// 并创建此 Class 对象所表示的类的一个新实例
//			Object object = Class.forName("com.artsoft.downloadThreadpool.people.HaoSouWordAdmin").newInstance();
//			// 返回方法名为“testMethod”的一个 Method 对象，后面跟的是该方法参数
//			Method method = object.getClass().getMethod("main" , new Class[] {    });
//			// 执行该方法
//			method.invoke(object , null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
//		 HaoSouWordAdmin.main(null);
		 
//			String strHtml = "";
//		 String url="http://zhishu.sogou.com/index/media/wechat?kwdNamesStr=%E6%96%87%E7%AB%A0&timePeriodType=MONTH&dataType=MEDIA_WECHAT&queryType=INPUT";
//		 strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null,
//		 null);
//		 
//		 System.out.println(strHtml);
		 
		
	}

}
