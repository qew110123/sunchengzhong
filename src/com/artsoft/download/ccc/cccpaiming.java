package com.artsoft.download.ccc;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.ccc;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.TimeTest;

public class cccpaiming {
//  private static List<Student> getStudent() throws Exception  
//  {  
//      List list = new ArrayList();  
//      SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");  
//
//      Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));  
//      Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));  
//      Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));  
//      list.add(user1);  
//      list.add(user2);  
//      list.add(user3);  
//
//      return list;  
//  }  
	private static List<ccc> getccc() throws Exception  {
      List list = new ArrayList();  
//      SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");  
//
//      Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));  
//      Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));  
//      Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));  
//      list.add(user1);  
//      list.add(user2);  
//      list.add(user3);  
      
      String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText("http://www.xunyee.cn/rank-teleplay-play-0.html", 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.yesterdaytab a.rank_left_lib");
		String paiming="";
		String dianshiju="";
		String bofangliang="";
		String time="";
		for (Element element : links) {
			
			System.out.println(paiming=element.select("span.rank_left_rank").text());
			System.out.println(dianshiju=element.select("span.rank_left_name").first().text());
			System.out.println(bofangliang=element.select("span.rank_left_value").first().text());
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			System.out.println(time=TimeTest.getNowTime("yyyy-MM-dd"));
			CommonUtil.setLog(paiming+"	"+dianshiju+"	"+bofangliang+"	"+time);
			ccc c=new ccc(paiming,dianshiju,bofangliang);
			list.add(c);
			
		}
//
      return list;  
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText("http://www.xunyee.cn/rank-teleplay-play-0.html", 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.yesterdaytab a.rank_left_lib");
		String paiming="";
		String dianshiju="";
		String bofangliang="";
		String time="";
		for (Element element : links) {
			
			System.out.println(paiming=element.select("span.rank_left_rank").text());
			System.out.println(dianshiju=element.select("span.rank_left_name").first().text());
			System.out.println(bofangliang=element.select("span.rank_left_value").first().text());
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			System.out.println(time=TimeTest.getNowTime("yyyy-MM-dd"));
			CommonUtil.setLog(paiming+"	"+dianshiju+"	"+bofangliang+"	"+time);
			
		}

	}

}
