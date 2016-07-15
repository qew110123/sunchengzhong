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

public class variety_details_Iqiyi {
	
	
	public static void IqiyiBranch(String urlall){
		String strHtml = DownloadUtil.getHtmlText(urlall, 1000 * 30, "UTF-8", null, null);
		if (strHtml.contains("小头图信息区")) {
			System.out.println("小头图信息区");
			iQiYiSmallBranch(urlall);
		}
		else{
			System.out.println("大头图信息区");
			iQiYibigBranch(urlall);
		}
	}
	
	
	public static void iQiYibigBranch(String urlBranch) {

//		TvPlay playtv = new TvPlay();
		TEM_DIM_PLATFORM platform=new TEM_DIM_PLATFORM();
//		playtv.setTvplay_url(urlBranch);
		
		platform.setUrl(urlBranch);
		platform.setTvplayUrl(urlBranch);
		platform.setSource(2);
		platform.setDataType(1);
		
//		System.out.println(name);
//		playtv.setTvplay_name(name);
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		String name=HtmlAnalyze.getTagText(strHtml, "data-subscribe-tvname=\"", "\"").replace("\r\n", "").replace(" ", "");
		platform.setTvplayName(name);
		

		// String name = HtmlAnalyze.getTagText(strHtml, "<meta
		// name=\"keywords\" content=\"", "\" /> ");
		
		String PRODUCTION_AREA="";
		PRODUCTION_AREA=HtmlAnalyze.getTagText(strHtml, "地区：", "</p>").replace("\r\n", "").replace(" ", "");
//		playtv.setProduction_area(PRODUCTION_AREA);
		platform.setProduceArea(PRODUCTION_AREA);
		
		String MAJOR_ACTORS="";
		MAJOR_ACTORS=HtmlAnalyze.getTagText(strHtml, "主持人：", "</p>").replace("\r\n", "").replace(" ", "");
//		playtv.setProduction_area(PRODUCTION_AREA);
		platform.setMajorActors(MAJOR_ACTORS);
		
		String SUBJECT="";
		SUBJECT=HtmlAnalyze.getTagText(strHtml, "类型：", "</p>").replace("\r\n", "");
		SUBJECT=SUBJECT.replace("\r\n", "").replace(" ", "");
		if (!SUBJECT.contains("/")) {
			SUBJECT="";
			String classstrall = HtmlAnalyze.getTagText(strHtml, "类型：", "</p>", true, 0);
//			System.out.println(classstrall);
			int i = 0;
			String[] classstrlist = classstrall.replace(" ", "").split("\r\n\r\n");
			for (String string : classstrlist) {
				String textss = HtmlAnalyze.getTagText(string, ">", "<");
				if (!textss.equals("null")&&!textss.equals("")) {
					SUBJECT = SUBJECT + textss + "/";
					if (classstrlist.length != 1 && i + 1 < classstrlist.length) {
						SUBJECT = SUBJECT ;
					}
				}
				

				i += 1;
			}
		}
		
		
		
//		playtv.setSubject(SUBJECT);
		platform.setSubjectName(SUBJECT);
		
		
//		String daoyan = "";// 导演
//		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "id=\"datainfo-director-list\">", "</p>", true, 0);
//		String[] daoyanlist = daoyanAll.split("</span>/");
//		int i = 0;
//		for (String stringtext : daoyanlist) {
//			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
//			// daoyan=daoyan+textss+"|"+urlss;
//			if (!(urlss.equals("") && textss.equals(""))) {
//				daoyan = daoyan + textss + "|" + urlss;
//			}
//			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
//				daoyan = daoyan + ",";
//			}
//
//			i += 1;
//		}
//		System.out.println(daoyan);
//		playtv.setDirector(daoyan);
		
//		String DIRECTOR="";
//		DIRECTOR=HtmlAnalyze.getTagText(strHtml, "导演：", "</p>").replace("\r\n", "").replace(" ", "");
//		playtv.setDirector(DIRECTOR);
		
		
		
		String BASIC_INFO="";
		BASIC_INFO=HtmlAnalyze.getTagText(strHtml, "简介：", "收起").replace("\r\n", "").replace(" ", "");
//		playtv.setDirector(BASIC_INFO);
		platform.setBasicInfo(BASIC_INFO);
		

//		String yanyuan = "";// 演员
//		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：", "</p>", true, 0);
//		String[] yanyuanlist = yanyuanAll.split("/a>");
//		int i = 0;
//		for (String stringtext : yanyuanlist) {
//			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
//			if (!(urlss.equals("") && textss.equals(""))) {
//				yanyuan = yanyuan + textss + "|" + urlss;
//			}
//			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
//				yanyuan = yanyuan + ",";
//			}
//
//			i += 1;
//		}
//		System.out.println(yanyuan);
//		playtv.setMajor_actors(yanyuan);

		// String videoId = HtmlAnalyze.getTagText(strHtml, "albumId:", ",");
		//
		// String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId +
		// "/";
		// String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30,
		// "UTF-8", null, null);
		// System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, ":",
		// "}"));
		// try {
		// OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch,
		// "3", "0");
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "style=\"display: none;\"><span class=\"c-999\">简介：</span>", "</span>");
		System.out.println(detail);
		
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"c-999\">简介：</span>", "</span>");
		}
		platform.setBasicInfo(detail);
