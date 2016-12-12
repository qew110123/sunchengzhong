package com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.tengxunyule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;

public class tengxunyule_5 {

	public static void runnewMain(String urlMain,int DATA_TYPE) {
		// TODO Auto-generated method stub
		// String urlMain="http://ent.qq.com/tv/tv_2012/sjynd.htm";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gb2312", null, null);

		Document doc = Jsoup.parse(strHtml);
		//
		Elements links;
		try {
			
			 links = doc.getElementById("news").select("div.Q-pList");
		} catch (Exception e) {
			// TODO: handle exception
			 links = doc.select("div.pic");
		}
		for (Element link : links) {
			// System.out.println(link);
			WECHAT_INFORMATION wechat = new WECHAT_INFORMATION();

			// wechat.setWeixinhao(weixinhao);
			// wechat.setWeixinAtion(renzheng);
			// wechat.setRanking(paiming);
			// wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
			// String names=link.select("a").first().text();
			String url = link.select("a").first().attr("href");
			System.out.println(url);
			if (!url.contains("http:")) {

				url = "http://ent.qq.com" + url;
			}
			wechat.setUrls(url);
			// wechat.setUrls(urls);
			String imgurl = link.select("img").first().attr("src");
			System.out.println(imgurl);

			wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");

			String imgname = "";
			if (!imgurl.equals("") && imgurl != null) {
				String imgurls = imgurl.replace("\\/", "/");
				String[] namelist=imgurls.split("/");
				if (namelist[namelist.length-1].equals("0")) {
					
					imgname = Image2.imagUrldownload_2(imgurls);
				}else{
					imgname = Image2.imagUrldownload_1(imgurls);
				}
			}

			wechat.setIMG_BIG_NAME(imgname);

			// wechat.setUrls(url);
			String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "gb2312", null, null);

			Document docs = Jsoup.parse(strHtmls);
			String names = docs.select("div.hd h1").text();
			System.out.println(names);
			if (names.equals("")) {
				continue;
			}
			wechat.setNames(names);
			String DATES = docs.select("span.a_time").text();
			wechat.setDates(DATES);
			try {

				String CONTENT_ALL = docs.getElementById("Cnt-Main-Article-QQ").toString();
				wechat.setContentAll(CONTENT_ALL);
				// String CONTENT_P=docs.select("article-detail");

				Elements js_contentps = docs.getElementById("Cnt-Main-Article-QQ").select("p");
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
							Stringelement = Stringelementother.replace("#1#strong#2#", "<strong>")
									.replace("#1#/strong#2#", "</strong>");

						} else {
							Stringelement = element.text();
						}

					}
					js_contentStringp = js_contentStringp + Stringelement + "||";
					// }
					ii += 1;

				}
				// wechat1.setContentP(js_contentStringp);

				System.out.println(js_contentStringp);
				if (js_contentStringp.equals("")) {
					continue;
				}
				wechat.setContentP(js_contentStringp);

				wechat.setSOURCE(3);

				wechat.setPostUser("Ã⁄—∂”È¿÷");
				
				wechat.setDATA_TYPE(DATA_TYPE);
//				DATA_TYPE
				if (!wechat.getNames().equals("")) {

					Oracle.InsertWECHAT_INFORMATION(wechat);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runnewMain("http://ent.qq.com/tv/");
		runnewMain("http://ent.qq.com/movie/",3);
		
//		runnewMain("http://ent.qq.com/star/");
	}

}
