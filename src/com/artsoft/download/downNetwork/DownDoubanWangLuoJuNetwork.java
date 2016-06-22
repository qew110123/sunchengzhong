package com.artsoft.download.downNetwork;

import java.net.Proxy;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.downloadThreadpool.IpFilter;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DealProxy;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class DownDoubanWangLuoJuNetwork {
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

//		TimeTest tt = new TimeTest();
//		String timesday=tt.getNowTime("yyyyMMdd");
//		IpFilter.mainip("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=20");
		
		for (int i = 0; i <=90; i=i+15) {
			String url ="https://movie.douban.com/subject_search?start="+i+"&search_text=%E7%BD%91%E5%89%A7&cat=1002";
			mainUrlall(url);

		}
		
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

	private static void mainUrlall(String urlBranch) {
		Proxy proxy = DealProxy.getInstance().getPoxxy();
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
//		if (strHtml == null || strHtml.equals("")) {
//			return;
//		}
		boolean bb=true;
		int ii=0;
		while (bb) {
			
			proxy = DealProxy.getInstance().getPoxxy();
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, proxy);
			System.out.println("訪問次數:"+(ii=ii+1));
			if (strHtml!=null) {
				bb=false;
			}
			
		}
		
		

		// String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");
		try {
			
		
			Document doc = Jsoup.parse(strHtml);
			Elements links = doc.select("div.article table tr.item");
			for (Element element : links) {
	//			if (element.text().contains("电视剧")) {
				String name= "";
				System.out.println(name= element.select("a.nbg").first().attr("title"));
					String urls = "";
					System.out.println(urls = element.select("a.nbg").first().attr("href"));
					String feishu = "";
					System.out.println(feishu = element.select("span.rating_nums").text());
					String pinglun = "";
					System.out.println(pinglun = HtmlAnalyze.getTagText(element.toString(), ">(", "人评价"));
					try {
						if (feishu != null && !feishu.equals("")) {
	//						OracleOpreater.intoReputation(name, "9", feishu, "0", "", urls, "1", "1");
	//						OracleOpreater.intoReputation(name, "9", feishu, "0", "", urls, "1", "1");
							OracleOpreater.intoReputation(name, "9", feishu, "0", "", urls, "1", "1");
							
						}
						else{
							OracleOpreater.intoReputation(name, "9", "-1", "0", "", urls, "1", "1");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						OracleOpreater.intoReputation(name, "9", "-1", "0", "", urls, "1", "1");
					}
					try {
						if (pinglun != null &&! pinglun.equals("")) {
							pinglun = pinglun.replaceAll("\\D+", "");
							if (pinglun != null && !pinglun.equals("")) {
	//							OracleOpreater.intoReputation(name, "9", pinglun, "0", "", urls, "1", "2");
								OracleOpreater.intoReputation(name, "9", pinglun, "0", "", urls, "1", "2");
								
							}
							else{
								OracleOpreater.intoReputation(name, "9", "-1", "0", "", urls, "1", "2");
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						OracleOpreater.intoReputation(name, "9", "-1", "0", "", urls, "1", "2");
					}
	//				return;
	//			}
			}
		
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
		}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimingTime(0, 30, 0);

//		runstatic();
	}

}
