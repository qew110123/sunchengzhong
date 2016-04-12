package com.artsoft.download.TVPlay.platform;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.artsoft.bean.TvPlay;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.CommonUtil;
import com.artsoft.util.DownloadUtil;
import com.artsoft.util.HtmlAnalyze;
import com.artsoft.util.TimeTest; 

public class Downkankanplatform {

	public static void mainurl(String mainUrl) {

		// String
		// mainUrl="http://list.letv.com/apin/chandata.json?c=2&d=1&md=&o=20&p=4&s=1";
		// ParseJson(BuildJson());
		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "UTF-8", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
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
				System.out.println(url = link.select("a.pic").attr("href"));
				System.out.println(title = link.select("a.pic").attr("title"));
				hunanBranch(url, title);

			}
		} catch (Exception e) {
			// TODO: handle exception
			// /���򱨴�
			System.out.println("����");
		}

	}

	private static void hunanBranch(String mainUrl, String title) {
		// TODO Auto-generated method stub

		String strHtml = "";
		boolean bb = true;
		while (bb) {
			strHtml = DownloadUtil.getHtmlText(mainUrl, 1000 * 30, "GBK", null, null);
			if (strHtml != null && !"".equals(strHtml)) {
				bb = false;
			}
		}
		
		TvPlay playtv=new TvPlay();
		playtv.setTvplay_url(mainUrl);
		
		playtv.setTvplay_name(title);
		
		String yanyuan="";// ��Ա
		String yanyuanAll= HtmlAnalyze.getTagText(strHtml, "����:", "</li>",true,0);
		String[] yanyuanlist=yanyuanAll.split("a><a");
		int i=0;
		for (String stringtext : yanyuanlist) {
			String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss=HtmlAnalyze.getTagText(stringtext, ">", "<");
			yanyuan=yanyuan+textss+"|"+urlss;
			if (yanyuanlist.length!=1&&i+1<yanyuanlist.length) {
				yanyuan=yanyuan+",";
			}
			
			i+=1;
		}
		System.out.println(yanyuan);
		playtv.setMajor_actors(yanyuan);
		
		String daoyan="";// ����
		String daoyanAll= HtmlAnalyze.getTagText(strHtml, "����:", "</li>",true,0);
		String[] daoyanlist=daoyanAll.split("a><a");
		 i=0;
		for (String stringtext : daoyanlist) {
			String urlss=HtmlAnalyze.getTagText(stringtext, "href=\"", "\"");
			String textss=HtmlAnalyze.getTagText(stringtext, ">", "<");
			daoyan=daoyan+textss+"|"+urlss;
			if (daoyanlist.length!=1&&i+1<daoyanlist.length) {
				daoyan=daoyan+",";
			}
			
			i+=1;
		}
		System.out.println(daoyan);
		playtv.setDirector(daoyan);
		
		String diqu=""; //����
		diqu =HtmlAnalyze.getTagText(strHtml, "������", "</li>");
		playtv.setProduction_area(diqu);
		
		String classstr = ""; // ����:
		classstr = HtmlAnalyze.getTagText(strHtml, "����:", "</li>");
		System.out.println(classstr=classstr.replaceAll(" 					", ""));
		playtv.setSubject(classstr);
		
		String detail="";
		detail=HtmlAnalyze.getTagText(strHtml, "id=\"movie_info_intro_l\" style=\"display:none\">", "<span class=\"closeinfo_tigger\">");
		System.out.println(detail);
		if (detail==null||detail.equals("")||detail.equals("null")) {
			detail=HtmlAnalyze.getTagText(strHtml, "id=\"movie_info_intro_s\">", "<span class=\"closeinfo_tigger\">");
		}
		playtv.setBasic_info(detail);
		
		playtv.setClassnum(8);
		
		playtv.setSOURCE(8);
		
		
		OracleOpreater.intoTEM_DIM_TVPLAY_PLATFORM(playtv);
//		OracleOpreater.intoTEM_DIM_FILM_PLATFORM(playtv);
		
		
		
//		String comment = "";
//		System.out.println(comment = HtmlAnalyze.getTagText(strHtml, " total:\"", "\""));
//		System.out.println(comment = comment.replaceAll(",", ""));
//		try {
//			OracleOpreater.intoReputation(title, "8", comment, "0", "", mainUrl, "3", "0");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

	}
	
	//yunxing
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":�� ʼ");

		for (int i = 1; i <= 50; i++) {
			String url = "http://movie.kankan.com/type,order/teleplay,update/page" + i + "/";
			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
			mainurl(url);
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
//		while (true) {
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":��ʼ");
//			for (int i = 1; i <= 36; i++) {
//				String url = "http://movie.kankan.com/type,order/teleplay,update/page" + i + "/";
//				CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":" + url);
//				mainurl(url);
//			}
//			CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":����");
//			try {
//				Thread.sleep(1000*60*60*23);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		TimingTime(23, 59, 59);
//		runstatic();
	}

}
