package com.artsoft.download.BaiDu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleHaoSou;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class BaiduImageyanyuan {
	
	
	public static void mainmore(String strId, String url, String strUrlname,int sount) {
		
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(url, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}

		Document doc = Jsoup.parse(strHtml);
		
		try {

			// 进行数据的另一个表数据 的添加                                         roleIntroduction-item  
			Elements linkli = doc.select("li.roleIntroduction-item");
			String personname = "";
			String personurl = "";
			String rolename = "";
			String personstillsurl = "";
			String dubbingname = "";
			String dubbingurl = "";
			String roleintro = "";
			String PERSON_BIG_URL="";
			String PERSON_BIG_URLall="";
			for (Element elementli : linkli) {
				System.out.println(personstillsurl = HtmlAnalyze.getTagText(elementli.toString(), "<img src=\"", "\""));
				
				System.out.println(PERSON_BIG_URLall = elementli.select("a.roleIntrodcution-picture").attr("href"));
				System.out.println(
						rolename = HtmlAnalyze.getTagText(elementli.toString(), "class=\"item-value\">", "</span>"));
				System.out.println(personname = elementli.select("div.role-actor span.item-value").text());
				System.out.println(personurl = elementli.select("div.role-actor span.item-value a ").attr("href"));
				// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-actor
				// span.item-value").toString(), "href=\"", "\""));
				System.out.println(dubbingname = elementli.select("div.role-voice span.item-value").text());
				System.out.println(dubbingurl = elementli.select("div.role-voice span.item-value a").attr("href"));
				// System.out.println(HtmlAnalyze.getTagText(elementli.select("div.role-voice
				// span.item-value").toString(), "href=\"", "\""));
				
				
				System.out.println(
						roleintro = HtmlAnalyze.getTagText(elementli.toString(), "role-description\">", "</dd>"));
				if (personurl != "") {
					personurl = "http://baike.baidu.com" + personurl;
				}
				if (dubbingurl != "") {
					dubbingurl = "http://baike.baidu.com" + dubbingurl;
				}
				
				if (PERSON_BIG_URLall != "") {
					PERSON_BIG_URLall = "http://baike.baidu.com" + PERSON_BIG_URLall;
					String strHtmlPERSON_BIG_URLall = DownloadUtil.getHtmlText(PERSON_BIG_URLall, 1000 * 30, "UTF-8", null, null);
					Document docPERSON_BIG_URLall = Jsoup.parse(strHtmlPERSON_BIG_URLall);
					PERSON_BIG_URL=docPERSON_BIG_URLall.getElementById("imgPicture").attr("src");
				}
				
				OracleHaoSou.intotemtvplayall(strId, strUrlname, url, "", personname, personurl, rolename, personstillsurl,
						dubbingname, dubbingurl, roleintro,PERSON_BIG_URL,sount);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainmore("0", "http://baike.baidu.com/subview/5819677/12186430.htm", "功夫熊猫",3);
	}

}
