package com.artsoft.download.downNetwork;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.download.maoyanandpiaofang.maoyan.maoyan_tangying;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DownqqNetword {
//	public static void downMain(String urlMain) {
//		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		if (strHtml == null || strHtml.equals("")) {
//			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
//		}
//		if (strHtml == null || strHtml.equals("")) {
//			return;
//		}
//
//		Document doc = Jsoup.parse(strHtml);
//		Elements links = doc.select("ul.mod_list_pic_130 li");
//		// Element content = doc.getElementById("content");
//		// Elements links = content.getElementsByTag("a");
//		for (Element link : links) {
//			String strmainurl = "";
//			String score = "";
//			String name = "";
//			System.out.println(strmainurl = link.select("a.mod_poster_130").attr("href"));
//			// System.out.println(strmainurl = link.select("a").attr("id"));
//			System.out.println(name = link.select("a.mod_poster_130").attr("title"));
//			// System.out.println(link.select("a").text());
//			// System.out.println(link.text());
//			System.out.println(score = link.select("strong.c_txt3").text());
//			try {
//				OracleOpreater.intoReputation(name, "3", score, "0", "", urlMain, "1", "1");
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			downBranch(strmainurl, name, urlMain);
//		}
//
//	}

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
		System.out.println(numstring);
		try {
			OracleOpreater.intoReputation(nameBranch, "3", numstring, "0", "", urlMain, "1", "0");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	public static boolean downMain(String urlMain ,int xxx) {
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
			
			if (score.equals("")) {
				System.out.println(score = link.select("div.figure_score").text().replace(" ", ""));
			}
			try {
//				OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0", "", urlMain, "3", "1",strmainurl);
//				OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0", "", urlMain, "0", "1",strmainurl);
				OracleOpreater.intoReputationAndDETAIL_URL(name, "3", score, "0", "", urlMain, "1", "1",strmainurl);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			downBranch(strmainurl, name, urlMain);
			
			
			String numstring="";
			numstring=link.select("span.info_inner").text();
			
			if (numstring.equals("")) {
				numstring=link.select("span.num").text();
			}
			
			numstring=maoyan_tangying.Stringnum(numstring);
			System.out.println(numstring);
			
			try {
				OracleOpreater.intoReputation(name, "3", numstring, "0", "", strmainurl, "1", "0");
//				OracleOpreater.intoReputationAndDETAIL_URL(strmainurl, "3", numstring, "0", "", urlMain, "0", "0",
//						strmainurl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
//		String tt = doc.select("span.txt_01").select("em.strong").first().text();
//		
//		
//		try {
//			if (xxx<Integer.parseInt(tt)) {
//				return true;
//			}
//			else{
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		return true;
		

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

	public static void openstatic() {
		String url = "";
		// String[] diqu = { "814", "815", "816", "817", "818", "819" };
		// for (String diqutxt : diqu) {
		for (int i = 0; i < 5000; i = i + 20) {
			url = "http://v.qq.com/x/teleplaylist/?sort=4&offset="+i+"&itype=844&iarea=-1&iyear=-1&ipay=-1";
			System.out.println(url);
			boolean bb = downMain(url, i);
			// String urlnext = DownYoukuMovie.youkuMaim(url);
			if (!bb) {
				break;
			}
		}

		// }
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");
		// for (int i = 0; i <= 188; i++) {
		// String url = "http://v.qq.com/list/2_-1_-1_-1_1_0_" + i +
		// "_20_-1_-1_0_-1.html";
		// System.out.println(url);
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" +
		// url);
		// downMain(url);
		// }
		openstatic();
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// downMain("http://v.qq.com/list/2_-1_-1_-1_1_0_0_20_-1_-1_0_-1.html");
		// http://v.qq.com/list/2_-1_-1_-1_1_0_1_20_-1_-1_0_-1.html
		// http://v.qq.com/list/2_-1_-1_-1_1_0_188_20_-1_-1_0_-1.html
		// http://v.qq.com/list/2_-1_-1_-1_1_0_188_20_-1_-1_0_-1.html
		// while (true) {
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":开始");
		// for (int i = 0; i <= 188; i++) {
		// String url = "http://v.qq.com/list/2_-1_-1_-1_1_0_" + i +
		// "_20_-1_-1_0_-1.html";
		// System.out.println(url);
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" +
		// url);
		// downMain(url);
		// }
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":结束");
		// try {
		// Thread.sleep(1000 * 60 * 60 * 23);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// TimingTime(23, 59, 59);

		// for (int i = 0; i <= 16; i++) {
		// //http://v.qq.com/list/2_24_-1_-1_1_0_0_20_-1_-1_0_-1.html
		// String url = "http://v.qq.com/list/2_24_-1_-1_1_0_" + i +
		// "_20_-1_-1_0_-1.html";
		// System.out.println(url);
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" +
		// url);
		// downMain(url);
		// }
		
//		 TimingTime(23, 59, 59);
		runstatic();
//		runstatic();

	}

}
