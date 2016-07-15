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
//import com.artsoft.bean.TEM_DIM_PLATFORM;
import com.artsoft.bean.TvPlay;
import com.artsoft.download.TVPlay.DownloadYouku;
import com.artsoft.oracle.OracleNetwork;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class variety_details_YouKu {
	
	
	public static void youkuBranch(String urlall){
		String url=urlall;
		String tvplayNamehtml=DownloadUtil.getHtmlText(urlall, 30000, "UTF-8", null, null);
		String tvplayName="";
		tvplayName= HtmlAnalyze.getTagText(tvplayNamehtml, "class=\"name\">", "</span>");
		
		TEM_DIM_PLATFORM platform=new TEM_DIM_PLATFORM();
		platform.setTvplayUrl(urlall);
		platform.setUrl(urlall);
		platform.setTvplayName(tvplayName);
		
		// urlBranch="http://www.youku.com/show_page/id_zd56886dc86fc11e3a705.html";
		String strHtml = DownloadUtil.getHtmlText(urlall, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlall, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		Document doc = Jsoup.parse(strHtml);
		// Elements links = doc.select("div.yk-col3");

		/**
		 * 总播放: 评论: 顶:
		 */

		String name = "";// 名称
		System.out.println(name = doc.select("span.name").text());
//		playtv.setTvplay_name(name);
		String Amount = "";// 播放量
		System.out.println(Amount = doc.select("span.play").text());
		Amount = Amount.replaceAll("总播放:", "").replaceAll(",", "");

		String comment = ""; // 评论
		System.out.println(comment = doc.select("span.comment").text());
		comment = comment.replaceAll("评论:", "").replaceAll(",", "");

		String answer = ""; // 顶
		System.out.println(answer = doc.select("span.increm").text());
		answer = answer.replaceAll("顶:", "").replaceAll(",", "");

		String score = ""; // 评分

		score = HtmlAnalyze.getTagText(strHtml, "<label>评分:</label>", "<style type=\"text/css\">");
		// Pattern pattern = Pattern.compile("[0-9]*");
		// Matcher isNum = pattern.matcher(score);
		// score=doc.select("span.ratingstar").text();
		if (score.contains("\r")) {
			score = HtmlAnalyze.getTagText("#" + score, "#", "\r");
		}
		// if (!isNum.matches()) {
		// score=doc.select("span.ratingstar").text().replace("评分:", "");
		// }
		System.out.println(score);

		String bieming = "";// 别名

		bieming = HtmlAnalyze.getTagText(strHtml, "<label>别名:</label>", "</span>");
		System.out.println(bieming = bieming.replace("	", ""));
//		playtv.setAlias_en(bieming);
		
		String shichang = ""; // 时长
		shichang = HtmlAnalyze.getTagText(strHtml, "<label>时长:</label>", "</span>");
//		playtv.setTime_length(shichang);

		String times = ""; // 上映:
		times = HtmlAnalyze.getTagText(strHtml, "上映:</label>", "</span>");
		// System.out.println(times=times.replaceAll("-", ""));
//		playtv.setShow_date(times);

		String niandai = ""; // 年代
		niandai = HtmlAnalyze.getTagText(strHtml, "class=\"pub\">", "</span>");
//		playtv.setShoot_time(niandai);

		String diqu = ""; // 地区
		diqu = HtmlAnalyze.getTagText(strHtml, "<label>地区:</label>", "</span>");
//		playtv.setProduction_area(diqu);
		platform.setProduceArea(diqu);
		
		String classstr = ""; // 类型:
		classstr = HtmlAnalyze.getTagText(strHtml, "<label>类型:</label>", "</span>");
		classstr=classstr.replace("\r", "").replace("\n", "").replace("\t", "");
		
		System.out.println(classstr = classstr.replaceAll(" 					", ""));
//		playtv.setSubject(classstr);
		platform.setSubjectName(classstr);
		

		String peopleste = ""; // ren:
		peopleste = HtmlAnalyze.getTagText(strHtml, "<label>主演:</label>", "总播放:");
		System.out.println(peopleste = peopleste.replaceAll(" 					", ""));
		
		String CHANNEL_NAME = ""; // 播出:
		CHANNEL_NAME = HtmlAnalyze.getTagText(strHtml, "<label>播出:</label>", "</span>");
		System.out.println(CHANNEL_NAME = CHANNEL_NAME.replaceAll(" 					", ""));
//		playtv.setSubject(classstr);
		platform.setChannelName(CHANNEL_NAME);
		
		String MAJOR_ACTORS = ""; // 播出:
		MAJOR_ACTORS = HtmlAnalyze.getTagText(strHtml, "<label>主持人:</label>", "</span>");
		MAJOR_ACTORS=MAJOR_ACTORS.replace("\r", "").replace("\n", "").replace("\t", "");
		System.out.println(MAJOR_ACTORS = MAJOR_ACTORS.replaceAll(" 					", ""));
//		playtv.setSubject(classstr);
		platform.setMajorActors(MAJOR_ACTORS);
		

		String daoyan = "";// 导演
		String daoyanAll = HtmlAnalyze.getTagText(strHtml, "<label>导演:</label>", "</span>", true, 0);
		String[] daoyanlist = daoyanAll.split(" /");
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
//		playtv.setDirector(daoyan);

		String yanyuan = "";// 演员
		String yanyuanAll = HtmlAnalyze.getTagText(strHtml, "<label>主演:</label>", "</span>", true, 0);
		String[] yanyuanlist = yanyuanAll.split(" /");
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
//		playtv.setMajor_actors(yanyuan);

		String detail = "";
		detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"long\" style=\"display:none;\">", "</span>");
		System.out.println(detail);
		if (detail == null || detail.equals("") || detail.equals("null")) {
			detail = HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
//		playtv.setBasic_info(detail);

		// playtv.setClassnum(1);
//		playtv.setSOURCE(1);
		// basic_info

		// OracleOpreater.intoTEM_NETWORK_TVPLAY_AMOUNT(name, 1,
		// Integer.parseInt(Amount), times, urlBranch, classstr, peopleste,
		// Integer.parseInt(comment));
		// OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
//		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
		platform.setSource(1);
		platform.setDataType(1);
		System.out.println(platform);
		OracleOpreater.intoPLATFORM(platform);
	}
	
	
	
	/**
	 * 2016年5月30日17:36:49
	 * 进行整体数据的更细
	 */
	private static void openordor() {
		// TODO Auto-generated method stub
		TimeTest tt = new TimeTest();
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String date_date = DownloadYouku.getBeforeAfterDate(newtime, -30);
		List<String> listArray =OracleNetwork.selectyoukuvariety(date_date,"1");
		for (Object Objstring : listArray) {
			List<String> listTemp = (List<String>) Objstring;
			System.out.println(listTemp.get(0));
			try {
				youkuBranch(listTemp.get(0));
//				DownloadYouku.youkuBranch(listTemp.get(0));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public static void runstatic() {
		CommonUtil.setLog("" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
//		openstatic();
//		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		openordor();
//		Thread.sleep(millis);
//		try {
////			System.out.println("等待2小时");
//			CommonUtil.setLog("当前时间:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			Thread.sleep(1000 * 60 * 60 * 2);
//			openstatic();
//			CommonUtil.setLog("优酷等待2小时" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			System.out.println("等待2小时");
//			CommonUtil.setLog("当前时间:" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//			Thread.sleep(1000 * 60 * 60 * 3);
//			openstatic();
//			CommonUtil.setLog("优酷等待3小时" + TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") );
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
//		youkuBranch("http://www.youku.com/show_page/id_z234cc978f0e411e583e8.html");
		runstatic();
	}

}
