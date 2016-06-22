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
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Each_variety_hunaniv {
	
	private static void mainmore(String url) {
		// TODO Auto-generated method stub
		String strHtml = "";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}

		// System.out.println(strHtml);
		Document doc = Jsoup.parse(strHtml);
		// Element linkmain = doc.getElementById("fluxes_static");
		Elements links = doc.select("p.img-box a");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		System.out.println(links.size());
		for (Element link : links) {
			// System.out.println(link.select("a").text());
			String urlstr = "";
			System.out.println(urlstr = link.select("a").attr("href"));
//			mainurl(urlstr);
			try {
				
				mainfalseurl(urlstr);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private static void mainfalseurl(String urlstr) {
		// TODO Auto-generated method stub
		
//		System.out.println(urlstr);
		String strHtml = "";
		strHtml = DownloadUtil.getHtmlText(urlstr, 1000 * 30, "UTF-8", null, null);
		
		String cid = HtmlAnalyze.getTagText(strHtml, "cid: ", ",");
		if (cid.equals("")) {
			return;
		}
		String cidurl="http://v.api.hunantv.com/web/slist?collection_id="+cid;
		
		String cidhtml=DownloadUtil.getHtmlText(cidurl, 1000 * 30, "UTF-8", null, null);
		if (cidhtml.equals("")) {
			return;
		}
		JSONObject pptvjson = new JSONObject();
		JSONArray pptvjsonArray = new JSONArray();
		pptvjson = JSONObject.fromObject(cidhtml);
		JSONObject jsondata= (JSONObject) pptvjson.get("data");
		pptvjsonArray = (JSONArray) jsondata.get("latest");
		for (Object object : pptvjsonArray) {
			
			
			String tyPlayName="";
			String source="7";
			String playAmount="";
			String vodeoType="0";
			String palydate="";
			String tvType="2";
			String realUrl="";
			
			JSONObject objectobject = JSONObject.fromObject(object);
//			Amount = (String) objectobject.get("playCount");
			String count = (String) objectobject.get("count");
			tyPlayName = (String) objectobject.get("title");
			realUrl = (String) objectobject.get("url");
			if (!realUrl.contains("http")) {
				realUrl="http://www.mgtv.com"+realUrl;
			}
			playAmount=String.valueOf(WeiBoSearchTVplay.Stringnum(count));
			OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, realUrl, tvType, realUrl);
		}
	
		
		
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 1; i <= 22; i++) {
			//http://list.mgtv.com/1/---------5-3---.html
			//http://list.mgtv.com/1/---------5-2---.html
			String url = "http://list.mgtv.com/1/---------5-" + i + "---.html";
			System.out.println(url);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
			mainmore(url);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
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
		TimingTime(0, 59, 59);
		
//		runstatic();

	}

}
