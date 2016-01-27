package com.artsoft.download.Movies.Details;

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

public class DownHunantvDetails {

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
		
		TvPlay playtv=new TvPlay();
		playtv.setTvplay_url(mainUrl);
		
		playtv.setTvplay_name(tyPlayName);
		
		String yanyuan="";// 演员
		String yanyuanAll= HtmlAnalyze.getTagText(strHtml, "主演：</em>", "</p>",true,0);
		String[] yanyuanlist=yanyuanAll.split(" / ");
		int i=0;
		for (String stringtext : yanyuanlist) {
			String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss=HtmlAnalyze.getTagText(stringtext, ">", "<");
			yanyuan=yanyuan+textss+"|"+urlss;
			if (yanyuanlist.length!=1&&i+1<yanyuanlist.length) {
				yanyuan=yanyuan+",";
			}
			
			i+=1;
		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);
		
		String daoyan="";// 导演
		String daoyanAll= HtmlAnalyze.getTagText(strHtml, "导演：</em>", "</p>",true,0);
		String[] daoyanlist=daoyanAll.split(" / ");
		 i=0;
		for (String stringtext : daoyanlist) {
			String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss=HtmlAnalyze.getTagText(stringtext, ">", "<");
			daoyan=daoyan+textss+"|"+urlss;
			if (daoyanlist.length!=1&&i+1<daoyanlist.length) {
				daoyan=daoyan+",";
			}
			
			i+=1;
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);
		
		String classstr = ""; // 类型:
		classstr = HtmlAnalyze.getTagText(strHtml, "类型：", "</p>");
		System.out.println(classstr=classstr.replaceAll(" 					", ""));
		playtv.setSubject(classstr);
		
		String diqu=""; //地区
		diqu =HtmlAnalyze.getTagText(strHtml, "地区：", "</p>");
		playtv.setProduction_area(diqu);
		
		String detail="";
		detail=HtmlAnalyze.getTagText(strHtml, "简介：", "</span>");
		System.out.println(detail);
		if (detail==null||detail.equals("")||detail.equals("null")) {
			detail=HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		playtv.setBasic_info(detail);
		
		playtv.setClassnum(7);
		OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		

	}

	public static void hunanBranch(String idnum, String tyPlayName, String strVolumes, String playUrl) {
		// String tyPlayName = "";
		String serNumber = strVolumes.replaceAll("第", "").replaceAll("集", "");
		String source = "7";
		String playAmount = "";
		String vodeoType = "0";
		String palydate = "";
		// String playUrl = urlnew;
		String tvType = "3";
		// String realUrl = "";
		//http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn//dynamicinfo?callback=jQuery182005345956212840974_1453262019737&vid=2946416&_=1453262020460
		String urlnew = "http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn//dynamicinfo?callback=jQuery182005345956212840974_1453262019737&vid="
				+ idnum + "&_=1453262020460";
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

//			OracleOpreater.intoPlayAmont(tyPlayName, serNumber, source, playAmount, vodeoType, palydate, urlnew, tvType,
//					playUrl);
			OracleOpreater.intoReputation(tyPlayName, "7", playAmount, "0", "", playUrl, "3", "0");
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
			mainurl(urlstr);
		}
	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 1; i <= 63; i++) {
			String url = "http://list.hunantv.com/3/----------" + i + "---.html";
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
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// for (int i = 0; i < 25; i++) {
		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p="+i+"&s=1";
		// mainurl(mainUrl);
		// }
		// String mainUrl = "http://www.hunantv.com/v/2/104822/f/1821553.html";
		// mainurl(mainUrl);
		// while (true) {
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":开始");
		//
		// for (int i = 1; i <= 18; i++) {
		// String url = "http://list.hunantv.com/2/----------" + i + "---.html";
		// System.out.println(url);
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" +
		// url);
		// mainmore(url);
		// }
		// try {
		// Thread.sleep(1000 * 60 * 60 * 22);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") +
		// ":结束");
		// }

//		TimingTime(00, 59, 59);
		runstatic();
	}

}
