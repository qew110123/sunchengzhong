package com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.wangyiyule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class wangyiyule_3 {

	public static void runnewMain(String urlMain,int DATA_TYPE) {
		// TODO Auto-generated method stub
		// String urlMain="http://ent.163.com/special/ysl/";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);

//		Document doc = Jsoup.parse(strHtml);
		JSONObject objects = new JSONObject();
		JSONArray list = new JSONArray();
		if (strHtml.equals("")) {
			return;
		}
		strHtml= HtmlAnalyze.getTagText(strHtml, "data_callback(", "])");
		strHtml=strHtml+"]";
//		objects = JSONObject.fromObject(strHtml);
//		System.out.println(objects);
//		System.out.println(objects.get("data"));
////		JSONObject data = (JSONObject) objects.get("data");
////		System.out.println(data);
//		list = (JSONArray) objects.get("data");
		list=JSONArray.fromObject(strHtml);
		//
		
		for (Object object : list) {
			JSONObject objectobject = JSONObject.fromObject(object);
			WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();
			System.out.println(object);
			String url = objectobject.getString("docurl");
			System.out.println(url);
			// url="http://ent.qq.com"+url;
			wechat.setUrls(url);
			
			String names = objectobject.getString("title");
			System.out.println(names);
			wechat.setNames(names);
			
			String DATES = objectobject.getString("time");
//			DATES = HtmlAnalyze.getTagText(DATES, "</span>", "</p>");
			wechat.setDates(DATES);
			
			
			String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "gbk", null, null);

			Document docs = Jsoup.parse(strHtmls);
			// String names=docs.getElementById("page").select("h1").text();
			// System.out.println(names);
			// wechat.setNames(names);
			// String DATES=docs.select("span.time").text();
			// wechat.setDates(DATES);
			String CONTENT_ALL = docs.select("div.post_text").toString();

			wechat.setContentAll(CONTENT_ALL);
			// String CONTENT_P=docs.select("article-detail");

			while (CONTENT_ALL.contains("<script")) {

				String CONTENT_ALLhtml = HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>", false, 0);
				CONTENT_ALL = CONTENT_ALL.replace(CONTENT_ALLhtml, "");
			}

			Elements js_contentps = docs.select("div.post_text").select("p");
			String js_contentStringp = "";
			int ii = 0;
			for (Element element : js_contentps) {
				// if (ii>2) {
				String Stringelement = "";

				if (element.toString().contains("<img")) {
					Stringelement = element.toString();

				} else {
					if (element.toString().contains("<strong>")) {
						String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
								.replace("</strong>", "#1#/strong#2#");
						Document docStringelement = Jsoup.parse(Stringelement_1_2);
						String Stringelementother = docStringelement.text();
						Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>").replace("#1#/strong#2#",
								"</strong>");

					} else {
						Stringelement = element.text();
					}

				}
				js_contentStringp = js_contentStringp + Stringelement + "||";
				// }
				ii += 1;

			}
			// wechat1.setContentP(js_contentStringp);

			// System.out.println(js_contentStringp);
			wechat.setContentP(CONTENT_ALL);
			
			wechat.setSOURCE(4);

			wechat.setPostUser("Õ¯“◊”È¿÷");
			
			wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
			
			String imgurl=String.valueOf(objectobject.get("imgurl"));
			System.out.println(imgurl);
			String imgname="";
			if (!imgurl.equals("")&&imgurl!=null) {
				String imgurls=imgurl.replace("\\/", "/");
				imgname=Image2.imagUrldownload_1(imgurls);
			}
			
			wechat.setIMG_BIG_NAME(imgname);
			
			wechat.setDATA_TYPE(DATA_TYPE);

			Oracle.InsertWECHAT_INFORMATION(wechat);
			
		}
//		Elements links = doc.getElementById("news-flow-content").select("li");
		
