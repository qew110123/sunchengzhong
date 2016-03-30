package com.artsoft.download.TVPlay.platform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.ReadTxtFile;
import com.artsoft.util.TimeTest;

import java.util.Calendar;  
import java.util.Date;  
import java.util.Timer;  
import java.util.TimerTask; 

/**
 * 爱奇艺数据到的详细数据
 * @author Administrator
 *
 */
public class DownIqiyi {

	public static void iQiYiBranch(String urlBranch) {
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		
		try {
		String people = HtmlAnalyze.getTagText(strHtml, "主演：", "导演");

		System.out.println(people=people.replaceAll("\r\n", "").replaceAll(" ", ""));
		
		String classstr = HtmlAnalyze.getTagText(strHtml, "类型：", "：");

		
		classstr=classstr.replaceAll("导演", "").replaceAll("语言", "").replaceAll("主演", "");
		System.out.println(classstr=classstr.replaceAll("\r\n", "").replaceAll(" ", ""));
		

		String name = HtmlAnalyze.getTagText(strHtml, "<meta name=\"keywords\" content=\"", "\" /> ");

		System.out.println(name);
		String videoId = HtmlAnalyze.getTagText(strHtml, "albumId: ", ",");

		String albumurl = "http://cache.video.qiyi.com/jp/pc/" + videoId + "/?callback=window.Q.__callbacks__.cbrymman";
		System.out.println(albumurl);
		String numhtml = DownloadUtil.getHtmlText(albumurl, 1000 * 30, "UTF-8", null, null);
		System.out.println(numhtml = HtmlAnalyze.getTagText(numhtml, "\":", "}"));
			OracleOpreater.intoTEM_NETWORK_TVPLAY_AMOUNT(name, 2, Integer.parseInt(numhtml), "2015", urlBranch, classstr, people ,0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void mainurl(String urlMain) {
		// TODO Auto-generated method stub

		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			// return;
			return;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.site-piclist_pic");
		for (Element link : links) {
			String strmainurl = "";
			System.out.println(link.select("a.site-piclist_pic_link").attr("title"));
			System.out.println(strmainurl = link.select("a.site-piclist_pic_link").attr("href"));
			iQiYiBranch(strmainurl);
		}
	}

	
	public static void main(String[] args) {
		//http://list.iqiyi.com/www/2/-----------2015--4-1-1-iqiyi--.html
		//http://list.iqiyi.com/www/2/-----------2015--4-9-1-iqiyi--.html
		for (int i = 1; i < 10; i++) {
			DownIqiyi.mainurl("http://list.iqiyi.com/www/2/-----------2015--4-"+i+"-1-iqiyi--.html");
			
		}

	}
}
