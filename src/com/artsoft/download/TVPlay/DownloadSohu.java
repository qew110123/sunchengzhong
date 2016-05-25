package com.artsoft.download.TVPlay;

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

public class DownloadSohu {

	public static void sohuBranch(String urlBranch) {

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		// <h2>电视剧：<span class="vname">
		String name = HtmlAnalyze.getTagText(strHtml, "<span class=\"vname\">", "</span></h2>");
		String score = HtmlAnalyze.getTagText(strHtml, "</em><strong class=\"score\">", "</strong> 分");
		// 进行评分的采集
		try {
			OracleOpreater.intoReputationAndDETAIL_URL(name, "4", score, "0", "", urlBranch, "0", "1",urlBranch);

		} catch (Exception e) {
			// TODO: handle exception
		}
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.general li");

		sohuDetailedfirst(links.first().select("div.pic a").attr("href"), name);

		System.out.println(links.size());
		for (Element link : links) {
			// System.out.println(link);
			String strmainurl = "";
			String strmaintitle = "";
			System.out.println(strmainurl = link.select("div.pic a").attr("href"));
			System.out.println(strmaintitle = link.select("div.pic a").attr("title").replaceAll(name, ""));
			sohuDetailed(strmainurl, strmaintitle);

		}

	}

	/**
	 * 进行访问总是的采集
	 * 
	 * @param urlerer
	 * @param name
	 */
	public static void sohuDetailedfirst(String urlerer, String name) {
		String strHtmls = DownloadUtil.getHtmlText(urlerer, 1000 * 30, "UTF-8", null, null);
		String strvid = HtmlAnalyze.getTagText(strHtmls, "var vid=\"", "\"");
		String strvplaylistId = HtmlAnalyze.getTagText(strHtmls, "var playlistId=\"", "\"");
		System.out.println(strvid);
		if (strvid == null || strvid.equals("")) {
			return;
		}
		String urlnew = "http://count.vrs.sohu.com/count/queryext.action?vids=" + strvid + "&plids=" + strvplaylistId
				+ "&callback=playCountVrs";
		System.out.println(urlnew);
		String strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		String Amount = HtmlAnalyze.getTagText(strHtml, "{\"" + strvid + "\":{\"total\":", ",\"");
		try {
			OracleOpreater.intoReputationAndDETAIL_URL(name, "4", Amount, "0", "", urlerer, "0", "0",urlerer);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void sohuDetailed(String urlerer, String title) {
		String strHtmls = DownloadUtil.getHtmlText(urlerer, 1000 * 30, "GBK", null, null);
		String strvid = HtmlAnalyze.getTagText(strHtmls, "var vid=\"", "\"");
		String strvplaylistId = HtmlAnalyze.getTagText(strHtmls, "var playlistId=\"", "\"");
		System.out.println(strvid);
		if (strvid == null || strvid.equals("")) {
			return;
		}
		//http://count.vrs.sohu.com/count/queryext.action?vids=2931949&plids=9107339&callback=playCountVrs
		String urlnew = "http://count.vrs.sohu.com/count/queryext.action?vids=" + strvid + "&plids=" + strvplaylistId
				+ "&callback=playCountVrs";
		
		
		System.out.println(urlnew);

		String tyPlayName;
		String serNumber = title.replaceAll("第", "").replaceAll("集", "");
		String source = "4";
		String playAmount;
		String vodeoType = "0";
		String palydate = "";
		String playUrl = urlnew;
		String tvType = "0";
		String realUrl = urlerer;
		String strHtml = DownloadUtil.getHtmlText(urlnew, 1000 * 30, "UTF-8", null, null);
		System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtmls, "<meta name=\"album\" content=\"", "\" /> "));

		System.out.println(
				playAmount = HtmlAnalyze.getTagText(strHtml, "{\"" + strvplaylistId + "\":{\"total\":", ",\""));
		try {
//			tyPlayName,source,playAmount, vodeoType, palydate, playUrl,
//			tvType, urlerer
//			OracleOpreater.intoReputationAndDETAIL_URL(tyPlayName, source, playAmount, vodeoType, "", playUrl, tvType, dataType, DETAIL_URL);
//			
//			OracleOpreater.intoPlayAmont(tyPlayName, serNumber, source, playAmount, vodeoType, palydate, playUrl, tvType,
//					realUrl);
					
			OracleOpreater.intoPlayAmont(tyPlayName, serNumber, source, playAmount, vodeoType, palydate, playUrl,
					tvType, urlerer);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// http://count.vrs.sohu.com/count/queryext.action?vids=2604452&plids=6457518&callback=playCountVrs
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
		for (int i = 1; i <= 83; i++) {
			sohuMain("http://so.tv.sohu.com/list_p1101_p2_p3_p4-1_p5_p6_p77_p80_p9_p10" + i + "_p11_p12_p13.html");

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
		// sohuBranch("http://tv.sohu.com/item/MTE3NzM3NA==.html");
		// sohuDetailed("http://tv.sohu.com/20150919/n421602862.shtml","第1集");

		// sohuMain("http://so.tv.sohu.com/list_p1101_p2_p3_p4-1_p5_p6_p77_p80_p9_p10_p11_p12_p13.html");

//		while (true) {
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
//			for (int i = 1; i <= 72; i++) {
//				sohuMain("http://so.tv.sohu.com/list_p1101_p2_p3_p4-1_p5_p6_p77_p80_p9_p10" + i + "_p11_p12_p13.html");
//
//			}
//			try {
//				Thread.sleep(1000 * 60 * 60 * 22);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
//		}
		 TimingTime(0, 59, 59);
//		 runstatic();
	}

}