//		for (Element link : links) {
//			// System.out.println(link);
//			WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();
//
//			// wechat.setWeixinhao(weixinhao);
//			// wechat.setWeixinAtion(renzheng);
//			// wechat.setRanking(paiming);
//			// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
//			// String names=link.select("a").first().text();
//			String url = link.select("a").first().attr("href");
//			System.out.println(url);
//			// url="http://ent.qq.com"+url;
//			wechat.setUrls(url);
//			// wechat.setUrls(urls);
//			// wechat.setUrls(url);
//
//			String names = link.select("a").first().text();
//			System.out.println(names);
//			wechat.setNames(names);
//
//			String DATES = link.select("p.sourceDate").toString();// .text();
//			DATES = HtmlAnalyze.getTagText(DATES, "</span>", "</p>");
//			wechat.setDates(DATES);
//
//			// String imgurl=link.select("img").first().attr("src");
//			// System.out.println(imgurl);
//			//
//			// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
//			//
//			// String imgname="";
//			// if (!imgurl.equals("")&&imgurl!=null) {
//			// String imgurls=imgurl.replace("\\/", "/");
//			// imgname=Image2.imagUrldownload_1(imgurls);
//			// }
//			//
//			// wechat.setIMG_BIG_NAME(imgname);
//
//			String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "gbk", null, null);
//
//			Document docs = Jsoup.parse(strHtmls);
//			// String names=docs.getElementById("page").select("h1").text();
//			// System.out.println(names);
//			// wechat.setNames(names);
//			// String DATES=docs.select("span.time").text();
//			// wechat.setDates(DATES);
//			String CONTENT_ALL = docs.select("div.con_1").toString();
//
//			wechat.setContentAll(CONTENT_ALL);
//			// String CONTENT_P=docs.select("article-detail");
//
//			while (CONTENT_ALL.contains("<script")) {
//
//				String CONTENT_ALLhtml = HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>", false, 0);
//				CONTENT_ALL = CONTENT_ALL.replace(CONTENT_ALLhtml, "");
//			}
//
//			Elements js_contentps = docs.select("div.con_1").select("p");
//			String js_contentStringp = "";
//			int ii = 0;
//			for (Element element : js_contentps) {
//				// if (ii>2) {
//				String Stringelement = "";
//
//				if (element.toString().contains("<img")) {
//					Stringelement = element.toString();
//
//				} else {
//					if (element.toString().contains("<strong>")) {
//						String Stringelement_1_2 = element.toString().replace("<strong>", "#1#strong#2#")
//								.replace("</strong>", "#1#/strong#2#");
//						Document docStringelement = Jsoup.parse(Stringelement_1_2);
//						String Stringelementother = docStringelement.text();
//						Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>").replace("#1#/strong#2#",
//								"</strong>");
//
//					} else {
//						Stringelement = element.text();
//					}
//
//				}
//				js_contentStringp = js_contentStringp + Stringelement + "||";
//				// }
//				ii += 1;
//
//			}
//			// wechat1.setContentP(js_contentStringp);
//
//			// System.out.println(js_contentStringp);
//			wechat.setContentP(CONTENT_ALL);
//
//			wechat.setSOURCE(4);
//
//			wechat.setPostUser("Õ¯“◊”È¿÷");
//			
//			wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
//			
//			String imgurl=String.valueOf(objectobject.get("image_url"));
//			System.out.println(imgurl);
//			String imgname="";
//			if (!imgurl.equals("")&&imgurl!=null) {
//				String imgurls=imgurl.replace("\\/", "/");
//				imgname=Image2.imagUrldownload_1(imgurls);
//			}
//			
//			wechat.setIMG_BIG_NAME(imgname);
//			
//
//			Oracle.InsertWECHAT_INFORMATION(wechat);
//		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runnewMain("http://ent.163.com/special/000381P3/newsdata_tv_index.js?callback=data_callback");
		runnewMain("http://ent.163.com/special/000381Q1/newsdata_movieidx.js?callback=data_callback",3);
	}

}
