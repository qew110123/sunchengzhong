package com.artsoft.download.image;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.PERSON_ONTHER;
import com.artsoft.oracle.OraclePERSON;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class Image2345 {

	public static void mainur(String mainUrl, String PERSON_NAME, String IMG_URL, String SEX) {

		PERSON_ONTHER person = new PERSON_ONTHER();
		person.setPERSON_URL(mainUrl);
		person.setPERSON_NAME(PERSON_NAME);
		person.setIMG_URL(IMG_URL);
		person.setSEX(SEX);
		mainUrl = mainUrl.substring(0, mainUrl.length() - 1) + "-1/";
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "gb2312", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		String tyPlayName = "";
		// try {
		Document doc = Jsoup.parse(strHtml);
		Element linkmain = doc.getElementById("conList");
		Elements links = linkmain.select("li");
		for (Element link : links) {
			System.out.println(link);
			String baseInfoName = HtmlAnalyze.getTagText(link.toString(), "sTit\">", "</span>");
			String baseInfoValue = HtmlAnalyze.getTagText(link.toString(), "</span>", "</li>");
			person = buildPerson(baseInfoName, baseInfoValue, person);
		}
		// } catch (Exception e) {
		// // TODO: handle exception
		// // /���򱨴�
		// System.out.println("����");
		// }
		
		String BIG_IMG_URL=  HtmlAnalyze.getTagText(strHtml.toString(), "background-image: url(", ")");
		if (BIG_IMG_URL==null||BIG_IMG_URL.equals("null")) {
//			BIG_IMG_URL=IMG_URL;
			BIG_IMG_URL=  HtmlAnalyze.getTagText(strHtml.toString(), "������Ϣ", "alt",true,0);
			BIG_IMG_URL=HtmlAnalyze.getTagText(BIG_IMG_URL.toString(), "src=\"", "\"");
			if (BIG_IMG_URL==null||BIG_IMG_URL.equals("null")) {
				BIG_IMG_URL=IMG_URL;
			}
		}
		person.setBIG_IMG_URL(BIG_IMG_URL);
		String MAJOR_AWARDStxt=  HtmlAnalyze.getTagText(strHtml.toString(), "<i class=\"iIcon\"></i>��Ҫ�ɾ�</span>", "</dd>",true,0);
		String MAJOR_AWARDSs=  HtmlAnalyze.getTagText(MAJOR_AWARDStxt, "<div class=\"txtList\">", "</div>",true,0);
		try {
			
			String MAJOR_AWARDS=MAJOR_AWARDSs.replaceAll(" ", "").replaceAll("\r\n", "").replaceAll("<br/>", "##");
			person.setMAJOR_AWARDS(MAJOR_AWARDS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		person.setSOURCE("2345Ӱ��");
		System.out.println(person);
		

		OraclePERSON.InsertTemDimPerson(person);
	}

	public static PERSON_ONTHER buildPerson(String baseInfoName, String baseInfoValue, PERSON_ONTHER person) {
		baseInfoValue = baseInfoValue.replaceAll("&nbsp;", "");
		baseInfoValue = baseInfoValue.replaceAll("&middot;", "��");
		if ("������".equals(baseInfoName)) {
			baseInfoValue = baseInfoValue.replaceAll("&middot;", "��");
			person.setPERSON_NAME(baseInfoValue);
		}
		if ("Ѫ��".equals(baseInfoName)) {
			baseInfoValue = baseInfoValue.replaceAll("&middot;", "��");
			person.setBLOODTYPE(baseInfoValue);
		}
		if ("���".equals(baseInfoName)) {
			person.setHEIGHT(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setWEIGHT(baseInfoValue);
		}
		if ("ְҵ".equals(baseInfoName)) {
			person.setOCCUPATION(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			baseInfoValue = baseInfoValue.replaceAll("&nbsp;", "");
			person.setCONSTELLATION(baseInfoValue);
		}
		if ("��������".equals(baseInfoName)) {
			person.setBIRTHDAY(baseInfoValue);
		}
		if ("���͹�˾".equals(baseInfoName)) {
			person.setBROKERAGE_FIRM(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setVOLK(baseInfoValue);
		}
		if ("������".equals(baseInfoName)) {
			person.setBROKERS(baseInfoValue);
		}
		if ("����".equals(baseInfoName)) {
			person.setHOBBY(baseInfoValue);
		}
		return person;
	}

	public static void mainurllist(String mainUrl, String SEX) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "gb2312", null, null);
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
			Element linkmain = doc.getElementById("contentList");
			Elements links = linkmain.select("li div.pic");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			// System.out.println(links.size());
			for (Element link : links) {
				System.out.println(link);
				String IMG_URL = "";
				String PERSON_URL = "";
				String PERSON_NAME = "";
				System.out.println(PERSON_NAME = link.select("img").attr("alt"));
//				System.out.println(IMG_URL = link.select("img").attr("src"));
//				if (IMG_URL.matches("noimg.png")) {
//					IMG_URL= link.select("img").attr("loadsrc");
//				}
				IMG_URL= link.select("img").attr("loadsrc");
				if (IMG_URL==null||"".equals(IMG_URL)) {
					IMG_URL = link.select("img").attr("src");
				}
				System.out.println(PERSON_URL = link.select("a").attr("href"));

				// System.out.println(idnum = HtmlAnalyze.getTagText(idnum,
				// "/f/", ".html"));
				// System.out.println(link.select("span.inner-title").text());
				// hunanBranch(idnum, tyPlayName, strVolumes, mainUrl);
				mainur(PERSON_URL, PERSON_NAME, IMG_URL, SEX);
			}
		} catch (Exception e) {
			// TODO: handle exception
			// /���򱨴�
			System.out.println("����");
			
		}

	}
	
	
	private static void upurl(String urlss,String xingbie) {
		// TODO Auto-generated method stub
		
		for (int i = 1; i <= 100; i++) {
			String urlsss=urlss.replaceAll(".html", "-"+i+".html");
			System.out.println(urlsss);
			mainurllist(urlsss, xingbie);
		}
	}
	private static void run2345() {
		// TODO Auto-generated method stub
		String[] xingbei={"��","Ů"};
//		 String[] diqu={"�ڵ�","���","̨��","�ձ�","����","����","Ӣ��","���","����"};
		 String[] diqu={"���","̨��","�ձ�","����","����","Ӣ��","����"};
		 for (int i = 0; i < diqu.length; i++) {
			for (int j = 0; j < xingbei.length; j++) {
				System.out.println(xingbei[j]+diqu[i]);
				//name=java.net.URLEncoder.encode(name, "utf-8");
				//http://dianying.2345.com/mingxing/list/%E5%A5%B3-%E5%86%85%E5%9C%B0.html
				//http://dianying.2345.com/mingxing/list/%E5%A5%B3-%E5%86%85%E5%9C%B0-2.html
				try {
					String urlss="http://dianying.2345.com/mingxing/list/"+java.net.URLEncoder.encode(xingbei[j], "utf-8")+"-"+java.net.URLEncoder.encode(diqu[i], "utf-8")+".html";
					System.out.println(urlss);
					upurl(urlss,xingbei[j]);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 mainurllist("http://dianying.2345.com/mingxing/list/%E5%A5%B3-%E5%86%85%E5%9C%B0.html",
//		 "Ů");
//		mainur("http://dianying.2345.com/mingxing/2583/", "", "", "");
		 
		 run2345();
	}

	

}
