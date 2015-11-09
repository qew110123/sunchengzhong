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

public class DownloadHunantv {

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
		try {

			// System.out.println(strHtml);
			Document doc = Jsoup.parse(strHtml);
			Element linkmain = doc.getElementById("fluxes_static");
			Elements links = linkmain.select("ul.v-list-inner li.v-list-unit");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			System.out.println(links.size());
			for (Element link : links) {
				String idnum = "";
				String strVolumes = "";
				System.out.println(strVolumes = link.select("span.inner-title").text());
				System.out.println(idnum = link.select("a.video").attr("href"));
				System.out.println(idnum = HtmlAnalyze.getTagText(idnum, "/f/", ".html"));
				// System.out.println(link.select("span.inner-title").text());
				hunanBranch(idnum, tyPlayName, strVolumes, mainUrl);
			}
		} catch (Exception e) {
			// TODO: handle exception
			// /程序报错
			System.out.println("出错");
		}

	}

	public static void hunanBranch(String idnum, String tyPlayName, String strVolumes, String playUrl) {
		// String tyPlayName = "";
		String serNumber = strVolumes.replaceAll("第", "").replaceAll("集", "");
		String source = "7";
		String playAmount = "";
		String vodeoType = "0";
		String palydate = "";
		// String playUrl = urlnew;
		String tvType = "0";
		// String realUrl = "";

		String urlnew = "http://videocenter-2039197532.cn-north-1.elb.amazonaws.com.cn//dynamicinfo?callback=jQuery182025981585565023124_1445935554595&vid="
				+ idnum + "&_=1445935555113";
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

			OracleOpreater.intoPlayAmont(tyPlayName, serNumber, source, playAmount, vodeoType, palydate, urlnew, tvType,
					playUrl);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// for (int i = 0; i < 25; i++) {
		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p="+i+"&s=1";
		// mainurl(mainUrl);
		// }
		// String mainUrl = "http://www.hunantv.com/v/2/104822/f/1821553.html";
		// mainurl(mainUrl);
		while (true) {
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");

			for (int i = 1; i <= 18; i++) {
				String url = "http://list.hunantv.com/2/----------" + i + "---.html";
				System.out.println(url);
				CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
				mainmore(url);
			}
			try {
				Thread.sleep(1000*60*60*22);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
		}
	}

}
