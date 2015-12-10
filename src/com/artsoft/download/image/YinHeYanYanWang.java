package com.artsoft.download.image;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.PERSON_ONTHER;
import com.artsoft.oracle.OraclePERSON;
import com.artsoft.util.DownloadUtil;

public class YinHeYanYanWang {

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
			Elements links = doc.select("div.divItem");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			// System.out.println(links.size());
			for (Element link : links) {
				PERSON_ONTHER person = new PERSON_ONTHER();
//				System.out.println(link);
				String IMG_URL = "";
				String PERSON_URL = "";
				String PERSON_NAME = "";
				System.out.println(PERSON_NAME = link.select("a.aName").text());
				System.out.println(PERSON_URL = "http://yy.8fkd.com" + link.select("a.aName").attr("href"));
				System.out.println(IMG_URL =  link.select("img").attr("src"));
				// mainur(PERSON_URL, PERSON_NAME, IMG_URL, SEX);
				person.setPERSON_NAME(PERSON_NAME);
				person.setPERSON_URL(PERSON_URL);
				person.setIMG_URL(IMG_URL);

				person.setSOURCE("银河演员网");
				person.setSEX(SEX);
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
		for (int i = 1; i <= 1505; i++) {

			mainurllist("http://yy.8fkd.com/YanYuanKu/Search.aspx?key=&gj=&xx=&sex=1&xz=&zm=&page=" + i, "男");
		}
		for (int i = 1; i <= 699; i++) {

			mainurllist("http://yy.8fkd.com/YanYuanKu/Search.aspx?key=&gj=&xx=&sex=2&xz=&zm=&page=" + i, "女");
		}

	}

}
