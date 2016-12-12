package com.artsoft.download.only_one_Once;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_BAIDU_NEW;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class baidu_new_time {

	private static void baidunew(String name) {
		// TODO Auto-generated method stub
		String utf8name = "";
		try {
			utf8name = java.net.URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i <= 740; i=i+20) {
			//http://news.baidu.com/ns?word=intitle%3A%28%E7%9B%97%E5%A2%93%E7%AC%94%E8%AE%B0%29&pn=80&cl=2&ct=0&tn=newstitle&rn=20&ie=utf-8&bt=0&et=0
			String urlutf8name = "http://news.baidu.com/ns?word=title%3A%28"+utf8name+"%29&pn="+i+"&cl=2&ct=0&tn=newstitle&rn=20&ie=utf-8&bt=0&et=0";
			System.out.println(urlutf8name);
			baidunew_xiangxi(name,urlutf8name);
		}
	}

	private static void baidunew_xiangxi(String name ,String urlMain) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		// System.out.println(strHtml);
		strHtml = HtmlAnalyze.getTagText(strHtml, "<div id=\"ecomAdDiv_1\">", "<div id=\"gotoPage\">", true, 0);
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select(".result").select(".title");
		String timeString = "";
//		String TVPLAY_NAME="";
		String SHOW_NAME="";
		String SOURCES="";
		String CREATE_DATE="";
		String TVPLAY_URL="";
		for (Element link : links) {
			// System.out.println(link);
			TEM_BAIDU_NEW BAIDU_NEW=new TEM_BAIDU_NEW();
			BAIDU_NEW.setTvplayName(name);
			System.out.println(SHOW_NAME=link.select("h3").text());
			BAIDU_NEW.setShowName(SHOW_NAME);
			System.out.println(SOURCES=HtmlAnalyze.getTagText(link.toString(), "c-title-author\">", "&nbsp;&nbsp;"));
			BAIDU_NEW.setSources(SOURCES);
			timeString = HtmlAnalyze.getTagText(link.toString(), "&nbsp;&nbsp;", "&nbsp;&nbsp;");
			if (timeString.equals("")) {
				timeString = HtmlAnalyze.getTagText(link.toString(), "&nbsp;&nbsp;", "<");
			}
			int intnum=1;
			try {
				System.out.println(timeString);
				Date timedate = strToDateLong(timeString);
				System.out.println(timedate);
				String dateStrinvgall = dateToStr(timedate);
				System.out.println(dateStrinvgall);
				BAIDU_NEW.setCreateDate(dateStrinvgall);
				 intnum=compare_date(dateStrinvgall, "2015-06-12 00:00:00");
				System.out.println(intnum);
			} catch (Exception e) {
				// TODO: handle exception
				
				BAIDU_NEW.setCreateDate(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
			}
			
			System.out.println(TVPLAY_URL=link.select("a").first().attr("href"));
			BAIDU_NEW.setTvplayUrl(TVPLAY_URL);
			if (intnum==1) {

				Oracle.InsertTEM_BAIDU_NEW(BAIDU_NEW);
			}
		}

	}

	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
    public static int compare_date(String DATE1, String DATE2) {
        
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
//                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		baidunew("盗墓笔记");

	}

}
