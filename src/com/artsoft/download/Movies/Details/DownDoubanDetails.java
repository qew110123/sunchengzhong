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
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Calendar;  
import java.util.Date;  
import java.util.Timer;  
import java.util.TimerTask; 

public class DownDoubanDetails {

	public static void iQiYiBranch(String urlBranch,String name) {

		
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

//		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");

		System.out.println(name);
		TvPlay playtv=new TvPlay();
		playtv.setTvplay_url(urlBranch);
		
		playtv.setTvplay_name(name);
		
		String daoyan="";// 导演
		String daoyanAll= HtmlAnalyze.getTagText(strHtml, "导演</span>: ", "</span>",true,0);
		String[] daoyanlist=daoyanAll.split(" /");
		int i=0;
		for (String stringtext : daoyanlist) {
			String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss=HtmlAnalyze.getTagText(stringtext, "\">", "<");
			if (!urlss.equals("")) {
				urlss="http://movie.douban.com"+urlss;
			}
			daoyan=daoyan+textss+"|"+urlss;
			if (daoyanlist.length!=1&&i+1<daoyanlist.length) {
				daoyan=daoyan+",";
			}
			
			i+=1;
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);
		
		
		
			String bianju="";// 编剧
		String bianjuAll= HtmlAnalyze.getTagText(strHtml, "编剧</span>: ", "</span>",true,0);
		String[] bianjulist=bianjuAll.split(" /");
		 i=0;
		for (String stringtext : bianjulist) {
			String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss=HtmlAnalyze.getTagText(stringtext, "\">", "<");
			if (!urlss.equals("")) {
				urlss="http://movie.douban.com"+urlss;
			}
			bianju=bianju+textss+"|"+urlss;
			if (daoyanlist.length!=1&&i+1<daoyanlist.length) {
				bianju=bianju+",";
			}
			
			i+=1;
		}
		System.out.println(bianju);
		playtv.setScreenwriter(bianju);
		
		
		String yanyuan="";// 演员
		String yanyuanAll= HtmlAnalyze.getTagText(strHtml, "主演</span>: ", "</span>",true,0);
		String[] yanyuanlist=yanyuanAll.split(" /");
		i=0;
		for (String stringtext : yanyuanlist) {
			String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss=HtmlAnalyze.getTagText(stringtext, "\">", "<");
			if (!urlss.equals("")) {
				urlss="http://movie.douban.com"+urlss;
			}
			yanyuan=yanyuan+textss+"|"+urlss;
			if (yanyuanlist.length!=1&&i+1<yanyuanlist.length) {
				yanyuan=yanyuan+",";
			}
			
			i+=1;
		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);
		
		String classstr = ""; // 类型:
		classstr = HtmlAnalyze.getTagText(strHtml, "类型:", "<br/>");
		System.out.println(classstr=classstr.replaceAll(" 					", ""));
		playtv.setSubject(classstr);
		
		String diqu=""; //地区
		diqu =HtmlAnalyze.getTagText(strHtml, "地区:", "<br/>");
		playtv.setProduction_area(diqu);
		
		
		String yuyan="";//语言
		
		yuyan =HtmlAnalyze.getTagText(strHtml, "语言:", "<br/>");
		playtv.setLgName(yuyan);
		
		String times = ""; // 上映:
		times = HtmlAnalyze.getTagText(strHtml, "上映日期:", "<br/>");
//		System.out.println(times=times.replaceAll("-", ""));
		playtv.setShow_date(times);
		
		
		String shichang=""; //时长
		shichang= HtmlAnalyze.getTagText(strHtml, "片长:", "<br/>");
		playtv.setTime_length(shichang);
		
		String bieming ="";//别名
		
		bieming = HtmlAnalyze.getTagText(strHtml, "又名:", "<br/>");
		System.out.println(bieming=bieming.replace("	", ""));
		playtv.setAlias_en(bieming);
		
		
		String IMDb=""; //IMDb
		IMDb = HtmlAnalyze.getTagText(strHtml, "IMDb链接:", "<br>");
		System.out.println(IMDb);
		playtv.setIMDb(IMDb);
		
		String detail="";
		detail=HtmlAnalyze.getTagText(strHtml, "<span property=\"v:summary\" class=\"\">", "</span>");
		System.out.println(detail);
		if (detail==null||detail.equals("")||detail.equals("null")) {
			detail=HtmlAnalyze.getTagText(strHtml, "<span class=\"short\" style=\"display:none;\">", "</span>");
		}
		playtv.setBasic_info(detail);
		
		playtv.setClassnum(9);
		
		OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		
		
//		String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");
//
//		String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/?callback=window.Q.__callbacks__.cbrymman";
//		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
//		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, "\":", "}"));
//		try {
//
//			OracleOpreater.intoReputation(name, "2", numhtml, "0", "", urlBranch, "1", "0");
//
//			String videourl = "http://up.video.iqiyi.com/ugc-updown/quud.do?dataid=" + videoId
//					+ "&type=1&userid=&flashuid=e42dc42f8825edf5580806cde99606ce&appID=21&callback=window.Q.__callbacks__.cb1953yg";
//
//			String feishuhtml = DownloadUtil.getHtmlText(videourl, 1000 * 30, "UTF-8", null, null);
//			String feishu = "";
//			System.out.println(feishu = HtmlAnalyze.getTagText(feishuhtml, "score\":", ",\""));
//
//			OracleOpreater.intoReputation(name, "2", feishu, "0", "", urlBranch, "1", "1");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}

	private static void mainurl(String urlMain) {
		// TODO Auto-generated method stub

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		
		JSONObject htmljson = new JSONObject();
		JSONArray htmljsonArray = new JSONArray();
		htmljson = JSONObject.fromObject(strHtml);
		
		htmljsonArray=(JSONArray) htmljson.get("subjects");
		for (Object objecthtml : htmljsonArray) {
			JSONObject jsonsubject=(JSONObject) objecthtml;
			String name = "";
			System.out.println(name=(String) jsonsubject.get("title"));
			String urls="";
			System.out.println(urls=(String) jsonsubject.get("url"));
			String feishu="";
			System.out.println(feishu=(String) jsonsubject.get("rate"));
			String pinglun="";
			
			iQiYiBranch(urls,name);
		}
		
	}

	/**
	 * 
	 * @param url
	 * @return
	 */

	private static String youkuMaim(String url) {
		mainurl(url);
		url = url.replaceAll("1-1-iqiyi--.html", "");
		for (int i = 1; i < 5; i++) {
			mainurl(url + i + "-1-iqiyi--.html");
		}

		return null;
	}
	
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		for (int i = 0; i <=500; i=i+20) {
//			System.out.println(i);
					DownDoubanDetails.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
				}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

//判断数据开始时间
	public static void TimingTime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
        calendar.set(Calendar.MINUTE, mm);       // 控制分  
        calendar.set(Calendar.SECOND, ss);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
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
		
		
//		TimingTime(23, 59, 59);
//		for (int j = 0; j < 4; j++) {
//			
//			DownDoubanNetword.mainurl("http://list.iqiyi.com/www/2/-24065------------4-"+j+"-1-iqiyi--.html");
//		}
		
		
		
//		for (int i = 0; i <=500; i=i+20) {
////			System.out.println(i);
//			DownDoubanNetword.mainurl("http://movie.douban.com/j/search_subjects?type=tv&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
//		}
		
		//http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=20
		
		
//		for (int i = 0; i <=500; i=i+20) {
////	System.out.println(i);
//			DownDoubanMovie.mainurl("http://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start="+i);
//		}
//		TimingTime(23, 59, 59);
		
		runstatic();

	}
}
