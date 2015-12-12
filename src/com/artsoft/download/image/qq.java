package com.artsoft.download.image;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class qq {
	public static void mainurllist(String mainUrl, String AREA, String SOURCE) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "utf-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		// // dmzj bb=new dmzj();
		// strHtml = requestByGetMethod(mainUrl);
		// requestByPostMethod
		// System.out.println(strHtml);
		String ANIMATION_NAME = ""; // 动漫名称
		String ANIMATION_URL = ""; // 网站url
		String AUTHOR = ""; // 作者
		// String AREA = ""; // 地区
		String STATE = ""; // 状态
		String POPULARITY = "0"; // 人气
		String SUBJECT_NAME = ""; // 题材名称
		String ANIMATION_CATEGORY = ""; // 分类
		String IN_DATE = ""; // 收录日期
		// String SOURCE = ""; // 漫画来源 ： 网站名称
		String CLICK_NUM = "";// 点击量
		String SCORE = "";// 评分
		Document doc = Jsoup.parse(strHtml);
		Elements links = doc.select("div.ret-works-info");
		// Elements links = linksul.select("ul.list_con_li").first();
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			System.out.println(link);
			System.out.println(ANIMATION_NAME = link.select("a").first().text());
			System.out.println(ANIMATION_URL = "http://ac.qq.com" + link.select("a").first().attr("href"));
			 System.out.println(AUTHOR =link.select("p.ret-works-author").first().attr("title"));
			 Elements linksworks = link.select("p.ret-works-tags a");
			 int xx=0;
			for (Element element : linksworks) {
				if (xx==0) {
					
					SUBJECT_NAME=element.text();
					xx+=1;
				}
				else{
					SUBJECT_NAME=SUBJECT_NAME+"/"+element.text();
				}
			}
			POPULARITY=link.select("p.ret-works-tags em").first().text();
			// System.out.println(STATE =
			// HtmlAnalyze.getTagText(link.toString(), "状态：", "</p>"));
//			String comic_id = link.select("a").first().attr("href").replace("/manhua", "").replace("/", "");

			// String comic_idstrHtml = DownloadUtil.getHtmlText(ANIMATION_URL,
			// 1000 * 30, "utf-8", null, null);
			// comic_id = HtmlAnalyze.getTagText(comic_idstrHtml, "var comic_id
			// = '", "'");
			// http://www.dmzj.com/static/hits/17049.json
			if (ANIMATION_URL != null) {
				String POPULARITYjson = DownloadUtil.getHtmlText(ANIMATION_URL, 1000 * 30, "utf-8", null, null);
//				System.out.println(POPULARITYjson);
				if (POPULARITYjson != null) {
					Document POPULARITYdoc = Jsoup.parse(POPULARITYjson);
					SCORE=POPULARITYdoc.select("strong.ui-text-orange").first().text();
					
				}
			}
			OracleSarFtGov.intotem_animation(ANIMATION_NAME, ANIMATION_URL, AUTHOR, AREA, STATE, POPULARITY,
					SUBJECT_NAME, ANIMATION_CATEGORY, IN_DATE, SOURCE, CLICK_NUM, SCORE);
			// OracleSarFtGov.intotem_animation(ANIMATION_NAME,
			// ANIMATION_URL, AUTHOR, AREA, STATE, POPULARITY,
			// SUBJECT_NAME, ANIMATION_CATEGORY, IN_DATE, SOURCE,"","");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub 15
		for (int j = 0; j < 15; j++) {
			mainurllist("http://ac.qq.com/Comic/all/theme/9/state/pink/search/time/page/" + j + "","", "腾讯动漫");
		}
		
	}

}
