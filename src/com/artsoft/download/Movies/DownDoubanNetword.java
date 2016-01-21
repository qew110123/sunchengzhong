package com.artsoft.download.Movies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Calendar;  
import java.util.Date;  
import java.util.Timer;  
import java.util.TimerTask; 

public class DownDoubanNetword {

	public static void iQiYiBranch(String urlBranch) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");

		System.out.println(name);
		String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/?callback=window.Q.__callbacks__.cbrymman";
		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, "\":", "}"));
		try {

			OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch, "1", "0");

			String videourl = "http://up.video.iqiyi.com/ugc-updown/quud.do?dataid=" + videoId
					+ "&type=1&userid=&flashuid=e42dc42f8825edf5580806cde99606ce&appID=21&callback=window.Q.__callbacks__.cb1953yg";

			String feishuhtml = DownloadUtil.getHtmlText(videourl, 1000 * 30, "UTF-8", null, null);
			String feishu = "";
			System.out.println(feishu = HtmlAnalyze.getTagText(feishuhtml, "score\":", ",\""));

			OracleOpreater.intoReputation(name, "2", feishu, "0", "", urlBranch, "1", "1");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void mainurl(String urlMain) {
		// TODO Auto-generated method stub

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		
		JSONObject htmljson = new JSONObject();
		JSONArray htmljsonArray = new JSONArray();
		htmljson = JSONObject.fromObject(strHtml);
		
		htmljsonArray=(JSONArray) htmljson.get("subjects");
		for (Object objecthtml : htmljsonArray) {
			JSONObject jsonsubject=(JSONObject) objecthtml;
			String name = "";
			System.out.println(name=(String) jsonsubject.get("title"));
			String urls="";
			System.out.println(urls=(String) jsonsubject.get("url"));
			String feishu="";
			System.out.println(feishu=(String) jsonsubject.get("rate"));
			String pinglun="";
//			OracleOpreater.intoReputation(name, "1", Amount, "0", "", urlBranch, "1", "0");
//			OracleOpreater.intoReputation(name, "1", score, "0", "", urlBranch, "1", "1");
//			OracleOpreater.intoReputation(name, "1", comment, "0", "", urlBranch, "1", "2");
//			OracleOpreater.intoReputation(name, "2", feishu, "0", "", urlBranch, "1", "1");
//			OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch, "1", "0");
			String strHtmlurl = DownloadUtil.getHtmlText(urls, 1000 * 30, "UTF-8", null, null);
			System.out.println(pinglun = HtmlAnalyze.getTagText(strHtmlurl, "votes\">", "</span"));
			try {
				OracleOpreater.intoReputation(name, "9", feishu, "0", "", urls, "3", "1");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OracleOpreater.intoReputation(name, "9", pinglun, "0", "", urls, "3", "2");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 * @param url
	 * @return
	 */

	private static String youkuMaim(String url) {
		mainurl(url);
		url = url.replaceAll("1-1-iqiyi--.html", "");
		for (int i = 1; i < 5; i++) {
			mainurl(url + i + "-1-iqiyi--.html");
		}

		return null;
	}
	
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		String strkey = ReadTxtFile.getKeyWordFromFile("keywordiqiyi.txt");
		String[] keys = strkey.split("\n");
		for (int i = 0; i < keys.length; i++) {
			System.out.println(keys[i]);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + keys[i]);
			String url = keys[i];
			System.out.println(url);
			boolean bb = true;
			DownDoubanNetword.youkuMaim(url);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

//判断数据开始时间
	public static void TimingTime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
        calendar.set(Calendar.MINUTE, mm);       // 控制分  
        calendar.set(Calendar.SECOND, ss);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
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
		
		
//		TimingTime(23, 59, 59);
//		for (int j = 0; j < 4; j++) {
//			
//			DownDoubanNetword.mainurl("http://list.iqiyi.com/www/2/-24065------------4-"+j+"-1-iqiyi--.html");
//		}
		
		
		
//		for (int i = 0; i <=500; i=i+20) {
////			System.out.println(i);
//			DownDoubanNetword.mainurl("http://movie.douban.com/j/search_subjects?type=tv&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
//		}
		
		//http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=20
		
		
		for (int i = 0; i <=500; i=i+20) {
//	System.out.println(i);
			DownDoubanNetword.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
		}

	}
}
