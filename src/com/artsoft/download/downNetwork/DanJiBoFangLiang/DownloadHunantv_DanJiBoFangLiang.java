package com.artsoft.download.downNetwork.DanJiBoFangLiang;

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

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DownloadHunantv_DanJiBoFangLiang {
	
	
	public static void mainfalseurl(String mainUrl){

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		String tyPlayName = "";
		System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml, "cname: \"", "\""));
		
		String strVolumess=HtmlAnalyze.getTagText(strHtml, tyPlayName , "_");
		String serNumber = strVolumess.replaceAll("第", "").replaceAll("集", "");
		if (serNumber.equals("1")) {
			String idnum = HtmlAnalyze.getTagText(strHtml, "vid: ", ",");
			String strVolumes=HtmlAnalyze.getTagText(strHtml, tyPlayName , "_");
			hunanBranch(idnum, tyPlayName, strVolumes, mainUrl);
		}
		else{
		try {
			String idnum = HtmlAnalyze.getTagText(strHtml, "vid: ", ",");
			String strVolumes=HtmlAnalyze.getTagText(strHtml, tyPlayName , "_");
//			hunanBranch(idnum, tyPlayName, strVolumes, mainUrl);
			String urlss="http://v.api.mgtv.com/list/tvlist?video_id="+idnum+"&page=0&size=50&callback=jQuery182009630912937482106_1463738087594&_=1463738087831";
			String strHtmlurlss=DownloadUtil.getHtmlText(urlss, 1000 * 30, "UTF-8", null, null);
			 strHtmlurlss=HtmlAnalyze.getTagText(strHtmlurlss, "(" , "}]}})");
			 strHtmlurlss=strHtmlurlss+"}]}}";
			System.out.println(strHtmlurlss); 
			 JSONObject letvjson = new JSONObject();
			//JSONArray letvjsonArray = new JSONArray();
			letvjson = JSONObject.fromObject(strHtmlurlss);
			
			Object Amountdata =  letvjson.get("data");
			JSONArray AmountdataJSONArray=(JSONArray) JSONObject.fromObject(Amountdata).get("list");
			for (Object object : AmountdataJSONArray) {
//				System.out.println();
				JSONObject objectobject = JSONObject.fromObject(object);
				String urls = (String) objectobject.get("url");
				String t1 = (String) objectobject.get("t1");
				String video_id = (String) objectobject.get("video_id");
//				aid = (String) objectobject.get("aid");
				if (!video_id.equals("")) {
					urls="http://www.mgtv.com"+urls;
					hunanBranch(video_id, tyPlayName, t1, mainUrl);
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			// /程序报错
//			System.out.println("出错mainfalseurl");
			
			
			String nexturl=HtmlAnalyze.getTagText(strHtml, "next_url: \"", "\"");
			if (!nexturl.equals("")&&!nexturl.equals("null")) {
				nexturl="http://www.mgtv.com"+nexturl;
				mainurl(nexturl);
			}
		}
		}

	}

	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		String tyPlayName = "";
		System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml, "cname: \"", "\""));
		try {

			// System.out.println(strHtml);
//			Document doc = Jsoup.parse(strHtml);
//			Element linkmain = doc.getElementById("fluxes_static");
//			Elements links = linkmain.select("ul.v-list-inner li.v-list-unit");
//			// Element content = doc.getElementById("content");
//			// Elements links = content.getElementsByTag("a");
//			System.out.println(links.size());
//			for (Element link : links) {
//				String idnum = "";
//				String strVolumes = "";
//				System.out.println(strVolumes = link.select("span.inner-title").text());
//				System.out.println(idnum = link.select("a.video").attr("href"));
//				System.out.println(idnum = HtmlAnalyze.getTagText(idnum, "/f/", ".html"));
//				// System.out.println(link.select("span.inner-title").text());
//				hunanBranch(idnum, tyPlayName, strVolumes, mainUrl);
//				
//			}
			String idnum = HtmlAnalyze.getTagText(strHtml, "vid: ", ",");
			String strVolumes=HtmlAnalyze.getTagText(strHtml, tyPlayName , "_");
			hunanBranch(idnum, tyPlayName, strVolumes, mainUrl);
			
//			String nexturl=HtmlAnalyze.getTagText(strHtml, "next_url: \"", "\"");
//			if (!nexturl.equals("")&&!nexturl.equals("null")) {
//				nexturl="http://www.mgtv.com"+nexturl;
//				mainurl(nexturl);
//			}
		} catch (Exception e) {
			// TODO: handle exception
			// /程序报错
			System.out.println("出错");
		}

	}

	public static void hunanBranch(String idnum, String tyPlayName, String strVolumes, String playUrl) {
		// String tyPlayName = "";
		String serNumber = strVolumes.replaceAll("第", "").replaceAll("集", "");
		String source = "7";
		String playAmount = "";
		String vodeoType = "0";
		String palydate = "";
		// String playUrl = urlnew;
		String tvType = "1";
		// String realUrl = "";

		//http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn//dynamicinfo?callback=jQuery18205857753134332597_1459236897033&vid=3088548&_=1459236897317
		//http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn//dynamicinfo?callback=jQuery18208216645761579375_1463733049128&vid=3173475&_=1463733050155
		String urlnew = "http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn//dynamicinfo?callback=jQuery182025981585565023124_1445935554595&vid="
				+ idnum + "&_=1463733050155";
		System.out.println(urlnew);

		String strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);

		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		System.out.println(playAmount = HtmlAnalyze.getTagText(strHtml, "all\":", ",\""));
		// playAmount = playAmount.replaceAll(",", "");
		try {

			OracleOpreater.intoPlayAmont(tyPlayName, serNumber, source, playAmount, vodeoType, palydate, urlnew, tvType,
					playUrl);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void mainmore(String url) {
		// TODO Auto-generated method stub
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}

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
			mainfalseurl(urlstr);
		}
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 1; i <= 2; i++) {
			//http://list.mgtv.com/1/122---------5-1---.html
			String url = "http://list.mgtv.com/1/122---------5-" + i + "---.html";
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
		}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//惊醒详细信息暂时不管
//		TimingTime(0, 59, 59);
		runstatic();
	}

}
