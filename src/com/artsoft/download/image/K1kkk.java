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
		String ANIMATION_NAME = ""; // ��������
		String ANIMATION_URL = ""; // ��վurl
		String AUTHOR = ""; // ����
		// String AREA = ""; // ����
		String STATE = ""; // ״̬
		String POPULARITY = "0"; // ����
		String SUBJECT_NAME = ""; // �������
		String ANIMATION_CATEGORY = ""; // ����
		String IN_DATE = ""; // ��¼����
		// String SOURCE = ""; // ������Դ �� ��վ����
		String CLICK_NUM = "";// �����
		String SCORE = "";// ����
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
			// HtmlAnalyze.getTagText(link.toString(), "���ߣ�", "</p>"));
			// System.out.println(SUBJECT_NAME =
			// HtmlAnalyze.getTagText(link.toString(), "���ͣ�", "</p>"));
			// System.out.println(STATE =
			// HtmlAnalyze.getTagText(link.toString(), "״̬��", "</p>"));
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
					System.out.println(AUTHOR = HtmlAnalyze.getTagText(POPULARITYjson, "���ߣ� ", "</li>")
							.replace("&nbsp;", "").replaceAll("                    ", "/"));
					System.out.println(SUBJECT_NAME = HtmlAnalyze.getTagText(POPULARITYjson, "��� ", "/li")
							.replaceAll("             ", "/"));
					POPULARITY = HtmlAnalyze.getTagText(POPULARITYjson, "������", "��");
					AREA = HtmlAnalyze.getTagText(POPULARITYjson, "����������", "</li>");
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
			mainurllist("http://www.1kkk.com/manhua-zhentantuili-p" + j + "/", "", "��������");
		}

	}

}
