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

public class Each_variety_sohu {
	
	public static void urljson(String urlBranch){
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		JSONObject sohujson = new JSONObject();
		sohujson = JSONObject.fromObject(strHtml);
		JSONArray sohujsonArray = new JSONArray();
		sohujsonArray= (JSONArray) sohujson.get("videos");
		for (Object object : sohujsonArray) {
			String tyPlayName="";
			String source="4";
			String playAmount="";
			String vodeoType="0";
			String palydate="";
			String tvType="2";
			String realUrl="";
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(tyPlayName=(String) objectobject.get("title"));
			System.out.println(realUrl=(String) objectobject.get("url"));
			System.out.println(playAmount=String.valueOf(objectobject.get("playCount")));
			OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, realUrl, tvType, realUrl);
		}
		
	}
	
	public static void sohuBranch(String urlBranch) {

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		String id=HtmlAnalyze.getTagText(strHtml, "playlistId = \"", "\"");
		Document doc=Jsoup.parse(strHtml);
		Elements linkyear=doc.select("li.v-year a");
		if (linkyear!=null) {
			for (Element element : linkyear) {
				String year="";
				System.out.println(year=element.text());
				if (year!=null) {
					//http://tv.sohu.com/item/VideoServlet?source=sohu&id=9112348&year=2016&month=0&page=1
					String urljson="http://tv.sohu.com/item/VideoServlet?source=sohu&id="+id+"&year="+year+"&month=0&page=1";
					urljson(urljson);
				}
			
			}
		}else{
			String urljson="http://tv.sohu.com/item/VideoServlet?source=sohu&id="+id+"&year=2016&month=0&page=1";
			urljson(urljson);
		}
		

	}
	
	private static void sohuMain(String sohuMainUrl) {
		// TODO Auto-generated method stub
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + sohuMainUrl);
		String strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(sohuMainUrl, 1000 * 30, "UTF-8", null, null);
		}
		try {

			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.st-pic");
			for (Element link : links) {
				String texthtml = link.select("a").attr("title");
				String texturl = link.select("a").attr("href");
				// System.out.println(texthtml);
				if (texthtml != null && !"".equals(texthtml) && texturl != null && !"".equals(texturl)) {
					System.out.println(texturl);
					sohuBranch(texturl);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		for (int i = 1; i <= 56; i++) {
			sohuMain("http://so.tv.sohu.com/list_p1106_p2_p3_p4_p5_p6_p7_p8_p9_p10" + i + "_p11_p12_p13.html");

		}
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
		TimingTime(1, 59, 59);
//		runstatic();
//		urljson("http://tv.sohu.com/item/VideoServlet?source=sohu&id=4735&year=2015&month=0&page=1");
//		sohuBranch("http://tv.sohu.com/item/MTE5ODEzMg==.html");
	}

}
