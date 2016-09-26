package com.artsoft.download.TVPlay.HuaXu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TEM_TVPLAY_TIDBITS;
import com.artsoft.demo.imag.Image1;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class HuaXu_sohu {

	public static void sohuBranch(String urlBranch,int DATA_TYPE) {
		// TvPlay playtv = new TvPlay();
		// playtv.setTvplay_url(urlBranch);

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		// <h2>电视剧：<span class="vname">
		String name = HtmlAnalyze.getTagText(strHtml, "<span class=\"vname\">", "</span>");
		System.out.println(name);
		// playtv.setTvplay_name(name);

		String yugaopiao = HtmlAnalyze.getTagText(strHtml, "<!-- Start : 预告片 -->", "<!-- End : 预告片 -->", true, 0);
		// System.out.println(yugaopiao);

		Document docyugaopiao = Jsoup.parse(yugaopiao);
		Elements docsyugaopiao = docyugaopiao.select("li");
		int i=0;
		for (Element link : docsyugaopiao) {
			System.out.println(link);
			TEM_TVPLAY_TIDBITS tidbits = new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(urlBranch);
//			tidbits.setSOURCE(4);
			String IMG_SMALL_URL = "";
			System.out.println(IMG_SMALL_URL = link.select("img").attr("src"));
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			String IMG_SMALL_NAME = Image1.downloadimg(IMG_SMALL_URL);
			tidbits.setImgSmallName(IMG_SMALL_NAME);
			String TIME_LONGS = "";
			System.out.println(TIME_LONGS = HtmlAnalyze.getTagText(link.toString(), "发布：", "</p>"));
			tidbits.setTimeLongs(TIME_LONGS);
			String PLAY_URL="";
			System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link.toString(), "href=\"", "\""));
			tidbits.setPlayUrl(PLAY_URL);
			String TITLE_NAME="";
			System.out.println(TITLE_NAME = link.select("a").first().attr("title"));
			tidbits.setTitleName(TITLE_NAME);
//			String PLAY_AMOUNT="";
//			System.out.println(PLAY_AMOUNT = link.select("li.v_stat span.num").text());
//			tidbits.setPlayAmount(Integer.valueOf(PLAY_AMOUNT.replace(",", "")));
			
			tidbits.setOrderNo(i);
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setSOURCE(4);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			i=i+1;
		}

	}
	
	
	
	

	public static void sohuBranch_huaxu(String urlBranch,int DATA_TYPE) {
		// TvPlay playtv = new TvPlay();
		// playtv.setTvplay_url(urlBranch);

		String strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		if (strHtml == null || strHtml.equals("")) {
			strHtml = DownloadUtil.getHtmlText(urlBranch, 1000 * 30, "utf-8", null, null);
		}
		if (strHtml == null || strHtml.equals("")) {
			return;
		}
		// <h2>电视剧：<span class="vname">
		String name = HtmlAnalyze.getTagText(strHtml, "<span class=\"vname\">", "</span>");
		System.out.println(name);
		// playtv.setTvplay_name(name);

		String yugaopiao = HtmlAnalyze.getTagText(strHtml, "<!-- Start : 花絮周边 -->", "<!-- End : 花絮周边 -->", true, 0);
		// System.out.println(yugaopiao);

		Document docyugaopiao = Jsoup.parse(yugaopiao);
		Elements docsyugaopiao = docyugaopiao.select("li");
		int i=0;
		for (Element link : docsyugaopiao) {
			System.out.println(link);
			TEM_TVPLAY_TIDBITS tidbits = new TEM_TVPLAY_TIDBITS();
			tidbits.setTvplayName(name);
			tidbits.setDetailUrl(urlBranch);
//			tidbits.setSOURCE(4);
			String IMG_SMALL_URL = "";
			System.out.println(IMG_SMALL_URL = link.select("img").attr("src"));
			tidbits.setImgSmallUrl(IMG_SMALL_URL);
			String IMG_SMALL_NAME = Image1.downloadimg(IMG_SMALL_URL);
			tidbits.setImgSmallName(IMG_SMALL_NAME);
			String TIME_LONGS = "";
			System.out.println(TIME_LONGS = HtmlAnalyze.getTagText(link.toString(), "发布：", "</p>"));
			tidbits.setTimeLongs(TIME_LONGS);
			String PLAY_URL="";
			System.out.println(PLAY_URL=HtmlAnalyze.getTagText(link.toString(), "href=\"", "\""));
			tidbits.setPlayUrl(PLAY_URL);
			String TITLE_NAME="";
			System.out.println(TITLE_NAME = link.select("a").first().attr("title"));
			tidbits.setTitleName(TITLE_NAME);
//			String PLAY_AMOUNT="";
//			System.out.println(PLAY_AMOUNT = link.select("li.v_stat span.num").text());
//			tidbits.setPlayAmount(Integer.valueOf(PLAY_AMOUNT.replace(",", "")));
			
			tidbits.setOrderNo(i);
			tidbits.setDataType(DATA_TYPE);	
			
			tidbits.setSOURCE(4);
			
			Oracle.IntoTEM_TVPLAY_TIDBITS(tidbits);
			i=i+1;
		}

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
					try {
						
						sohuBranch(texturl,2);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						
						sohuBranch_huaxu(texturl, 1);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void runstatic() {
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开始");
		for (int i = 1; i <= 80; i++) {
			sohuMain("http://so.tv.sohu.com/list_p1101_p2_p3_p4-1_p5_p6_p77_p80_p9_p10" + i + "_p11_p12_p13.html");

		}
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结束");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 runstatic();
//		sohuBranch_huaxu("http://tv.sohu.com/item/MTIwNTQxNg==.html",2);
	}

}
