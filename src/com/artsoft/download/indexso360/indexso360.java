package com.artsoft.download.indexso360;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.downloadThreadpool.HaoSouWordAdmin;
import com.artsoft.downloadThreadpool.MyHaoSoutask;
import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class indexso360 {
	
	
	public static void tem_person_keyword_distrib(String person_id,String data_date,String keyword ,String urlMain){
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
//		strHtml = DownloadUtil.decodeUnicode(strHtml);
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		
		people = JSONObject.fromObject(strHtml);
		System.out.println(people);
		JSONObject data=(JSONObject) people.get("data");
		System.out.println(data);
		list=(JSONArray) data.get("list");
		int search_index=0;
		String trend="";
		for (Object object : list) {
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(keyword=(String) objectobject.get("query"));
			System.out.println(search_index=(int) objectobject.get("power"));
			System.out.println(trend=(String) objectobject.get("trend"));
			OracleHaoSou.intotem_person_keyword_distrib(data_date, person_id, keyword, search_index, trend, urlMain);
			
		}
	}
	
	private static void tem_person_relevant_keyword(String person_id,String data_date,String keyword ,String urlMain){
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
//		strHtml = DownloadUtil.decodeUnicode(strHtml);
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		
		people = JSONObject.fromObject(strHtml);
		System.out.println(people);
		
		JSONArray datalist=(JSONArray) people.get("data");
		
		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			String  recreason=(String) objectobject.get("recreason");
			Object liststring=(Object) objectobject.get("list");
//			for (Object object2 : listlist) {
//				System.out.println(object2);
//			}
			String trend="";
//			System.out.println(liststring);
			String[] listkeyword=liststring.toString().split(",");
			for (int i = 0; i < listkeyword.length; i++) {
//				System.out.println(listkeyword[i]);
				System.out.println(keyword=HtmlAnalyze.getTagText(listkeyword[i], "\"", "\":\""));
				System.out.println(trend=HtmlAnalyze.getTagText(listkeyword[i], "\":\"", "\""));
				String utf8keyword="";
				try {
					utf8keyword = java.net.URLEncoder.encode(keyword, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String keyword_url="http://www.so.com/s?ie=utf-8&src=360zhishu&q="+utf8keyword+"&from=360zhishu";
//				http://www.so.com/s?ie=utf-8&src=360zhishu&q=%E5%94%90%E5%AB%A3&from=360zhishu
				OracleHaoSou.intotem_person_relevant_keyword(data_date, person_id, utf8keyword, keyword_url, recreason, trend, urlMain);
			}
			
		}
	}
	
	
	private static void tem_person_relevant_news(String person_id,String data_date,String keyword ,String urlMain){
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		
		people = JSONObject.fromObject(strHtml);
		System.out.println(people);
		
		
		JSONObject data=(JSONObject) people.get("data");
		
		
		JSONArray datalist=(JSONArray) data.get("list");
		
		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
			String  news_title=(String) objectobject.get("title");
			System.out.println(news_title);
			String news_url=(String) objectobject.get("url");
			String news_sitename=(String) objectobject.get("sitename");
			String news_date=(String) objectobject.get("pdate");
			
			OracleHaoSou.intotem_person_relevant_news(data_date, person_id, news_date, news_sitename, news_title, news_url, urlMain);
		}
	}
	
	private static void tem_person_relevant_weibo(String person_id,String data_date,String keyword ,String urlMain){
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		
		people = JSONObject.fromObject(strHtml);
		System.out.println(people);
		
		
		JSONObject data=(JSONObject) people.get("data");
		
		
		JSONArray datalist=(JSONArray) data.get("list");
		
		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
//			String  news_title=(String) objectobject.get("title");
//			System.out.println(news_title);
			String comments_num=(String) objectobject.get("forwardsnum");
			String forwards_num=(String) objectobject.get("commentsnum");
			String timestamp=(String) objectobject.get("timestamp");
			String comments_url=(String) objectobject.get("link_forwardsnum");
			String forwards_url=(String) objectobject.get("link_commentsnum");
			String weibo_url=(String) objectobject.get("url");
			String text=(String) objectobject.get("text");
			OracleHaoSou.intotem_person_relevant_weibo(data_date, person_id, comments_num, forwards_num, comments_url, forwards_url, text, timestamp, weibo_url, urlMain);
			
		}
	}
	
	private static void tem_person_keyword_up(String person_id,String data_date,String keyword ,String urlMain){
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
		JSONObject people = new JSONObject();
		JSONArray list = new JSONArray();
		
		people = JSONObject.fromObject(strHtml);
		System.out.println(people);
		
		JSONObject data=(JSONObject) people.get("data");
		JSONArray datalist=(JSONArray) data.get("list");
		
		for (Object object : datalist) {
			JSONObject objectobject = JSONObject.fromObject(object);
//			String  news_title=(String) objectobject.get("title");
//			System.out.println(news_title);
			keyword=(String) objectobject.get("word");
			String up_rate=(String) objectobject.get("ratio");
			OracleHaoSou.intotem_person_keyword_up(data_date, person_id, keyword, up_rate, urlMain);
		}
	}
	
	
	public static void openstatic() {
		
		String returnNumPeople=OracleHaoSou.returnNumPeople("ODS.DIM_PERSON");
		System.out.println("需要采集的人名字数为"+returnNumPeople);
		
		for (int i = 0; i < Integer.parseInt(returnNumPeople); i = i + 1000) {
			List<String> listArray = OracleHaoSou.selectname(Integer.toString(i), Integer.toString(i + 1000));
			for (Object Objstring : listArray) {
				System.out.println(Objstring);
				List<String> listTemp = (List<String>) Objstring;
				System.out.println(listTemp.get(0));
				System.out.println(listTemp.get(1));
				if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
					String person_id=listTemp.get(0);
					String keyword=listTemp.get(1);
					String data_date=TimeTest.getNowTime("yyyyMMdd");
					String krywordutf8="";
					try {
						krywordutf8=java.net.URLEncoder.encode(listTemp.get(1), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						tem_person_keyword_distrib(person_id, data_date, keyword, "http://index.so.com/index.php?a=radarJson&t=30&q="+krywordutf8);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
					tem_person_relevant_keyword(person_id, data_date, keyword, "http://index.so.com/index.php?a=nlpJson&t=30&q="+krywordutf8);
					
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
					tem_person_relevant_news(person_id, data_date, keyword, "http://index.so.com/index.php?a=relNewsJson&q="+krywordutf8);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
					tem_person_relevant_weibo(person_id, data_date, keyword, "http://index.so.com/index.php?a=relWeiboJson&q="+krywordutf8);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
					tem_person_keyword_up(person_id, data_date, keyword, "http://index.so.com/index.php?a=surgeWordsJson&t=7&q="+krywordutf8);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}

	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				runstatic();
			}
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// runstatic();
		openstatic();
//		 TimingTime(11, 59, 59);
	}

}
