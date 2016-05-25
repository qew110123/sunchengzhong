package com.artsoft.download.variety;

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

public class variety_qq {
	
	
	public static void downBranchpingfen(String urlBranch, String nameBranch, String urlMain){
		String htmlpinglunhtml=DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		String htmlidhtml=HtmlAnalyze.getTagText(htmlpinglunhtml, "<!--\"create_at\":","-->");
		if (!htmlidhtml.equals("")) {
			
			String urlpinglun="http://coral.qq.com/article/1407496658/commentnum?callback=jQuery19105714774770759592_1464171533134&low_login=1&_="+htmlidhtml;
			String commentnum=DownloadUtil.getHtmlText(urlpinglun, 1000 * 30, "UTF-8", null, null);
			String comment=HtmlAnalyze.getTagText(commentnum, "commentnum\":\"","\"}");
			
			
			OracleOpreater.intoReputationAndDETAIL_URL(nameBranch, "3", comment, "0", "", urlBranch, "2", "2", urlBranch);
		}
	}
	
	public static void downBranch(String urlBranch, String nameBranch, String urlMain) {
		// http: // v.qq.com/cover/e/e7hi6lep1yc51ca.html

		urlBranch = urlBranch.replaceAll("http://v.qq.com/cover/", "").replaceAll("html", "");
		urlBranch = HtmlAnalyze.getTagText(urlBranch, "/", ".");
		String idlist=urlBranch;
		// http://sns.video.qq.com/tvideo/fcgi-bin/batchgetplaymount?callback=jQuery191043746947194449604_1445913614627&low_login=1&id=e7hi6lep1yc51ca&otype=json&_=1445913614628
		System.out.println(urlBranch);
		if (urlBranch == null || "".equals(urlBranch)) {
			return;
		}
		urlBranch = "http://sns.video.qq.com/tvideo/fcgi-bin/batchgetplaymount?callback=jQuery191043746947194449604_1445913614627&low_login=1&id="
				+ urlBranch + "&otype=json&_=1445913614628";
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		String numstring = HtmlAnalyze.getTagText(strHtml, "{\"all\":", ",\"");
		
		if (numstring.equals("")) {
//			System.out.println("为空");
			urlBranch="http://data.video.qq.com/fcgi-bin/data?tid=70&&appid=10001007&appkey=e075742beb866145&callback=jQuery19102410269394301756_1461233515175&low_login=1&idlist="+idlist+"&otype=json&_=1461233515181";
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
			if (strHtml == null || strHtml.equals("")) {
				strHtml = DownloadUtil.getHtmlText(strHtml, 1000 * 30, "UTF-8", null, null);
			}
			numstring = HtmlAnalyze.getTagText(strHtml, "\"allnumc\":",",");
		}
//		System.out.println(numstring);
		System.out.println(numstring);
		try {
			OracleOpreater.intoReputationAndDETAIL_URL(nameBranch, "3", numstring, "0", "", urlMain, "2", "0",
					urlBranch);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
	
	public static boolean downMain(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return false;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.figures_list li");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String strmainurl = "";
			String score = "";
			String name = "";
			System.out.println(strmainurl = link.select("strong.figure_title a").attr("href"));
			// System.out.println(strmainurl = link.select("a").attr("id"));
			System.out.println(name = link.select("strong.figure_title a").attr("title"));
			// System.out.println(link.select("a").text());
			// System.out.println(link.text());
			System.out.println(score = link.select("span.mod_score").text());
			try {
				// OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score,
				// "0", "", urlMain, "3", "1",strmainurl);
				OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0", "", urlMain, "2", "1", strmainurl);
//				OracleOpreater.intoReputation(name, "3", score, "0", "", urlMain, "1", "1");
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				
				downBranch(strmainurl, name, urlMain);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				
				downBranchpingfen(strmainurl, name, urlMain);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		String tt = doc.select("span.txt_01").select("em.strong").first().text();

//		try {
//			if (xxx < Integer.parseInt(tt)) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		return true;

	}
	
	public static void openstatic() {
		String url="";
		for (int i = 0; i <= 1160; i = i + 20) {
			url = "http://v.qq.com/x/varietylist/?itype=-1&offset=" + i + "&isource=-1&sort=4";
			
			downMain(url);
		}
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
			}, time, 1000 * 60 * 60 * 6);// 这里设定将延时每天固定执行
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runstatic();
		TimingTime(1, 59, 59);
	}

}
