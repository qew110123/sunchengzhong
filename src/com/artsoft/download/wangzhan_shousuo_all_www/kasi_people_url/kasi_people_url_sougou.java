package com.artsoft.download.wangzhan_shousuo_all_www.kasi_people_url;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_PERSON_URL;
import com.artsoft.bean.TVPLAY_IQIYI_INDEX;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class kasi_people_url_sougou {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String id ="";
			String name = "刘涛";
			String zhuopin = "欢乐颂,琅琊榜";
			shoushuo(id ,name, zhuopin);
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
		//https://www.sogou.com/web?query=%E5%88%98%E6%B6%9B&page=1&ie=utf8
		String url = "https://www.sogou.com/web?query=" + krywordutf8 + "&page=1&ie=utf8";
		String strHtml = Htmlurl(url);

		Document doc = Jsoup.parse(strHtml);
		Elements linkli = doc.select("h3");
		for (Element element : linkli) {

			if (element.text().contains("搜狗百科")) {
//				System.out.println(element);
				System.out.println(element.text());
				String urlxiangxi = "";
				System.out.println(urlxiangxi = element.select("a").attr("href"));
				
				if (urlxiangxi.equals("") || urlxiangxi == null) {
					continue;
				}

				chaxunall(id,name,krywordutf8, zhuopin, urlxiangxi);
			}

		}

	}
	
	
	
	/**
	 * 通过主页进行数据的筛选 2017年1月5日14:58:58
	 * 
	 * @param name
	 * @param zhuopin
	 * @param urlxiangxi
	 */
	private static void chaxunall(String id,String name,String nameutf_8, String zhuopin, String urlxiangxi) {
		// TODO Auto-generated method stub
		// String
		// url="http://www.baidu.com/s?wd="+krywordutf8+"&pn=0&"+krywordutf8;
		
		
		if (zhuopin.equals("") || zhuopin == null) {
			return;
		}

		xuanzhe(id,name, zhuopin, urlxiangxi);
		
		//http://baike.sogou.com/v74151.htm?fromTitle=%E5%88%98%E6%B6%9B
		
		String only_id=HtmlAnalyze.getTagText(urlxiangxi, "sogou.com/v", ".");
		
//		String nameutf_8=HtmlAnalyze.getTagText(urlxiangxi+"#", "fromTitle=", "fromTitle=");
		
		if (!only_id.equals("")&&only_id!=null&&!nameutf_8.equals("")&&nameutf_8!=null) {
			//http://baike.sogou.com/lemma/ambiList.v?lid=64567816&from=%E5%88%98%E6%B6%9B
			String Stringurl="http://baike.sogou.com/lemma/ambiList.v?lid="+only_id+"&from="+nameutf_8;
//			String strHtml = Htmlurl(Stringurl);
//			Document doc = Jsoup.parse(strHtml);
			String strHtml="";
			try {
				strHtml = Jsoup.connect(Stringurl).get().toString();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (strHtml==null||strHtml.equals("")) {
				return;
			}
			
			strHtml =HtmlAnalyze.getTagText(strHtml, "<body>", "</body>");
			
			JSONObject people = new JSONObject();
			JSONArray list = new JSONArray();

			people = JSONObject.fromObject(strHtml);
//			System.out.println(people);
			try {
				JSONArray data =  people.getJSONObject("data").getJSONArray("ambis");
				System.out.println(data.toString());
				for (Object object : data) {
					JSONObject objectobject = JSONObject.fromObject(object);
					String id_one="";
					System.out.println( id_one=objectobject.getString("lid"));
					System.out.println( objectobject.getString("ambiTitle"));
					//http://baike.sogou.com/v74151.htm
					String urltitle="http://baike.sogou.com/v"+id_one+".htm";
					System.out.println(urltitle);
					xuanzhe(id,name, zhuopin, urltitle);
					
				}
				
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			
		}
		
		
		
		

		
//		Elements linkli = doc.select("div.semantic_item li");
//		for (Element element : linkli) {
//			System.out.println(element);
//			String title = "";
//			String urltitle = "";
//			System.out.println(title = element.select("a").attr("title"));
//			System.out.println(urltitle = element.select("a").attr("href"));
//			if (urltitle.equals("") || urltitle == null) {
//				continue;
//			}
//			if (!urltitle.contains("http://baike.sogou.com")) {
//				urltitle = "http://baike.sogou.com" + urltitle;
//			}
////			xuanzhe(id,name, zhuopin, urltitle);
//		}

	}
	
	
	/**
	 * 查找先关数据 2017-1-5 15:10:11
	 * 
	 * @param name
	 * @param zhuopin
	 * @param urltitle
	 */
	private static void xuanzhe(String id,String name, String zhuopin, String urltitle) {
		// TODO Auto-generated method stub

		String strHtml = Htmlurl(urltitle);
		
		if (zhuopin==null||zhuopin.equals("")) {
			return;
		}

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
			personurl.setType(2);
//			personurl.setUpDate(upDate);
			
			 Oracle.InsertTEM_PERSON_URL(personurl);
		}

	}
	
	
	
	
	/**
	 * 2017年1月5日14:58:15 统一打开方式
	 * 
	 * @param urlMain
	 * @return
	 */
	public static String Htmlurl(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		int i = 0;
		while (i < 15  && (strHtml==null || strHtml.equals(""))) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
			i = i + 1;
		}
		return strHtml;
	}

}
