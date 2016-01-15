package com.artsoft.download.Movies;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownDoubanChaXunNetword {

	public static void mainUrlall(String urlBranch, String name) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		// String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.result-list div.result");
		for (Element element : links) {
			if (element.text().contains("电视剧")) {
				String urls = "";
				System.out.println(urls = element.select("a.nbg").first().attr("href"));
				String feishu = "";
				System.out.println(feishu = element.select("span.rating_nums").text());
				String pinglun = "";
				System.out.println(pinglun = HtmlAnalyze.getTagText(element.toString(), "<span>(", "评价"));
				try {
					if (feishu != null && !feishu.equals("")) {
						OracleOpreater.intoReputation(name, "9", feishu, "0", "", urls, "1", "1");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if (pinglun != null &&! pinglun.equals("")) {
						pinglun = pinglun.replaceAll("\\D+", "");
						if (pinglun != null && !pinglun.equals("")) {
							OracleOpreater.intoReputation(name, "9", pinglun, "0", "", urls, "1", "2");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}

	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		TimeTest tt = new TimeTest();
		String timesday=tt.getNowTime("yyyyMMdd");
		List<String> listArray = OracleNetwork.selectbaidudianshiju(timesday);
//
		for (Object Objstring : listArray) {
			// System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "http://www.douban.com/search?cat=1002&q="
							+ java.net.URLEncoder.encode(listTemp.get(0), "utf-8") + "";
					mainUrlall(urlBranch, listTemp.get(0));

					CommonUtil.setLog(
							TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0) + "," + listTemp.get(0));

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

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
		//20160111
		//	TimeTest tt = new TimeTest();
//		System.out.println("获取当天日期:"+tt.getNowTime("yyyy-MM-dd"));
		TimeTest tt = new TimeTest();
		String timesday=tt.getNowTime("yyyyMMdd");
		List<String> listArray = OracleNetwork.selectbaidudianshiju(timesday);
//
		for (Object Objstring : listArray) {
			// System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			if (listTemp.get(0) != null && !"".equals(listTemp.get(0))) {
				String urlBranch = "";
				try {
					urlBranch = "http://www.douban.com/search?cat=1002&q="
							+ java.net.URLEncoder.encode(listTemp.get(0), "utf-8") + "";
					mainUrlall(urlBranch, listTemp.get(0));

					CommonUtil.setLog(
							TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + listTemp.get(0) + "," + listTemp.get(0));

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
	}
}
