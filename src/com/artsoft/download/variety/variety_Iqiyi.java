package com.artsoft.download.variety;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.TVPlay.DownloadIqiyi;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

public class variety_Iqiyi {
	
//public static void iQiYiBranch(String urlBranch,String name,String score) {
//		
//		try {
//			
//			OracleOpreater.intoReputationAndDETAIL_URL(name, "2", score, "0", "", urlBranch, "3", "1",urlBranch);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//		if (strHtml == null || strHtml.equals("")) {
//			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
//		}
//		if (strHtml == null || strHtml.equals("")) {
//			return;
//		}
//
////		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");
//
//		System.out.println(name);
//		String videoId = HtmlAnalyze.getTagText(strHtml, "albumId:", ",");
//
//		String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/";
//		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
//		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, ":", "}"));
//		try {
//			OracleOpreater.intoReputationAndDETAIL_URL(name, "2", numhtml, "0", "", urlBranch, "2", "0",urlBranch);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

public static void iQiYiBranch(String names,String urlBranch) {
	String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
	if (strHtml == null || strHtml.equals("")) {
		strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
	}
	if (strHtml == null || strHtml.equals("")) {
		return;
	}

	String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");
	if (name.equals("")||name.equals("null")||name==null) {
		//data-shareplattrigger-videoname="
		name=HtmlAnalyze.getTagText(strHtml, "data-shareplattrigger-videoname=\"", "\"");
	}
	if (name.equals("")||name.equals("null")||name==null) {
		//data-shareplattrigger-videoname="
		name=names;
	}
	System.out.println(name);
	String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

	String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/?callback=window.Q.__callbacks__.cbrymman";
	String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
	System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, "\":", "}"));
	try {

		OracleOpreater.intoReputationAndDETAIL_URL(name, "2", numhtml, "0", "", urlBranch, "2", "0",urlBranch);

		String videourl = "http://up.video.iqiyi.com/ugc-updown/quud.do?dataid=" + videoId
				+ "&type=1&userid=&flashuid=e42dc42f8825edf5580806cde99606ce&appID=21&callback=window.Q.__callbacks__.cb1953yg";

		String feishuhtml = DownloadUtil.getHtmlText(videourl, 1000 * 30, "UTF-8", null, null);
		String feishu = "";
		System.out.println(feishu = HtmlAnalyze.getTagText(feishuhtml, "score\":", ",\""));

		OracleOpreater.intoReputationAndDETAIL_URL(name, "2", feishu, "0", "", urlBranch, "2", "1",urlBranch);
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
		Elements links = doc.select("div.site-piclist_pic");
		for (Element link : links) {
			String strmainurl = "";
			String names="";
			System.out.println(strmainurl = link.select("a.site-piclist_pic_link").attr("href"));
			System.out.println(link.select("a.site-piclist_pic_link").attr("title"));
			iQiYiBranch(names,strmainurl);
		}
	}
	
	
	public static void openstatic() {
		String url = "";
		String[] diqu = { "151", "152", "153", "154", "1113" };
		String[] leixing = { "155", "156", "157", "158", "159", "160", "163", "292", "193", "1002",
				"1003", "2117", "2118", "2119", "2120",
				"2121", "2122", "2224", "161"};
		for (String diqutxt : diqu) {
			for (String leixingtxt : leixing) {
				System.out.println(diqutxt + leixingtxt);
				
				try {
					for (int i = 1; i < 30; i++) {
						url = "http://list.iqiyi.com/www/6/" + diqutxt
								+ "-" + leixingtxt + "------------11-" + i + "-1-iqiyi--.html";
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
	        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行  
	    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runstatic();
		TimingTime(2, 59, 59);
	}

}
