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

import java.io.UnsupportedEncodingException;
import java.util.Calendar;  
import java.util.Date;  
import java.util.Timer;  
import java.util.TimerTask; 

public class DownIqiyiMovie {

	public static void iQiYiBranch(String urlBranch,String name,String score) {
		
		try {
			
			OracleOpreater.intoReputationAndDETAIL_URL(name, "2", score, "0", "", urlBranch, "3", "1",urlBranch);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

//		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");

		System.out.println(name);
		String videoId = HtmlAnalyze.getTagText(strHtml, "albumId:", ",");

		String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/";
		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, ":", "}"));
		try {
			OracleOpreater.intoReputationAndDETAIL_URL(name, "2", numhtml, "0", "", urlBranch, "3", "0",urlBranch);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void mainurl(String urlMain) {
		// TODO Auto-generated method stub

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		// String strHtml = DownloadUtil.readHtml(strUrl, 1000 * 30,"UTF-8",
		// cookstr, null);
		// StringBuffer strHtml = DownloadUtil.getContent(urlstr);
		// System.out.println(strHtml);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			return;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.mod-listTitle_left");
		for (Element link : links) {
			String strmainurl = "";
			String name="";
			String score="";
			System.out.println(strmainurl = link.select("a").attr("href"));
			System.out.println(name=link.select("a").attr("title"));
			System.out.println(score=link.select("span.score").text());
			iQiYiBranch(strmainurl,name,score);
		}
	}

	/**
	 * http://list.iqiyi.com/www/2/-100001------------4-1--iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-20------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-21------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-23------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-24------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-27------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-29------------4-1-1-iqiyi--.html
	 * http://list.iqiyi.com/www/2/15-30------------4-1-1-iqiyi--.html
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
	
	
	public static void openstatic() {
		String url = "";
		String[] diqu = { "1", "2", "3", "4", "308", "1115", "5" };
		String[] leixing = { "8", "13", "6", "11", "131", "291", "128", "10", "289", "12",
				"27356", "1284", "129", "9", "7",
				"27362", "130", "27375", "14", "27376", "296", "311"};
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				
				try {
					for (int i = 1; i < 30; i++) {
						url = "http://list.iqiyi.com/www/1/" + diqutxt
								+ "-" + leixingtxt + "------------4-" + i + "-1-iqiyi--.html";
						System.out.println(url);
//						String urlnext = DownYoukuMovie.youkuMaim(url);
						mainurl(url);
//						if (urlnext.equals("") || urlnext == "" || urlnext == null) {
//							break;
//						}
					}

				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}

		}
	}
	
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

//		String strkey = ReadTxtFile.getKeyWordFromFile("keywordiqiyi.txt");
//		String[] keys = strkey.split("\n");
//		for (int i = 0; i < keys.length; i++) {
//			System.out.println(keys[i]);
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + keys[i]);
//			String url = keys[i];
//			System.out.println(url);
//			boolean bb = true;
//			DownIqiyiMovie.youkuMaim(url);
//		}
		
		openstatic();
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
        }, time, 1000 * 60 * 60 * 10);// 这里设定将延时每天固定执行  
    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		TimingTime(23, 59, 59);
//		openstatic();
//		runstatic();
		TimingTime(0, 59, 59);

	}
}
