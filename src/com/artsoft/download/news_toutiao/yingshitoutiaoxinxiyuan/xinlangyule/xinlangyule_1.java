package com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.xinlangyule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.demo.imag.Image2;
import com.artsoft.download.news_toutiao.yingshitoutiaoxinxiyuan.jinritoutiao;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class xinlangyule_1 {
	
	
	public static void runnewMain(String urlMain,int DATA_TYPE) {
		// TODO Auto-generated method stub
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "UTF-8", null, null);
		
		strHtml=HtmlAnalyze.getTagText(strHtml.toString(),"result\":", "});}catch",true, 0);
		
		JSONObject objects = new JSONObject();
		JSONArray list = new JSONArray();

		objects = JSONObject.fromObject(strHtml);
//		System.out.println(objects);
//		System.out.println(objects.get("data"));
//		JSONObject data = (JSONObject) objects.get("data");
//		System.out.println(data);
		list = (JSONArray) objects.get("data");
		
		int search_index = 0;
		String trend = "";
		String title="";
		String xiangxi_url="";
		for (Object object : list) {
			JSONObject objectobject = JSONObject.fromObject(object);
			System.out.println(title = (String) objectobject.get("title"));
//			System.out.println( xiangxi_url="http://www.toutiao.com"+objectobject.get("source_url"));
			String urlid="";
//			urlid=HtmlAnalyze.getTagText(objectobject.get("source_url").toString()+"#", "group/", "#");
//			System.out.println( xiangxi_url="http://www.toutiao.com/a"+urlid);
			xiangxi_url=(String) objectobject.get("url");
			String strHtml_xiangxi = DownloadUtil.getHtmlText(xiangxi_url, 1000 * 30, "UTF-8", null, null);
			if (strHtml_xiangxi==null) {
				continue;
			}
//			System.out.println(strHtml_xiangxi);
			//处理logo图片
			
			strHtml_xiangxi=strHtml_xiangxi.replace("<img src=\"http://n.sinaimg.cn/8ee96216/20150813/appimg.jpg\">", "");
			
			//处理图片
			Document docsimg = Jsoup.parse(strHtml_xiangxi);
			Elements  js_contentps_img=docsimg.select("div.content_wrappr_left img");
			Elements  js_contentps_content_wrappr_left;
			js_contentps_content_wrappr_left=docsimg.select("div.content_wrappr_left");
			
			String new_strHtml_xiangxi=js_contentps_content_wrappr_left.toString();
			for (Element element : js_contentps_img) {
				String imgotherhtml = HtmlAnalyze.getTagText(element.toString(), "<img", "\">", false, 0);
//				String imgurlhtml = HtmlAnalyze.getTagText(imgotherhtml, "data-src=\"", "\"");
//				String newimgurl = Image2.imagUrldownload_allurl(imgurlhtml);
				new_strHtml_xiangxi = new_strHtml_xiangxi.toString().replace(imgotherhtml,
						"<p>"+imgotherhtml+"</p>");
//				String new_img_String ="<p>"+element.toString()+"</p>";
//				new_strHtml_xiangxi = new_strHtml_xiangxi.replace(element.toString(),new_img_String);
//				if (new_strHtml_xiangxi.contains(new_img_String)) {
//					System.out.println(1111);
//				}
//				
//				System.out.println(new_img_String);
			}
			
			
			
//			System.out.println(strHtml_xiangxi);
			
			
			Document docs = Jsoup.parse(new_strHtml_xiangxi);
//			System.out.println(docs.select("div.content_wrappr_left"));
			
			Elements  js_contentps=docs.select("div.content p");
			try {
				
				try {
					if (js_contentps.size()==0) {
					js_contentps=docs.select("div.content_wrappr_left p");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
				if (js_contentps.size()==0) {
					js_contentps=docs.getElementById("article-main").select("p");
				}
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
				if (js_contentps.size()==0) {
					js_contentps=docs.getElementById("text").select("p");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String  js_contentStringp="";
			int ii=0;
			 for (Element element : js_contentps) {
//				if (ii>2) {
					String Stringelement="";
					
					if (element.toString().contains("<img")) {
						if (!element.toString().contains("iframe")) {
						Stringelement=element.toString();
						}
					}else{
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2=element.toString().replace("<strong>", "#1#strong#2#").replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother=docStringelement.text();
							Stringelement=Stringelementother.replace("#1#strong#2#", "<strong>").replace("#1#/strong#2#", "</strong>");
							
						}else{
							if (!element.toString().contains("iframe")) {
								Stringelement=element.text();
							}
						}
						 	
					}
					js_contentStringp=js_contentStringp+Stringelement+"||";
//				}
				ii+=1;
				
				
			}
			
//			System.out.println(search_index = (int) objectobject.get("power"));
//			System.out.println(trend = (String) objectobject.get("trend"));
//			OracleHaoSou.intotem_person_keyword_distrib(data_date, person_id, keyword, search_index, trend, urlMain,data_type);
//			 System.out.println(js_contentStringp);
			 if (!js_contentStringp.equals("")) {
				 WECHAT_INFORMATION wechat=new WECHAT_INFORMATION();
				 wechat.setUrls(xiangxi_url);
				 wechat.setNames(title);
				 String DATES="";
				 System.out.println(objectobject.get("intime"));
				 String behot_time= String.valueOf( objectobject.get("intime"));
				 if (!behot_time.equals("")) {
					 DATES=jinritoutiao.stampToDate(behot_time+"000");
				}
				 wechat.setDates(DATES);
				 wechat.setContentAll(js_contentStringp);
				 wechat.setContentP(js_contentStringp);
				 wechat.setSOURCE(8);
				wechat.setPostUser(String.valueOf(objectobject.get("media_name")));
				
				
				
				JSONObject objectsimg = (JSONObject) objectobject.get("img");
				String imgurls_u=(String) objectsimg.get("u");
				
				String imgurl=String.valueOf(imgurls_u);
				System.out.println(imgurl);
				String imgname="";
				if (!imgurl.equals("")&&imgurl!=null) {
					String imgurls=imgurl.replace("\\/", "/");
					imgname=Image2.imagUrldownload_1(imgurls);
					wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
				}
				
				wechat.setIMG_BIG_NAME(imgname);
				
				wechat.setDATA_TYPE(DATA_TYPE);
				if (!wechat.getContentAll().equals("")) {
					System.out.println(wechat.getContentAll());
					Oracle.InsertWECHAT_INFORMATION(wechat);
				}
				 
			}
		}
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=105&lid=1230&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480487757606");
//		runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=51&lid=740&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480491409744");
//		runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=37&lid=531&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480491730297");
		
		xinlangyule_1.runnewMain("http://feed.mix.sina.com.cn/api/roll/get?pageid=107&lid=1244&num=30&versionNumber=1.2.8&page=1&encode=utf-8&callback=feedCardJsonpCallback&_=1480491886807",1);
	
	}

}
