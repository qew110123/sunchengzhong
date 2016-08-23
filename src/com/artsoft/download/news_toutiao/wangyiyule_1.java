package com.artsoft.download.news_toutiao;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.WECHAT_INFORMATION;
import com.artsoft.oracle.Oracle;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;

public class wangyiyule_1 {

	static void runnewMain() {
		// TODO Auto-generated method stub
		String urlMain="http://ent.163.com/special/yyx/";
		String strHtml = DownloadUtil.getHtmlText(urlMain, 1000 * 30, "gbk", null, null);
		
		Document doc = Jsoup.parse(strHtml);
		//
		Elements links = doc.getElementById("news-flow-content").select("li");
		for (Element link : links) {
//			System.out.println(link);
			WECHAT_INFORMATION wechat=new WECHAT_INFORMATION();
			
//			wechat.setWeixinhao(weixinhao);
//			wechat.setWeixinAtion(renzheng);
//			wechat.setRanking(paiming);
//			wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
//			String names=link.select("a").first().text();
			String url= link.select("a").first().attr("href");
			System.out.println(url);
//			url="http://ent.qq.com"+url;
			wechat.setUrls(url);
//			wechat.setUrls(urls);
//			wechat.setUrls(url);
			
			String names=link.select("a").first().text();
			System.out.println(names);
			wechat.setNames(names);
			
			String DATES=link.select("p.sourceDate").toString();//.text();
			DATES=HtmlAnalyze.getTagText(DATES, "</span>", "</p>");
			wechat.setDates(DATES);
			
			
//			String imgurl=link.select("img").first().attr("src");
//			System.out.println(imgurl);
//			
//			wechat.setIMG_BIG_URL("http://img.art-d.com.cn:88/upload/img/news/big/");
//			
//			String imgname="";
//			if (!imgurl.equals("")&&imgurl!=null) {
//				String imgurls=imgurl.replace("\\/", "/");
//				imgname=Image2.imagUrldownload_1(imgurls);
//			}
//			
//			wechat.setIMG_BIG_NAME(imgname);
			
			String strHtmls = DownloadUtil.getHtmlText(url, 1000 * 30, "gbk", null, null);
			
			Document docs = Jsoup.parse(strHtmls);
//			String names=docs.getElementById("page").select("h1").text();
//			System.out.println(names);
//			wechat.setNames(names);
//			String DATES=docs.select("span.time").text();
//			wechat.setDates(DATES);
			String CONTENT_ALL=docs.select("div.con_1").toString();
			
			
			
			
			
			
			wechat.setContentAll(CONTENT_ALL);
//			String CONTENT_P=docs.select("article-detail");
			
			while (CONTENT_ALL.contains("<script")) {
				
				String CONTENT_ALLhtml=HtmlAnalyze.getTagText(CONTENT_ALL, "<script", "</script>",false,0);
				CONTENT_ALL=CONTENT_ALL.replace(CONTENT_ALLhtml, "");
			}
			
			
			Elements  js_contentps=docs.select("div.con_1").select("p");
			String  js_contentStringp="";
			int ii=0;
			 for (Element element : js_contentps) {
//				if (ii>2) {
					String Stringelement="";
					
					if (element.toString().contains("<img")) {
						Stringelement=element.toString();
						
					}else{
						if (element.toString().contains("<strong>")) {
							String Stringelement_1_2=element.toString().replace("<strong>", "#1#strong#2#").replace("</strong>", "#1#/strong#2#");
							Document docStringelement = Jsoup.parse(Stringelement_1_2);
							String Stringelementother=docStringelement.text();
							Stringelement=Stringelementother.replace("#1#strong#2#", "<strong>").replace("#1#/strong#2#", "</strong>");
							
						}else{
							Stringelement=element.text();
						}
						 	
					}
					js_contentStringp=js_contentStringp+Stringelement+"||";
//				}
				ii+=1;
				
				
			}
//			wechat1.setContentP(js_contentStringp);
			
//			System.out.println(js_contentStringp);
			wechat.setContentP(CONTENT_ALL);
				
			wechat.setSOURCE(4);
			
			wechat.setPostUser("网易娱乐");
			
			
			Oracle.InsertWECHAT_INFORMATION(wechat);
		}
	}
	
	
	// 判断数据开始时间
	public static void TimingTime(int hh, int mm, int ss) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时
		calendar.set(Calendar.MINUTE, mm); // 控制分
		calendar.set(Calendar.SECOND, ss); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
//				runstatic();
				try {
					
					runnewMain();
//					
//					DBManager dbm = DBManager.instance();
//					dbm.executeCall(TimeTest.getNowTime("yyyyMMdd"));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}

			
		}, time, 1000 * 60 * 60 * 8);// 这里设定将延时每天固定执行
	}
	
	public static void main(String[] args) {
	//TimingTime(1, 59, 59);
		runnewMain();
	}

}
