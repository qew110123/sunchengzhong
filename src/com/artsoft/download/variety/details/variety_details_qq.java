package com.artsoft.download.variety.details;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_DIM_PLATFORM;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class variety_details_qq {
	
	
	public static void openstatic(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
//		if (strHtml == null || strHtml.equals("")) {
//			return false;
//		}

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
				
				danjibofangliang(strmainurl,name);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		String tt = doc.select("span.txt_01").select("em.strong").first().text();
		

	}
	
	
	
	private static void danjibofangliang(String urlMain,String name) {
		// TODO Auto-generated method stub
		TEM_DIM_PLATFORM platform=new TEM_DIM_PLATFORM();
//		playtv.setTvplay_url(urlBranch);
		
		platform.setUrl(urlMain);
		platform.setTvplayUrl(urlMain);
		platform.setSource(3);
		platform.setDataType(1);
		
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		
		if (name.equals("")||name==null) {
			
			 name = HtmlAnalyze.getTagText(strHtml, "<h3 class=\"player_title\">", "</h3>");
		}
		
		platform.setTvplayName(name);

		String classstr = ""; // 类型:
		String daoyanAlltext = HtmlAnalyze.getTagText(strHtml, "class=\"tag_list\">", "</ul>", true, 0);
		String[] daoyanlists = daoyanAlltext.split("</li>");
		int i = 0;
		for (String stringtext : daoyanlists) {
			// String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "itemprop=\"name\">", "<");
			if (!(textss.equals(""))) {
				classstr = classstr + textss;
			}
			if (daoyanlists.length != 1 && i + 1 < daoyanlists.length) {
				classstr = classstr + "/";
			}

			i += 1;
		}
		
		if (classstr.equals("")) {
			daoyanAlltext = HtmlAnalyze.getTagText(strHtml, "类型：", "</span>", true, 0);
			daoyanlists = daoyanAlltext.split("/a>/<a");
			i = 0;
			for (String stringtext : daoyanlists) {
				// String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"",
				// "\"");
				String textss = HtmlAnalyze.getTagText(stringtext, "cover.bread_2\">", "<");
				if (!(textss.equals(""))) {
					classstr = classstr + textss;
				}
				if (daoyanlists.length != 1 && i + 1 < daoyanlists.length) {
					classstr = classstr + "/";
				}

				i += 1;
			}
		}

		System.out.println(classstr);

//		playtv.setSubject(classstr);
//		platform.setSubjectName(classstr);
		platform.setSubjectName(classstr);

		String daoyan = "";// 
		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "主持：", "</ul>", true, 0);
		String[] daoyanlist = daoyanAll.split("</span>");
		i = 0;
		for (String stringtext : daoyanlist) {
			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss = HtmlAnalyze.getTagText(stringtext, "itemprop=\"name\" >", "<");
			if (textss.equals("")) {

				textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\"");
			}
			// daoyan=daoyan+textss+"|"+urlss;
			if (!(urlss.equals("") && textss.equals(""))) {
				daoyan = daoyan + textss + "|" + urlss;
			}
			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
				daoyan = daoyan + ",";
			}

			i += 1;
		}
		System.out.println(daoyan);
		platform.setMajorActors(daoyan);
//		playtv.setDirector(daoyan);

//		String niandai = ""; // 年代
//		niandai = HtmlAnalyze.getTagText(strHtml, "年份：", "</span>");
//		playtv.setShoot_time(niandai);
		
//		String MAJOR_ACTORS = ""; // 主持：
//		MAJOR_ACTORS = HtmlAnalyze.getTagText(strHtml, "主持：", "</ul>");
////		playtv.setShoot_time(MAJOR_ACTORS);
//		platform.setMajorActors(MAJOR_ACTORS);

		String diqu = ""; // 地区
		diqu = HtmlAnalyze.getTagText(strHtml, "地区：", "</span>");
		platform.setProduceArea(diqu);

//		String yanyuan = "";// 演员
//		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：</span>", "</ul>", true, 0);
//		String[] yanyuanlist = yanyuanAll.split("</li><li");
//		i = 0;
//		for (String stringtext : yanyuanlist) {
//			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//			String textss = HtmlAnalyze.getTagText(stringtext, "itemprop=\"name\" >", "<");
//			if (!(urlss.equals("") && textss.equals(""))) {
//				yanyuan = yanyuan + textss + "|" + urlss;
//			}
//			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
//				yanyuan = yanyuan + ",";
//			}
//
//			i += 1;
//		}
//		if (yanyuan.equals("")) {
//			yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：", "</dd>", true, 0);
//			yanyuanlist = yanyuanAll.split("/a>/<a");
//			i = 0;
//			for (String stringtext : yanyuanlist) {
//				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//				String textss = HtmlAnalyze.getTagText(stringtext, "title=\"", "\">");
//				if (!(urlss.equals("") && textss.equals(""))) {
//					yanyuan = yanyuan + textss + "|" + urlss;
//				}
//				if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
//					yanyuan = yanyuan + ",";
//				}
//
//				i += 1;
//			}
//
//		}
//		System.out.println(yanyuan);
//		playtv.setMajor_actors(yanyuan);

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"intro_full\">", "</p>");
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "itemprop=\"abstract\">", "</p>");
		}

		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"detail_all\">", "</p>");
		}

		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"detail\">", "</p>");
		}
		System.out.println(detail);

//		playtv.setBasic_info(detail);
		platform.setBasicInfo(detail);
//		platform.setChannelName(TVSTATION);
		OracleOpreater.intoPLATFORM(platform);

		// playtv.setClassnum(3);

//		playtv.setSOURCE(3);
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
//		return playtv;
		
		
		
		
		
	}
	
	public static int  Stringnum( String numString){
		int numIn=0;
		if (numString==null||numString.equals("")) {
			numString="-1";
			numIn=0;
		}
		if (numString.contains("亿")) {
			numIn=(int) ((Double.parseDouble(numString.replace("亿", "")))*100000000);
		}else{
			
		
			if (numString.contains("万")) {
				numIn=(int) (Double.parseDouble(numString.replace("万", ""))*10000);
			}else{
				numIn=(int) Double.parseDouble(numString);
			}
		}
		
		
		return numIn;
		
	}



	private static void openordor() {
		// TODO Auto-generated method stub
		String url="";
		for (int i = 0; i <= 1160; i = i + 20) {
			url = "http://v.qq.com/x/varietylist/?itype=-1&offset=" + i + "&isource=-1&sort=4";
			
			openstatic(url);
		}
		
	}
	
	public static void runstatic() {
		CommonUtil.setLog("" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		openordor();

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
				}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
			}

	/**
	 * 腾讯综艺基本信息
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TimingTime(1, 59, 59);
		runstatic();
//		openordor();
//		danjibofangliang("http://v.qq.com/cover/b/bkj3856wxukmjcj.html");
	}

}
