package com.artsoft.download.Movies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DownqqMovie {
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
//				OracleOpreater.intoReputation(name, "3", score, "0", "", urlMain, "3", "1");
			} catch (Exception e) {
				// TODO: handle exception
			}
			downBranch(strmainurl, name, urlMain);
		}
		Element linksnext = doc.select("a.page_next").first();
		
		if (linksnext.attr("href")=="javascript:;") {
			return true;
		}
		else{
			return false;
		}
		

	}

	public static void downBranch(String urlBranch, String nameBranch, String urlMain) {
		// http: // v.qq.com/cover/e/e7hi6lep1yc51ca.html

		urlBranch = urlBranch.replaceAll("http://v.qq.com/cover/", "").replaceAll("html", "");
		urlBranch = HtmlAnalyze.getTagText(urlBranch, "/", ".");
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
		System.out.println(numstring);
		try {
			OracleOpreater.intoReputation(nameBranch, "3", numstring, "0", "", urlMain, "3", "0");
		} catch (Exception e) {
			// TODO: handle exception
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
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		for (int i = 0; i <= 188; i++) {
			String url = "http://v.qq.com/list/2_-1_-1_-1_1_0_" + i + "_20_-1_-1_0_-1.html";
			System.out.println(url);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
			downMain(url);
		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
	
	
	public static void openstatic() {
		String url = "";
		String[] diqu = { "100024", "100025", "100026", "100027", "100028", "100029", "100030", "100031", "100032", "100033" };
		String[] nianfen = { "100063", "100034", "100035", "100036", "100037", "100038", "100039", "100040"};
		for (String diqutxt : diqu) {
			for (String nianfentxt : nianfen) {
				System.out.println(diqutxt + nianfentxt);
				
				try {
					for (int i = 20; i < 5000; i=i+20) {
						url = "http://v.qq.com/x/movielist/?cate=-1&offset="+i+"&sort=5&pay=-1&area=" + diqutxt
								+ "&year=" + nianfentxt + "";
						System.out.println(url);
						boolean bb=downMain(url);
//						String urlnext = DownYoukuMovie.youkuMaim(url);
						if (!bb) {
							break;
						}
					}

				}catch (Exception e) {
					// TODO: handle exception
				}
				
			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		TimingTime(23, 59, 59);
		openstatic();

	}

}
