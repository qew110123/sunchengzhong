package com.artsoft.download.maoyanandpiaofang;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_FILM_SCHEDULE;
import com.artsoft.oracle.OracleMovePiaoFang;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class maoyanall {
	
	
	
	
	
	
	
	public static void openstatic() {
		String datetext = TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"), -1).toString();
//		String urlMain = "http://piaofang.maoyan.com/?date=" + datetext;
//		openstatPaipian(urlMain, datetext);
		for (int i = -1; i > -547; i--) {
			datetext = TimeTest.getBeforeAfterDate(TimeTest.getNowTime("yyyy-MM-dd"), i).toString();
			try {
				openstaticPaipianShuJuquanguo("", datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				openstaticPaipianShuJu("", datetext);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	
	/**
	 * 2016年7月12日17:26:36
	 * @param urlMain
	 * @param datetext
	 * 进行数据的整体排片各个城市
	 */
	public static void openstaticPaipianShuJuquanguo(String urlMain, String datetext) {
		String url="http://pf.maoyan.com/show";
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
//			urlMain = "http://piaofang.maoyan.com/";
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

//		String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
//		System.out.println(data_date = data_date.replace("-", ""));

		Elements links = doc.getElementById("area").select("ul.table li a.react");

		for (Element link : links) {
			String City="";
			String datatype="";
			System.out.println(City=link.text());
			System.out.println(datatype=link.attr("data-type"));
			String Cityutf="";
			
			try {
				Cityutf = java.net.URLEncoder.encode(City, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//http://pf.maoyan.com/show?showDate=2016-07-06&periodType=0&cityType=4&cityName=%E5%85%B6%E5%AE%83%E5%9F%8E%E5%B8%82&showType=2
//			String cityurl="http://pf.maoyan.com/show?showDate="+datetext+"&periodType=0&cityType=0&cityName="+Cityutf+"&showType=2";
			String cityurl="http://pf.maoyan.com/show?showDate="+datetext+"&periodType=0&cityType="+datatype+"&cityName="+Cityutf+"&showType=2";
			String strcityHtml = "";
			if (strcityHtml == null || strcityHtml.equals("")) {
//				urlMain = "http://piaofang.maoyan.com/";
				strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
			}
			if (strcityHtml == null || strcityHtml.equals("")) {
				strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
			}
			
			Document docciy = Jsoup.parse(strcityHtml);

//			String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
//			System.out.println(data_date = data_date.replace("-", ""));

			Elements linkscty = docciy.getElementById("playPlan_table").select("ul");
			for (Element element : linkscty) {
//				System.out.println(element);
				TEM_FILM_SCHEDULE schedule=new TEM_FILM_SCHEDULE();
				schedule.setDataDate(datetext.replace("-", ""));
				schedule.setCollectionUrl(cityurl);
				schedule.setCityName(City);
				String name="";
				System.out.println(name=element.attr("data-name"));
				schedule.setTitle(name);
				String rate="";
				System.out.println(rate=element.attr("data-rate"));
				schedule.setFieldNumRate(rate);
				String ratenum="";
				System.out.println(ratenum=element.attr("data-number"));
				schedule.setFieldNum(ratenum);
				String id ="";
				System.out.println(id=HtmlAnalyze.getTagText(element.toString(), "detail:'", "'"));
				schedule.setFid(id);
				String elementurl="";
				System.out.println(elementurl="http://pf.maoyan.com"+HtmlAnalyze.getTagText(element.toString(), "href:'", "'"));
				schedule.setUrl(elementurl);
				OracleMovePiaoFang.intoTEM_FILM_SCHEDULE(schedule);
			}
			
//			playPlan_table
			
			
		}

	}
	
	
	
	
	/**
	 * 2016年7月12日17:26:36
	 * @param urlMain
	 * @param datetext
	 * 进行数据的整体排片各个城市
	 */
	public static void openstaticPaipianShuJu(String urlMain, String datetext) {
		String url="http://pf.maoyan.com/show";
		String strHtml = "";
		if (urlMain == null || urlMain.equals("")) {
//			urlMain = "http://piaofang.maoyan.com/";
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
		}

		Document doc = Jsoup.parse(strHtml);

//		String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
//		System.out.println(data_date = data_date.replace("-", ""));

		Elements links = doc.select("div.abc ul.table li a.react");

		for (Element link : links) {
			String City="";
			String cityid="";
			System.out.println(City=link.text());
			System.out.println(cityid=link.attr("data-cityid"));
			String Cityutf="";
			
			try {
				Cityutf = java.net.URLEncoder.encode(City, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//http://pf.maoyan.com/show?showDate=2016-07-11&periodType=0&cityType=0&cityName=%E9%9E%8D%E5%B1%B1&showType=2
			String cityurl="http://pf.maoyan.com/show?showDate="+datetext+"&periodType=0&cityType=0&cityName="+Cityutf+"&showType=2";
			String strcityHtml = "";
			if (strcityHtml == null || strcityHtml.equals("")) {
//				urlMain = "http://piaofang.maoyan.com/";
				strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
			}
			if (strcityHtml == null || strcityHtml.equals("")) {
				strcityHtml = DownloadUtil.getHtmlText(cityurl, 1000 * 30, "UTF-8", null, null);
			}
			
			Document docciy = Jsoup.parse(strcityHtml);

//			String data_date = HtmlAnalyze.getTagText(strHtml, "data-ymd=\"", "\">");
//			System.out.println(data_date = data_date.replace("-", ""));

			Elements linkscty = docciy.getElementById("playPlan_table").select("ul");
			for (Element element : linkscty) {
//				System.out.println(element);
				TEM_FILM_SCHEDULE schedule=new TEM_FILM_SCHEDULE();
				schedule.setDataDate(datetext.replace("-", ""));
				schedule.setCollectionUrl(cityurl);
				schedule.setCityName(City);
				String name="";
				System.out.println(name=element.attr("data-name"));
				schedule.setTitle(name);
				String rate="";
				System.out.println(rate=element.attr("data-rate"));
				schedule.setFieldNumRate(rate);
				String ratenum="";
				System.out.println(ratenum=element.attr("data-number"));
				schedule.setFieldNum(ratenum);
				String id ="";
				System.out.println(id=HtmlAnalyze.getTagText(element.toString(), "detail:'", "'"));
				schedule.setFid(id);
				String elementurl="";
				System.out.println(elementurl="http://pf.maoyan.com"+HtmlAnalyze.getTagText(element.toString(), "href:'", "'"));
				schedule.setUrl(elementurl);
				OracleMovePiaoFang.intoTEM_FILM_SCHEDULE(schedule);
			}
			
//			playPlan_table
			
			
		}

	}
	
	
	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		// String strurl = DownYoukuMovie
		// .youkuMaim("http://www.youku.com/v_olist/c_97_s_1_d_1_g_%E4%BC%98%E9%85%B7%E5%87%BA%E5%93%81.html");
		openstatic();
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
//		TimingTime(11, 59, 59);
//		openstaticPaipianShuJuquanguo("", "2016-07-11");
//		openstatic();
	//	openstatic();
		runstatic();
	}
	

}