//		playtv.setBasic_info(detail);

		// playtv.setClassnum(2);
//		playtv.setSOURCE(2);
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		
//		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
//		platform.setChannelName(tvName);
		OracleOpreater.intoPLATFORM(platform);

	}
	
	private static void iQiYiSmallBranch(String urlBranch) {
		// TODO Auto-generated method stub
		
//		TvPlay playtv = new TvPlay();
		TEM_DIM_PLATFORM platform=new TEM_DIM_PLATFORM();
//		playtv.setTvplay_url(urlBranch);
		
		platform.setUrl(urlBranch);
		platform.setTvplayUrl(urlBranch);
		platform.setSource(2);
		platform.setDataType(1);
		
//		playtv.setTvplay_url(urlBranch);
//		System.out.println(name);
//		playtv.setTvplay_name(name);
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		
		String name=HtmlAnalyze.getTagText(strHtml, "data-subscribe-tvname=\"", "\"").replace("\r\n", "").replace(" ", "");
		platform.setTvplayName(name);
		// String name = HtmlAnalyze.getTagText(strHtml, "<meta
		// name=\"keywords\" content=\"", "\" /> ");
		
		String PRODUCTION_AREA="";
		PRODUCTION_AREA=HtmlAnalyze.getTagText(strHtml, "地区：", "</em>").replace("\r\n", "").replace(" ", "");
//		playtv.setProduction_area(PRODUCTION_AREA);
		platform.setProduceArea(PRODUCTION_AREA);
		
		
		
		String MAJOR_ACTORS="";
		MAJOR_ACTORS=HtmlAnalyze.getTagText(strHtml, "主持人：", "</em>").replace("\r\n", "").replace(" ", "");
//		playtv.setProduction_area(PRODUCTION_AREA);
		platform.setProduceArea(MAJOR_ACTORS);
		
		
		
		String SUBJECT="";
		SUBJECT=HtmlAnalyze.getTagText(strHtml, "类型：", "</em>");
		SUBJECT=SUBJECT.replace("\r\n", "").replace(" ", "");
//		playtv.setSubject(SUBJECT);
		platform.setSubjectName(SUBJECT);
		
		
//		String daoyan = "";// 导演
//		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "id=\"datainfo-director-list\">", "</p>", true, 0);
//		String[] daoyanlist = daoyanAll.split("</span>/");
//		int i = 0;
//		for (String stringtext : daoyanlist) {
//			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
//			// daoyan=daoyan+textss+"|"+urlss;
//			if (!(urlss.equals("") && textss.equals(""))) {
//				daoyan = daoyan + textss + "|" + urlss;
//			}
//			if (daoyanlist.length != 1 && i + 1 < daoyanlist.length) {
//				daoyan = daoyan + ",";
//			}
//
//			i += 1;
//		}
//		System.out.println(daoyan);
//		playtv.setDirector(daoyan);
		
//		String DIRECTOR="";
//		DIRECTOR=HtmlAnalyze.getTagText(strHtml, "导演：", "</em>").replace("\r\n", "").replace(" ", "");
//		playtv.setDirector(DIRECTOR);
		
		

//		String yanyuan = "";// 演员
//		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "主演：", "</em>", true, 0);
//		String[] yanyuanlist = yanyuanAll.split("/a>");
//		int i = 0;
//		for (String stringtext : yanyuanlist) {
//			String urlss = HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
//			String textss = HtmlAnalyze.getTagText(stringtext, ">", "<");
//			if (!(urlss.equals("") && textss.equals(""))) {
//				yanyuan = yanyuan + textss + "|" + urlss;
//			}
//			if (yanyuanlist.length != 1 && i + 1 < yanyuanlist.length) {
//				yanyuan = yanyuan + ",";
//			}
//
//			i += 1;
//		}
//		System.out.println(yanyuan);
//		playtv.setMajor_actors(yanyuan);

		// String videoId = HtmlAnalyze.getTagText(strHtml, "albumId:", ",");
		//
		// String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId +
		// "/";
		// String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30,
		// "UTF-8", null, null);
		// System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, ":",
		// "}"));
		// try {
		// OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch,
		// "3", "0");
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"showMoreText\" data-moreorless=\"moreinfo\" style=\"display: none;\">", "收起");
		System.out.println(detail);
		
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "简介：</em>", "</span>");
		}
		if (!detail.equals("")) {
			detail=detail.replace("简介：", "");
//			playtv.setBasic_info(detail);
			platform.setBasicInfo(detail);
		}

		// playtv.setClassnum(2);
//		playtv.setSOURCE(2);
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		
//		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
		OracleOpreater.intoPLATFORM(platform);
		
	}

	
	
	/**
	 * 2016年5月31日18:03:17
	 * 进行整体数据的更细
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectyoukuvariety(date_date,"2");
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			try {
				IqiyiBranch(listTemp.get(0));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	public static void runstatic() {
		CommonUtil.setLog("优酷总数" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TimingTime(1, 59, 59);
		runstatic();
//		openordor();
//		IqiyiBranch("http://www.iqiyi.com/a_19rrgiarlt.html#vfrm=2-4-0-1");
	}

}
