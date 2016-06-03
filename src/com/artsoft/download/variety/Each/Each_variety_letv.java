package com.artsoft.download.variety.Each;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Each_variety_letv {
	
	
	public static void mainurl(String mainUrl) {

		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}

		JSONObject letvjson = new JSONObject();
		JSONArray letvjsonArray = new JSONArray();
		letvjson = JSONObject.fromObject(strHtml);
		letvjsonArray = (JSONArray) letvjson.get("album_list");
		String urlBranch = "";
		String Amount = "";
		String name = "";
		String score = "";
		String aid = "";
		String DETAIL_URL = "";
		if (letvjsonArray == null) {
			return;
		}
		try {

			for (Object object : letvjsonArray) {
				JSONObject objectobject = JSONObject.fromObject(object);
				Amount = (String) objectobject.get("playCount");
				score = (String) objectobject.get("rating");
				name = (String) objectobject.get("name");
				aid = (String) objectobject.get("aid");
				// System.out.println(Amount = (String)
				// objectobject.get("playCount"));
				// System.out.println(score = (String)
				// objectobject.get("rating"));
				// System.out.println(name = (String) objectobject.get("name"));
				// System.out.println(aid = (String) objectobject.get("aid"));
				if (aid != "") {
					DETAIL_URL = "http://www.le.com/zongyi/" + aid + ".html";
				}

				System.out.println(Amount + "score" + score);
				
				playdanjizhongyi(DETAIL_URL);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void playdanjizhongyi(String urlMain) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return ;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links=doc.select("div.month a");
		for (Element element : links) {
			String list_month=element.attr("list-month");
			String list_year=element.attr("list-year");
			String data_tabbtt=element.attr("data-tabbtt");
			//http://api.le.com/mms/out/albumInfo/getVideoListByIdAndDate?callback=LETV.App.Detail.List.paintList&year=2015&type=1&p=420001&month=12&id=52269&b=1&s=20&o=1&_=1464772356393
			String id= HtmlAnalyze.getTagText(urlMain,"zongyi/", ".");
			if (id!=null) {
				String url="http://api.le.com/mms/out/albumInfo/getVideoListByIdAndDate?callback=LETV.App.Detail.List.paintList&year="+list_year+"&type=1&p=420001&month="+list_month+"&id="+id+"&b=1&s=20&o=1&_=1464772356393";
				zhongjianurl(url,list_year,list_month);
			}
			
		}
		
		
	}

	private static void zhongjianurl(String url, String list_year, String list_month) {
		// TODO Auto-generated method stub
		
		String strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return ;
		}
		String strHtmlString = HtmlAnalyze.getTagText(strHtml,"(", "\"})")+"\"}";
		
		JSONObject letvjson = new JSONObject();
		JSONArray letvjsonArray = new JSONArray();
		letvjson = JSONObject.fromObject(strHtmlString);
		JSONObject data = (JSONObject) letvjson.get("data");
		JSONObject nian=(JSONObject) data.get(list_year);
		letvjsonArray  =  (JSONArray) nian.get(list_month);
		
		for (Object object : letvjsonArray) {
			String tyPlayName="";
			String source="5";
			String playAmount="";
			String vodeoType="0";
			String palydate="";
			String tvType="2";
			String realUrl="";
			
			JSONObject objectobject = JSONObject.fromObject(object);
			String id =String.valueOf( objectobject.get("id"));
			realUrl="http://www.le.com/ptv/vplay/"+id+".html";
			tyPlayName=(String) objectobject.get("subTitle");
			
			String playAmounturl="http://v.stat.letv.com/vplay/getIdsInfo?type=vlist&ids="+id;
			String  playAmountstrHtml = DownloadUtil.getHtmlText(playAmounturl, 1000 * 30, "UTF-8", null, null);
			playAmount = HtmlAnalyze.getTagText(playAmountstrHtml,"play_count\":", ",");
			
			OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, realUrl, tvType, realUrl);
		}
		
		
		
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 0; i < 30; i++) {
			//http://list.le.com/apin/chandata.json?c=11&d=1&md=&o=3&p=2&s=3
			String mainUrl = "http://list.le.com/apin/chandata.json?c=11&d=1&md=&o=3&p=" + i + "&s=3";
			mainurl(mainUrl);
		}
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
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
		TimingTime(1, 59, 59);
//		runstatic() ;
	}

}
