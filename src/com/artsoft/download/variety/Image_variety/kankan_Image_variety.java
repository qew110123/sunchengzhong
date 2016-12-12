package com.artsoft.download.variety.Image_variety;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadImage;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest;

public class kankan_Image_variety {
	
	
	private static void pinglunAnddafen(String title,String mainUrl) {
		// TODO Auto-generated method stub
		
		String xiaourlString =HtmlAnalyze.getTagText(mainUrl, "/v/", ".shtml");
		if (!xiaourlString.equals("")) {
			String xiaourl ="http://app11.kankan.com/movie_rating/data/"+xiaourlString+".js";
			String strHtml = "";
//			boolean bb = true;
//			while (bb) {
				strHtml = DownloadUtil.getHtmlText(xiaourl, 1000 * 30, "UTF-8", null, null);
//				if (strHtml != null && !"".equals(strHtml)) {
//					bb = false;
//				}
//			}
			String score=HtmlAnalyze.getTagText(strHtml, "rating\":\"", "\"");
			try {
				OracleOpreater.intoReputationAndDETAIL_URL(title, "8", score, "0", "", mainUrl, "2", "1", mainUrl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String comment = ""; // ����
			System.out.println(comment =HtmlAnalyze.getTagText(strHtml, "rating_people_num\":\"", "\""));
			
			try {
				OracleOpreater.intoReputationAndDETAIL_URL(title, "8", comment, "0", "", mainUrl, "2", "2",mainUrl);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	private static void hunanBranch(String mainUrl, String title) {
		// TODO Auto-generated method stub

		String strHtml = "";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		String comment = "";
		System.out.println(comment = HtmlAnalyze.getTagText(strHtml, " total:\"", "\""));
		System.out.println(comment = comment.replaceAll(",", ""));
		try {
			OracleOpreater.intoReputationAndDETAIL_URL(title, "8", comment, "0", "", mainUrl, "2", "0",mainUrl);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			pinglunAnddafen(title,mainUrl);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
	


	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
//		boolean bb = true;
//		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
//			if (strHtml != null && !"".equals(strHtml)) {
//				bb = false;
//			}
//		}
		// String tyPlayName = "";
		// System.out.println(tyPlayName = HtmlAnalyze.getTagText(strHtml,
		// "cname: \"", "\""));
		try {

			// System.out.println(strHtml);
			Document doc = Jsoup.parse(strHtml);
			Element linkmain = doc.getElementById("movie_list");
			Elements links = linkmain.select("li");
			// Element content = doc.getElementById("content");
			// Elements links = content.getElementsByTag("a");
			System.out.println(links.size());
			for (Element link : links) {
				String url = "";
				String title = "";
				// System.out.println(strVolumes = link.select("a.pic").text());
				System.out.println(title = link.select("a.pic").attr("title"));
				System.out.println(url = link.select("a.pic").attr("href"));
//				hunanBranch(url, title);
				String imgurl="";
				System.out.println(imgurl= link.select("img").attr("_src"));
				DownloadImage.download(imgurl, title+".jpg", "D:\\Image\\variety\\kankan\\");

			}
		} catch (Exception e) {
			// TODO: handle exception
			// /���򱨴�
			System.out.println("����");
		}

	}
	
	//yunxing
		public static void runstatic(){
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

			for (int i = 1; i <= 44; i++) {
				//http://movie.kankan.com/type,order/tv,update/page3/
				String url = "http://movie.kankan.com/type,order/tv,update/page" + i + "/";
				CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
				mainurl(url);
//				System.out.println(url);
			}
			
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ��");
		}
		
		
		
		//�ж����ݿ�ʼʱ��
			public static void TimingTime(int hh , int mm ,int ss) {  
		        Calendar calendar = Calendar.getInstance();  
		        calendar.set(Calendar.HOUR_OF_DAY, hh); // ����ʱ  
		        calendar.set(Calendar.MINUTE, mm);       // ���Ʒ�  
		        calendar.set(Calendar.SECOND, ss);       // ������  
		  
		        Date time = calendar.getTime();         // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00  
		  
		        Timer timer = new Timer();  
		        timer.scheduleAtFixedRate(new TimerTask() {  
		            public void run() {  
		                System.out.println("-------�趨Ҫָ������--------");  
		                runstatic();
		            } 
		        }, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��  
		    } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		runstatic();
//		TimingTime(0, 59, 59);
		runstatic();
//		hunanBranch("http://vod.kankan.com/v/91/91924.shtml", "������");
	}

}
