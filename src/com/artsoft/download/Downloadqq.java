package com.artsoft.download;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class Downloadqq {
	public static void downMain(String urlMain) {
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("ul.mod_list_pic_130 li");
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String strmainurl = "";
			String score = "";
			String name = "";
			System.out.println(strmainurl = link.select("a.mod_poster_130").attr("href"));
			// System.out.println(strmainurl = link.select("a").attr("id"));
			System.out.println(name = link.select("a.mod_poster_130").attr("title"));
			// System.out.println(link.select("a").text());
			// System.out.println(link.text());
			System.out.println(score = link.select("strong.c_txt3").text());
			try {
				OracleOpreater.intoReputation(name, "3", score, "0", "", urlMain, "0", "1");
			} catch (Exception e) {
				// TODO: handle exception
			}
			downBranch(strmainurl, name, urlMain);
		}

	}

	public static void downBranch(String urlBranch, String nameBranch, String urlMain) {
		// http: // v.qq.com/cover/e/e7hi6lep1yc51ca.html

		urlBranch = urlBranch.replaceAll("http://v.qq.com/cover/", "").replaceAll("html", "");
		urlBranch = HtmlAnalyze.getTagText(urlBranch, "/", ".");
		// http://sns.video.qq.com/tvideo/fcgi-bin/batchgetplaymount?callback=jQuery191043746947194449604_1445913614627&low_login=1&id=e7hi6lep1yc51ca&otype=json&_=1445913614628
		System.out.println(urlBranch);
		if (urlBranch == null || "".equals(urlBranch)) {
			return;
		}
		urlBranch = "http://sns.video.qq.com/tvideo/fcgi-bin/batchgetplaymount?callback=jQuery191043746947194449604_1445913614627&low_login=1&id="
				+ urlBranch + "&otype=json&_=1445913614628";
		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "UTF-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}

		String numstring = HtmlAnalyze.getTagText(strHtml, "{\"all\":", ",\"");
		System.out.println(numstring);
		try {
			OracleOpreater.intoReputation(nameBranch, "3", numstring, "0", "", urlMain, "0", "0");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		downMain("http://v.qq.com/list/2_-1_-1_-1_1_0_0_20_-1_-1_0_-1.html");
		       // http://v.qq.com/list/2_-1_-1_-1_1_0_1_20_-1_-1_0_-1.html
	 //  http://v.qq.com/list/2_-1_-1_-1_1_0_188_20_-1_-1_0_-1.html
		//http://v.qq.com/list/2_-1_-1_-1_1_0_188_20_-1_-1_0_-1.html
		
		for (int i = 120; i <= 188; i++) {
			String url="http://v.qq.com/list/2_-1_-1_-1_1_0_"+i+"_20_-1_-1_0_-1.html";
			System.out.println(url);
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
			downMain(url);
		}

	}

}
