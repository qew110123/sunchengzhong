package com.artsoft.download.variety.Each;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.webo.weiboSearch.WeiBoSearchTVplay;
import com.artsoft.downloadThreadpool.people.HaoSouWordAdmin;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Each_variety_pptv {

	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		
//		boolean bb = true;
//		while (bb) {
		strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
		
		//"pid":
		if (strHtml.equals("")) {
			return;
		}
		
		try {
			
			
			Document doc = Jsoup.parse(strHtml);
			String DETAIL_URL="";
			Elements linksa = doc.select("a.ui-list-ct");
			for (Element elementa : linksa) {
				System.out.println(DETAIL_URL=elementa.attr("href"));
				
				String strHtmlDETAIL_URL = DownloadUtil.getHtmlText(DETAIL_URL, 1000 * 30, "UTF-8", null, null);
				String pid=HtmlAnalyze.getTagText(strHtmlDETAIL_URL, "pid\":",",");
				
				if (pid.equals("")) {
					return;
				}
				
				String urlpid="http://v.pptv.com/show/videoList?from=web&version=1.0.0&format=jsonp&pid="+pid;
				String trHtmlpid = DownloadUtil.getHtmlText(urlpid, 1000 * 30, "UTF-8", null, null);
//				System.out.println(trHtmlpid);
//				String strHtmlpid = DownloadUtil.decodeUnicode(trHtmlpid);
//				
//				System.out.println(strHtmlpid);
				
				JSONObject pptvjson = new JSONObject();
				JSONArray pptvjsonArray = new JSONArray();
				pptvjson = JSONObject.fromObject(trHtmlpid);
				JSONObject jsondata= (JSONObject) pptvjson.get("data");
				pptvjsonArray = (JSONArray) jsondata.get("list");
				for (Object object : pptvjsonArray) {
					
					
					String tyPlayName="";
					String source="6";
					String playAmount="";
					String vodeoType="0";
					String palydate="";
					String tvType="2";
					String realUrl="";
					
					JSONObject objectobject = JSONObject.fromObject(object);
//					Amount = (String) objectobject.get("playCount");
					playAmount = (String) objectobject.get("pv");
					tyPlayName = (String) objectobject.get("title");
					realUrl = (String) objectobject.get("url");
					playAmount=String.valueOf(WeiBoSearchTVplay.Stringnum(playAmount));
					OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, realUrl, tvType, realUrl);
				}
				
				
				
				
				
				
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		
		
		// System.out.println(strHtml);
//			try {
//				
//			
//				Document doc = Jsoup.parse(strHtml);
//				String DETAIL_URL="";
//				Elements linksa = doc.select("a.ui-list-ct");
//				for (Element elementa : linksa) {
//					System.out.println(DETAIL_URL=elementa.attr("href"));
//					Elements links = elementa.select("p.ui-txt");
//					// Element content = doc.getElementById("content");
//					// Elements links = content.getElementsByTag("a");
//					for (Element link : links) {
//						String name = "";
//						String score = "";
//						System.out.println(name = link.select("span").text());
//						System.out.println(score = link.select("em").text());
//		//				System.out.println(DETAIL_URL= link.select("em").text());
//						// Download.youkuBranch(strmainurl);
////						try {
////							OracleOpreater.intoReputationAndDETAIL_URL(name, "6", score, "0", "", mainUrl, "2", "1",DETAIL_URL);
////							
////						} catch (Exception e) {
////							// TODO: handle exception
////						}
//					}
//					
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
		

	}
	
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 1; i < 80; i++) {
			//http://list.pptv.com/channel_list.html?page=2&type=4&sort=1
			String mainUrl = "http://list.pptv.com/channel_list.html?page=" + i + "&type=4&sort=1";
			mainurl(mainUrl);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
	
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
		TimingTime(0, 59, 59);
//		runstatic();
	}

}
