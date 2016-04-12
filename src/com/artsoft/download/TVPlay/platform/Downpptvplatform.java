package com.artsoft.download.TVPlay.platform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Downpptvplatform {

	public static void urlBranch(String urlBranch) {
		TvPlay playtv = new TvPlay();
		// System.out.println(urlBranch);
		playtv.setTvplay_url(urlBranch);

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);

		String name = "";// 名称
		System.out.println(name = doc.select("cite").text());
		playtv.setTvplay_name(name);

		String shichang = ""; // 时长
		shichang = HtmlAnalyze.getTagText(strHtml, "片长：", "</li>");
		playtv.setTime_length(shichang);

		String times = ""; // 上映:
		times = HtmlAnalyze.getTagText(strHtml, "上映：", "</li>");
		// System.out.println(times=times.replaceAll("-", ""));
		playtv.setShoot_time(times);

		String classstr = ""; // 类型:
		classstr = HtmlAnalyze.getTagText(strHtml, "类型：", "</li>");
		System.out.println(classstr = classstr.replaceAll(" 					", ""));
		playtv.setSubject(classstr);

		String diqu = ""; // 地区
		diqu = HtmlAnalyze.getTagText(strHtml, "地区：", "</li>");
		playtv.setProduction_area(diqu);

		String daoyan = "";// 导演
		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "导演：", "</li>", true, 0);
		String[] daoyanlist = daoyanAll.split("a><a");
		int i = 0;
		for (String stringtext : daoyanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			daoyan = daoyan + textss + "|" + urlss;
			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
				daoyan = daoyan + ",";
			}

			i += 1;
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);

		String yanyuan = "";// 演员
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：", "</li>", true, 0);
		String[] yanyuanlist = yanyuanAll.split("a><a");
		i = 0;
		for (String stringtext : yanyuanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
			yanyuan = yanyuan + textss + "|" + urlss;
			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
				yanyuan = yanyuan + ",";
			}

			i += 1;
		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "class=\"intro j_intro\">", "</div>");
		System.out.println(detail);
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		playtv.setBasic_info(detail);

		playtv.setClassnum(6);

		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);

		playtv.setSOURCE(6);

		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);

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
		// System.out.println(strHtml);
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("a.detailbtn");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String name = "";
			String score = "";
			String url = "";
			System.out.println(url = link.attr("href"));
			// System.out.println(score = link.select("em").text());
			urlBranch(url);
		}
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 1; i < 80; i++) {
			String mainUrl = "http://list.pptv.com/channel_list.html?page=" + i + "&type=2&sort=1";
			mainurl(mainUrl);
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
		// for (int i = 0; i < 25; i++) {
		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p="+i+"&s=1";
		// mainurl(mainUrl);
		// }
		// String
		// mainUrl="http://list.pptv.com/channel_list.html?page=1&type=2&sort=1";
		// mainurl(mainUrl);
		// while (true) {
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":开始");
		// for (int i = 1; i < 70; i++) {
		// String mainUrl = "http://list.pptv.com/channel_list.html?page=" + i +
		// "&type=2&sort=1";
		// mainurl(mainUrl);
		// }
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":结束");
		// try {
		// Thread.sleep(1000*60*60*23);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		// TimingTime(23, 59, 59);
		runstatic();
	}

}
