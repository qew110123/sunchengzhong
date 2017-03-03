package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_url;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_PERSON_URL;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;

public class kasi_people_url_360 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id ="";
		String name = "刘涛";
		String zhuopin = "欢乐颂,琅琊榜";
		shoushuo(id ,name, zhuopin);
		
//		kasi_people_url_360 url360=new kasi_people_url_360();
//		try {
//			url360.url("");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			daJeWang1();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	
	public String  url(String url) throws IOException{
		// 创建URL对象
		URL myURL = new URL("https://www.sun.com");

		// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
		HttpsURLConnection httpsConn = (HttpsURLConnection) myURL
		.openConnection();

		// 取得该连接的输入流，以读取响应内容
		InputStreamReader insr = new InputStreamReader(httpsConn
		.getInputStream());

		// 读取服务器的响应内容并显示
		int respInt = insr.read();
		while (respInt != -1) {
		System.out.print((char) respInt);
		respInt = insr.read();
		}
		
		
		return "";
	}
	
	
	public static void shoushuo(String id,String name, String zhuopin) {
		// TODO Auto-generated method stub
		// https://www.baidu.com/s?wd=%E5%88%98%E6%B6%9B&pn=10&oq=%E5%88%98%E6%B6%9B
		// http://www.baidu.com/s?wd=刘涛&pn=10&oq=刘涛

		// String utf8name="";
		String krywordutf8 = "";
		try {
			krywordutf8 = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "http://baike.so.com/search/?q=" + krywordutf8 + "" 	;
//		url="http://baike.so.com/search/?q=%E5%88%98%E6%B6%9B";
		String strHtml = Htmlurl(url);
		
		
//		url="http://www.so.com/s?q=%E5%88%98%E6%B6%9B&src=srp&psid=17a7edeabeae7976bdc09dcaea053f6a";
//		String strHtml="";
//		try {
//			strHtml = Jsoup.connect(url).get().toString();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		Document doc = Jsoup.parse(strHtml);
		Elements linkli = doc.select("h3");
		for (Element element : linkli) {

			if (element.text().contains("360百科")) {
				System.out.println(element);
				System.out.println(element.text());
				String urlxiangxi = "";
				System.out.println(urlxiangxi = element.select("a").attr("href"));
				
				
				

				chaxunall(id,name, zhuopin, urlxiangxi);
			}

		}

	}
	
	
	/**
	 * 通过主页进行数据的筛选2017年2月22日14:37:18
	 * 
	 * @param name
	 * @param zhuopin
	 * @param urlxiangxi
	 */
	private static void chaxunall(String id,String name, String zhuopin, String urlxiangxi) {
		// TODO Auto-generated method stub
		// String
		// url="http://www.baidu.com/s?wd="+krywordutf8+"&pn=0&"+krywordutf8;

		xuanzhe(id,name, zhuopin, urlxiangxi);

		
//		try {
//			
//			String strHtml = Htmlurl(urlxiangxi);
//			Document doc = Jsoup.parse(strHtml);
//			Elements linkli = doc.getElementById("sense-list").select("li");
//			for (Element element : linkli) {
//				System.out.println(element);
//				String title = "";
//				String urltitle = "";
//				System.out.println(title = element.select("a").attr("title"));
//				System.out.println(urltitle = element.select("a").attr("href"));
//				if (urltitle.equals("") || urltitle == null) {
//					continue;
//				}
//				if (!urltitle.contains("http://baike.so.com")) {
//					urltitle = "http://baike.so.com" + urltitle;
//				}
//				System.out.println(urltitle);
//				xuanzhe(id,name, zhuopin, urltitle);
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		

	}
	
	
	
	
	/**
	 * 查找先关数据
	 * 2017年2月22日14:44:45
	 * 
	 * @param name
	 * @param zhuopin
	 * @param urltitle
	 */
	private static void xuanzhe(String id,String name, String zhuopin, String urltitle) {
		// TODO Auto-generated method stub
		
		if (zhuopin==null||zhuopin.equals("")) {
			return;
		}

		String strHtml = Htmlurl(urltitle);

		String[] zhuopinlist = zhuopin.split(",");
		boolean bb = false;
		int inti = 0;
		for (int i = 0; i < zhuopinlist.length; i++) {
			if (strHtml.contains(zhuopinlist[i])) {
				System.out.println(urltitle);
				inti += 1;
			}
		}
//		if (inti == zhuopinlist.length) {
		if (inti >0) {
			System.out.println("成功找到url");
			
			TEM_PERSON_URL personurl = new TEM_PERSON_URL();
			personurl.setPersonId(id);
			personurl.setPersonName(name);
			personurl.setPersonUrl(urltitle);
			personurl.setType(3);
//			personurl.setUpDate(upDate);
			
			System.out.println(personurl);
			
			 Oracle.InsertTEM_PERSON_URL(personurl);
		}

	}
	
	
	
	
	
	/**
	 * 2017年1月5日14:58:15 统一打开方式
	 * 
	 * @param urlMain
	 * @return
	 * https://www.so.com/s?q=%E5%88%98%E6%B6%9B
	 */
	public static String Htmlurl(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		System.out.println(strHtml);
		int i = 0;
		while (i < 15 && strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			i = i + 1;
		}
		return strHtml;
	}
	
	
	
	public static String httpsurl(String url){
		
		return url;
		
	}
	
	
	

}
