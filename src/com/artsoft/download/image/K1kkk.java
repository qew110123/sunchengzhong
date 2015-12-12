package com.artsoft.download.image;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleSarFtGov;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class K1kkk {
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
		Elements links = doc.select("ul.in_01 li p.cover");
		// Elements links = linksul.select("ul.list_con_li").first();
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			System.out.println(link);
			System.out.println(ANIMATION_NAME = link.select("a").first().text());
			System.out.println(ANIMATION_URL = "http://www.1kkk.com" + link.select("a").first().attr("href"));
			// System.out.println(AUTHOR =
			// HtmlAnalyze.getTagText(link.toString(), "作者：", "</p>"));
			// System.out.println(SUBJECT_NAME =
			// HtmlAnalyze.getTagText(link.toString(), "类型：", "</p>"));
			// System.out.println(STATE =
			// HtmlAnalyze.getTagText(link.toString(), "状态：", "</p>"));
			String comic_id = link.select("a").first().attr("href").replace("/manhua", "").replace("/", "");

			// String comic_idstrHtml = DownloadUtil.getHtmlText(ANIMATION_URL,
			// 1000 * 30, "utf-8", null, null);
			// comic_id = HtmlAnalyze.getTagText(comic_idstrHtml, "var comic_id
			// = '", "'");
			// http://www.dmzj.com/static/hits/17049.json
			if (comic_id != null) {
				String POPULARITYjson = DownloadUtil.getHtmlText(
						"http://www.1kkk.com/showcomicdetail/?id=" + comic_id + "", 1000 * 30, "utf-8", null, null);
				System.out.println(POPULARITYjson);
				if (POPULARITYjson != null) {
					System.out.println(AUTHOR = HtmlAnalyze.getTagText(POPULARITYjson, "作者： ", "</li>")
							.replace("&nbsp;", "").replaceAll("                    ", "/"));
					System.out.println(SUBJECT_NAME = HtmlAnalyze.getTagText(POPULARITYjson, "类别： ", "/li")
							.replaceAll("             ", "/"));
					POPULARITY = HtmlAnalyze.getTagText(POPULARITYjson, "人气：", "次");
					AREA = HtmlAnalyze.getTagText(POPULARITYjson, "漫画地区：", "</li>");
					if (AREA == null) {
						AREA = "";
					}
					// CLICK_NUM = HtmlAnalyze.getTagText(POPULARITYjson,
					// "\"hits\":", ",");
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
		// TODO Auto-generated method stub
		for (int j = 0; j < 15; j++) {
			mainurllist("http://www.1kkk.com/manhua-zhentantuili-p" + j + "/", "", "极速漫画");
		}

	}

}
