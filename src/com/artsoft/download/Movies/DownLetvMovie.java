package com.artsoft.download.Movies;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DownLetvMovie {

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
		// System.out.println(strHtml);
		String str = strHtml;
		String[] strarray = str.split("\"actor\":");
		for (int i = 0; i < strarray.length; i++) {
			String urlBranch = "";
			String Amount = "";
			String name = "";
			String score = "";
			// System.out.println(strarray[i]);
			System.out.println(Amount = HtmlAnalyze.getTagText(strarray[i], "playCount\":\"", "\""));
			System.out.println(score = HtmlAnalyze.getTagText(strarray[i], "rating\":\"", "\""));
			System.out.println(name = HtmlAnalyze.getTagText(strarray[i], "\"name\":\"", "\""));
//			System.out.println(DETAIL_URL=HtmlAnalyze.getTagText(strarray[i], "\"vids\":\"", "\""));
			String DETAIL_URL="";
			String url=(String) HtmlAnalyze.getTagText(strarray[i], "\"vids\":\"", "\"");
			System.out.println(url="http://www.letv.com/ptv/vplay/"+url.split(",")[0]+".html");
//			playtv.setTvplay_url(url);
			DETAIL_URL=url;
			if (urlBranch != null || Amount != null || name != null || score != null || urlBranch != "" || Amount != ""
					|| name != "" || score != "") {
				try {
					if (name != null && Amount != null && mainUrl != null) {
						OracleOpreater.intoReputationAndDETAIL_URL(name, "5", Amount, "0", "", mainUrl, "3", "0",DETAIL_URL);
					}
					if (name != null && score != null && mainUrl != null) {
						OracleOpreater.intoReputationAndDETAIL_URL(name, "5", score, "0", "", mainUrl, "3", "1",DETAIL_URL);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 0; i < 30; i++) {
			//http://list.letv.com/apin/chandata.json?c=1&d=1&md=&o=9&p=2&s=1
			String mainUrl = "http://list.letv.com/apin/chandata.json?c=1&d=1&md=&o=9&p=" + i + "&s=1";
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
//		for (int i = 0; i < 30; i++) {
//			String mainUrl = "http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=" + i + "&s=1";
//			mainurl(mainUrl);
		
//		}
//		TimingTime(23, 59, 59);
		TimingTime(21, 59, 59);
//		runstatic();
	}
	

}
