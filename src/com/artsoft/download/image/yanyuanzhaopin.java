package com.artsoft.download.image;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.PERSON_ONTHER;
import com.artsoft.oracle.OraclePERSON;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class yanyuanzhaopin {

	public static void mainurllist(String mainUrl, String SEX) {

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
		String tyPlayName = "";
		// System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml,
		// "cname: \"", "\""));
		try {

			// System.out.println(strHtml);
			Document doc = Jsoup.parse(strHtml);
			// Element linkmain = doc.getElementById("contentList");
			Elements links = doc.select("div.dianying2");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			// System.out.println(links.size());
			for (Element link : links) {
				PERSON_ONTHER person = new PERSON_ONTHER();
//				System.out.println(link);
				String IMG_URL = "";
				String PERSON_URL = "";
				String PERSON_NAME = "";
				System.out.println(PERSON_NAME = link.select("a").first().attr("title"));
				System.out.println(PERSON_URL = link.select("a").attr("href"));
				System.out.println(IMG_URL =  link.select("img").attr("src"));
				// mainur(PERSON_URL, PERSON_NAME, IMG_URL, SEX);
				person.setPERSON_NAME(PERSON_NAME);
				person.setPERSON_URL(PERSON_URL);
				person.setIMG_URL(IMG_URL);

				person.setSOURCE("演员招聘网网");
//				person.setSEX(SEX);
				if (PERSON_URL != null) {
					String POPULARITYjson = DownloadUtil.getHtmlText(PERSON_URL, 1000 * 30, "utf-8", null, null);
					System.out.println(POPULARITYjson);
					if (POPULARITYjson != null) {
//						Document POPULARITYdoc = Jsoup.parse(POPULARITYjson);
//						SCORE=POPULARITYdoc.select("strong.ui-text-orange").first().text();
						String HEIGHT = HtmlAnalyze.getTagText(POPULARITYjson, "身&nbsp;&nbsp;高：", "\r");
						HEIGHT=HEIGHT.replace("&nbsp;", "");
						if (HEIGHT!=null) {
							person.setHEIGHT(HEIGHT);
						}
						 SEX = HtmlAnalyze.getTagText(POPULARITYjson, "性&nbsp;&nbsp;别：", "\r").replace("&nbsp;", "");
						if (SEX!=null) {
							person.setSEX(SEX);
						}
						//导演 明星
						String OCCUPATION= HtmlAnalyze.getTagText(POPULARITYjson, "职&nbsp;&nbsp;业:", "</l").replace("&nbsp;", "").replace(" ", "/");
						if (OCCUPATION!=null) {
							person.setOCCUPATION(OCCUPATION);
						}
						String BIRTHDAY= HtmlAnalyze.getTagText(POPULARITYjson, "出&nbsp;&nbsp;生：", "\r").replace("&nbsp;", "");
						if (BIRTHDAY!=null) {
							person.setBIRTHDAY(BIRTHDAY);
						}
						//<img alt='演员招聘' src='uploadfile
						String BIG_IMG_URL= HtmlAnalyze.getTagText(POPULARITYjson, "<img alt='演员招聘' src='", "'").replace("&nbsp;", "");
						if (BIG_IMG_URL!=null) {
							person.setBIG_IMG_URL("http://www.yanyuanzhaopin.com/"+BIG_IMG_URL);
						}
					}
				}
				 OraclePERSON.InsertTemDimPerson(person);
			}
		} catch (Exception e) {
			// TODO: handle exception
			// /程序报错
			System.out.println("出错");

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 44; i++) {

			mainurllist("http://www.yanyuanzhaopin.com/actor_list.php?curPage=" + i, "");
		}
//		for (int i = 1; i <= 699; i++) {
//
//			mainurllist("http://yy.8fkd.com/YanYuanKu/Search.aspx?key=&gj=&xx=&sex=2&xz=&zm=&page=" + i, "女");
//		}

	}

}
