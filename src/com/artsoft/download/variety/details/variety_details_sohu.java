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
import com.artsoft.bean.TvPlay;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class variety_details_sohu {
	
	public static void urljson(String urlBranch){
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		JSONObject sohujson = new JSONObject();
		sohujson = JSONObject.fromObject(strHtml);
		JSONArray sohujsonArray = new JSONArray();
		sohujsonArray= (JSONArray) sohujson.get("videos");
		for (Object object : sohujsonArray) {
			String tyPlayName="";
			String source="4";
			String playAmount="";
			String vodeoType="0";
			String palydate="";
			String tvType="2";
			String realUrl="";
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(tyPlayName=(String) objectobject.get("title"));
			System.out.println(realUrl=(String) objectobject.get("url"));
			System.out.println(playAmount=String.valueOf(objectobject.get("playCount")));
			OracleOpreater.intoPlayAmont(tyPlayName, source, playAmount, vodeoType, palydate, realUrl, tvType, realUrl);
		}
		
	}
	
	public static void sohuBranch(String urlBranch) {
//		TvPlay playtv = new TvPlay();
		TEM_DIM_PLATFORM platform=new TEM_DIM_PLATFORM();
//		playtv.setTvplay_url(urlBranch);
		
		platform.setUrl(urlBranch);
		platform.setTvplayUrl(urlBranch);
		platform.setSource(4);
		platform.setDataType(1);
//		playtv.setTvplay_url(urlBranch);

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		// <h2>电视剧：<span class="vname">
		String name = HtmlAnalyze.getTagText(strHtml, "<title>", "</title>");
		System.out.println(name);
//		playtv.setTvplay_name(name);
		platform.setTvplayName(name);

		// <span>别名：</span>
//		String ALIAS_CN = HtmlAnalyze.getTagText(strHtml, "<span>别名：</span>", "</li>");
//		if (ALIAS_CN!=null) {
//			ALIAS_CN=ALIAS_CN.replace(" ", "");
//		}
//		playtv.setAlias_cn(ALIAS_CN);
		
		

//		String times = ""; // 上映:
//		times = HtmlAnalyze.getTagText(strHtml, "年份：", "</span>");
//		// System.out.println(times=times.replaceAll("-", ""));
//		if (times.equals("")) {
//
//			times = HtmlAnalyze.getTagText(strHtml, "<span>上映时间：</span>", "</li>");
//		}
//		playtv.setShow_date(times);

		
		String classstr = ""; // 类型:
		classstr = HtmlAnalyze.getTagText(strHtml, "类型：", "</li>");
		System.out.println(classstr = classstr.replaceAll(" 					", "").replace("\n", "").replace("\t", "").replace("\r", ""));
//		playtv.setSubject(classstr);
		platform.setSubjectName(classstr);

//		String shichang = ""; // 时长
//		shichang = HtmlAnalyze.getTagText(strHtml, "时长：", "</span>");
//		playtv.setTime_length(shichang);
		
		
//		String TVSTATION = ""; // 时长
//		TVSTATION = HtmlAnalyze.getTagText(TVSTATION, "时长：", "</span>");
////		playtv.setTime_length(TVSTATION);
//		platform.setTVSTATION(TVSTATION);
		
		String TVSTATION = ""; //播出
		TVSTATION = HtmlAnalyze.getTagText(strHtml, "播出：", "</li>");
//		playtv.setTime_length(TVSTATION);
		platform.setTVSTATION(TVSTATION);
		platform.setChannelName(TVSTATION);
		String diqu = ""; // 地区
		diqu = HtmlAnalyze.getTagText(strHtml, "地区：", "</span>");
		if (diqu.equals("") || diqu == null || diqu.equals("null")) {
			diqu = HtmlAnalyze.getTagText(strHtml, "地区：", "</li>");
		}
//		playtv.setProduction_area(diqu);
		platform.setProduceArea(diqu);
		

//		String daoyan = "";// 导演
////		daoyan = HtmlAnalyze.getTagText(strHtml, "导演：", "</span>");
//		if (daoyan.equals("") || daoyan == null || daoyan.equals("null")) {
////			String daoyan = "";// 导演
//			String daoyanAll = HtmlAnalyze.getTagText(strHtml, "导演：</span>", "</li>", true, 0);
//			String[] daoyanlist = daoyanAll.split(" /");
//			int i = 0;
//			for (String stringtext : daoyanlist) {
//				String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//				String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
//				daoyan = daoyan + textss + "|" + urlss;
//				if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
//					daoyan = daoyan + ",";
//				}
//
//				i += 1;
//			}
//			
//		}
//		System.out.println(daoyan);
//		playtv.setDirector(daoyan);
		

		String yanyuan = "";// 演员
//		yanyuan = HtmlAnalyze.getTagText(strHtml, "主演：", "</p>");
//		yanyuan = yanyuan.replaceAll("\r\n\t", "").replaceAll("\t", "").replaceAll("/", "|,");
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主持人：</span>", "</li>", true, 0);
		String[] yanyuanlist = yanyuanAll.split("class=\"mLR6\">/</span>");
		int i = 0;
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
//		playtv.setMajor_actors(yanyuan);
		
		platform.setMajorActors(yanyuan);
		

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "<p class=\"all_info\">", "</p>");
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"full_intro \" style=\"display:none\">", "</span>");
		}
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short_intro hide\">", "</span>");
		}
		//
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "class=\"full_intro\" style=\"display: none\">", "</span>");
		}
		
		if (detail!=null) {
			detail=detail.replace("&nbsp;", "");
		}
		System.out.println(detail);
//		playtv.setBasic_info(detail);
		platform.setBasicInfo(detail);

//		playtv.setClassnum(4);
//		playtv.setSOURCE(4);

//		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
		OracleOpreater.intoPLATFORM(platform);


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
		
		for (int i = 1; i <= 57; i++) {
			sohuMain("http://so.tv.sohu.com/list_p1106_p2_p3_p4_p5_p6_p7_p8_p9_p10" + i + "_p11_p12_p13.html");

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

	

	private static void groupby() {
		// TODO Auto-generated method stub
		
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectyoukuvariety(date_date,"4");
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			try {
				sohuBranch(listTemp.get(0));
//				DownloadYouku.youkuBranch(listTemp.get(0));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TimingTime(1, 59, 59);
//		runstatic();
		groupby();
//		sohuMain("http://so.tv.sohu.com/list_p1106_p2_p3_p4_p5_p6_p7_p8_p9_p107_p11_p12_p13.html");
//		sohuBranch("http://tv.sohu.com/item/MTIwNTUwNw==.html");
//		urljson("http://tv.sohu.com/item/VideoServlet?source=sohu&id=4735&year=2015&month=0&page=1");
//		sohuBranch("http://tv.sohu.com/item/MTE5ODEzMg==.html");
	}

}
